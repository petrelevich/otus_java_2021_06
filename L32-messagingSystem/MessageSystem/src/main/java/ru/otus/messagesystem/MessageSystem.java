package ru.otus.messagesystem;

import ru.otus.messagesystem.client.MsClient;
import ru.otus.messagesystem.client.ResultDataType;
import ru.otus.messagesystem.message.Message;

public interface MessageSystem {

    void addClient(MsClient msClient);

    void removeClient(String clientId);

    <T extends ResultDataType> boolean newMessage(Message<T> msg);

    void dispose() throws InterruptedException;

    void dispose(Runnable callback) throws InterruptedException;

    void start();

    int currentQueueSize();
}

