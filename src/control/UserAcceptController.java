package control;

import persistence.UserDTO;
import protocol.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserAcceptController {
    public void handleAccept(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        ResponseSender responseSender = new ResponseSender();
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();
        RequestReceiver requestReceiver = new RequestReceiver();
        //시작 신호 보내기
        Header start_Header = new Header(Header.TYPE_START, Header.CODE_USER_ACCEPT, 0);
        outputStream.write(start_Header.getBytes());
        String user_id ="";

        List<UserDTO> userList = responseReceiver.receiveUserList(inputStream);

            System.out.println("<승인 대기 중인 계정>");
            printUserWithNumber(userList);
            requestSender.sendRequestReceiveAcceptUserNumReq(outputStream);
            if (requestReceiver.receiveAcceptUserNumReq(inputStream)) //계정번호 입력요청 받기
                user_id = responseSender.sendAcceptUserIDAns(userList, sc, outputStream);;
            if (requestReceiver.receiveHowAcceptUserReq(inputStream))//계정 승인여부 입력요청 받기
                responseSender.sendHowAcceptUserAns(sc,user_id, outputStream);

            List<UserDTO> Wusers = new ArrayList<UserDTO>();

            if((Wusers = requestReceiver.receiveUserAcceptResultReq(inputStream)) != null)
                System.out.println("계정 승인/거절이 완료되었습니다.");
            else
                System.out.println("계정 승인거절 실패.");

            printUserWithNumber(Wusers);


    }

    public void printUserWithNumber(List<UserDTO> user) {
        int i = 1;
        System.out.println("====================================");
        for (UserDTO users : user) {
            System.out.println("(" + i + ")");
            System.out.println("ID : " + users.getUser_id());
            System.out.println("PW : " + users.getUser_pw());
            System.out.println("이름 : " + users.getUser_name());
            System.out.println("휴대폰번호 : " + users.getUser_phone());
            System.out.println("주소 : " + users.getUser_address());
            if (users.getUser_state()) System.out.println("승인여부 :  승인완료");
            else System.out.println("승인여부 : 승인대기");
            System.out.println("====================================");
            i++;
        }
    }
}
