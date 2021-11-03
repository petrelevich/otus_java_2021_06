package ru.otus.messagesystem;

import ru.otus.messagesystem.message.MessageType;

public interface HandlersStore {
    RequestHandler getHandlerByType(MessageType messageType);

    void addHandler(MessageType messageType, RequestHandler handler);
}
