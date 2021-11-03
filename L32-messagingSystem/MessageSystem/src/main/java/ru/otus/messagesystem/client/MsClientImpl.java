package ru.otus.messagesystem.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.messagesystem.HandlersStore;
import ru.otus.messagesystem.message.Message;
import ru.otus.messagesystem.message.MessageBuilder;
import ru.otus.messagesystem.MessageSystem;
import ru.otus.messagesystem.message.MessageType;

import java.util.Objects;

public class MsClientImpl implements MsClient {
    private static final Logger logger = LoggerFactory.getLogger(MsClientImpl.class);

    private final String name;
    private final MessageSystem messageSystem;
    private final HandlersStore handlersStore;

    public MsClientImpl(String name, MessageSystem messageSystem, HandlersStore handlersStore) {
        if (name == null) {
            throw new IllegalArgumentException("name can't be null");
        }
        this.name = name;
        this.messageSystem = messageSystem;
        this.handlersStore = handlersStore;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public <T extends ResultDataType> boolean sendMessage(Message<T> msg) {
        boolean result = messageSystem.newMessage(msg);
        if (!result) {
            logger.error("the last message was rejected: {}", msg);
        }
        return result;
    }

    @Override
    public <T extends ResultDataType> void handle(Message<T> msg) {
        logger.info("new message:{}", msg);
        try {
            var requestHandler = handlersStore.getHandlerByType(msg.getType());
            if (requestHandler != null) {
                requestHandler.handle(msg).ifPresent(this::sendMessage);
            } else {
                logger.error("handler not found for the message type:{}", msg.getType());
            }
        } catch (Exception ex) {
            logger.error("msg:{}", msg, ex);
        }
    }

    @Override
    public <T extends ResultDataType> Message<T> produceMessage(String to, T data, MessageType msgType, MessageCallback<T> callback) {
        return MessageBuilder.buildMessage(name, to, null, data, msgType, callback);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MsClientImpl msClient = (MsClientImpl) o;
        return name.equals(msClient.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
