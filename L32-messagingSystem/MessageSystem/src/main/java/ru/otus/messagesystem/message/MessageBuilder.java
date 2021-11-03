package ru.otus.messagesystem.message;

import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.client.ResultDataType;

import java.util.UUID;

public class MessageBuilder {
    private static final Message<ResultDataTypeVoid> VOID_MESSAGE =
            new Message<>(new MessageId(UUID.randomUUID().toString()), null, null,
                    null, MessageType.VOID_MESSAGE, new ResultDataTypeVoid(), null);

    private MessageBuilder() {
    }

    public static Message<ResultDataTypeVoid> getVoidMessage() {
        return VOID_MESSAGE;
    }

    public static <T extends ResultDataType> Message<T> buildReplyMessage(Message<T> message, T data) {
        return buildMessage(message.getTo(), message.getFrom(), message.getId(), data, message.getType(), message.getCallback());
    }

    public static <T extends ResultDataType> Message<T> buildMessage(String from, String to, MessageId sourceMessageId,
                                                                     T data, MessageType msgType, MessageCallback<T> callback) {
        String id = UUID.randomUUID().toString();
        return new Message<>(new MessageId(id), from, to, sourceMessageId, msgType, data, callback);
    }

    public static class ResultDataTypeVoid implements ResultDataType {

    }
}
