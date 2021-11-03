package ru.otus;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.db.handlers.GetUserDataRequestHandler;
import ru.otus.front.FrontendServiceImpl;
import ru.otus.db.DBServiceImpl;
import ru.otus.front.handlers.GetUserDataResponseHandler;
import ru.otus.messagesystem.HandlersStoreImpl;
import ru.otus.messagesystem.MessageSystemImpl;
import ru.otus.messagesystem.message.MessageType;
import ru.otus.messagesystem.client.MsClientImpl;

public class MSMain {
    private static final Logger logger = LoggerFactory.getLogger(MSMain.class);

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    public static void main(String[] args) throws InterruptedException {
        var messageSystem = new MessageSystemImpl();

        var requestHandlerDatabaseStore = new HandlersStoreImpl();
        requestHandlerDatabaseStore.addHandler(MessageType.USER_DATA, new GetUserDataRequestHandler(new DBServiceImpl()));
        var databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem, requestHandlerDatabaseStore);
        messageSystem.addClient(databaseMsClient);

        var requestHandlerFrontendStore = new HandlersStoreImpl();
        requestHandlerFrontendStore.addHandler(MessageType.USER_DATA, new GetUserDataResponseHandler());

        var frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem, requestHandlerFrontendStore);
        var frontendService = new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
        messageSystem.addClient(frontendMsClient);

        frontendService.getUserData(1, data -> logger.info("got data:{}", data));
        frontendService.getUserData(2, data -> logger.info("got data:{}", data));

        Thread.sleep(100);
        messageSystem.dispose();
        logger.info("done");
    }
}
