package control;

import protocol.Header;
import protocol.RequestSender;
import protocol.ResponseReceiver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Controller {

    public static final int LOG_IN = 1;
    public static final int USER_ACCEPT = 2;
    public static final int STORE_ACCEPT = 3;
    public static final int MENU_ACCEPT = 4;
    public static final int SEARCH_ALL_INFO = 5;
    public static final int QUIT = 6;

    LoginController loginController = new LoginController();
    UserAcceptController userAcceptController = new UserAcceptController();
    StoreAcceptController storeAcceptController = new StoreAcceptController();
    MenuAcceptController menuAcceptController = new MenuAcceptController();
    AllStatisticsController allStatisticsController = new AllStatisticsController();


    public boolean handleCommand(int command, Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {

        ResponseReceiver resReceiver = new ResponseReceiver();
        RequestSender reqSender = new RequestSender();

        switch (command) {

            case LOG_IN:
                loginController.handleLogin(sc,inputStream, outputStream);
                break;

            case USER_ACCEPT:
                userAcceptController.handleAccept(sc, inputStream, outputStream);
                break;

            case STORE_ACCEPT:
                storeAcceptController.handleAccept(sc, inputStream, outputStream);
                break;

            case MENU_ACCEPT:
                menuAcceptController.handleAccept(sc, inputStream, outputStream);
                break;

            case SEARCH_ALL_INFO:
                allStatisticsController.AllhandleStatistics(sc, inputStream, outputStream);
                break;

            case QUIT:
                Header quitHeader = new Header(
                        Header.TYPE_QUIT,
                        (byte) 0,
                        0);
                outputStream.write(quitHeader.getBytes());
                System.out.println("종료합니다.");
                return false;

        }

        return true;
    }
}
