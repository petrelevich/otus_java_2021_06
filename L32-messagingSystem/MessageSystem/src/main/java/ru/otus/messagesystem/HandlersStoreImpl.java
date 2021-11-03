package ru.otus.messagesystem;

import ru.otus.messagesystem.message.MessageType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HandlersStoreImpl implements HandlersStore {
    private final Map<MessageType, RequestHandler> handlers = new ConcurrentHashMap<>();

    @Override
    public RequestHandler getHandlerByType(MessageType messageType) {
        return handlers.get(messageType);
    }

    @Override
    public void addHandler(MessageType messageType, RequestHandler handler) {
        handlers.put(messageType, handler);
    }


}
