package ru.otus.front;

import ru.otus.dto.UserData;
import ru.otus.messagesystem.client.MessageCallback;
import ru.otus.messagesystem.message.MessageType;
import ru.otus.messagesystem.client.MsClient;


public record FrontendServiceImpl(MsClient msClient,
                                  String databaseServiceClientName) implements FrontendService {

    @Override
    public void getUserData(long userId, MessageCallback<UserData> dataConsumer) {
        var outMsg = msClient.produceMessage(databaseServiceClientName, new UserData(userId),
                MessageType.USER_DATA, dataConsumer);
        msClient.sendMessage(outMsg);
    }
}
