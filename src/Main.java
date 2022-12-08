import control.Controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("192.168.224.109", 3000);
        //192.168.227.206

        System.out.println("connected.");

        DataInputStream is = new DataInputStream(socket.getInputStream());
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        Scanner sc = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("=============관리자 프로그램==============");
        System.out.println("======================================");

        boolean isContinue = true;

        Controller controller = new Controller();

        while(isContinue) {

            int command;

            System.out.println("1. 로그인");
            System.out.println("2. 점주 가입 승인 / 거절");
            System.out.println("3. 가게 승인 / 거절");
            System.out.println("4. 메뉴 승인 / 거절");
            System.out.println("5. 통계정보 열람");
            System.out.println("6. 종료");
            System.out.println("======================================");
            System.out.print("메뉴를 선택하세요 : ");
            command = sc.nextInt();

            isContinue = controller.handleCommand(command, sc, is, os);

            System.out.println("======================================");


        } // end of while


    } // end of main
}
