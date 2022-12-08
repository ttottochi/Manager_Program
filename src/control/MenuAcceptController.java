package control;

import persistence.MenuDTO;
import persistence.UserDTO;
import protocol.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MenuAcceptController {
    public void handleAccept(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        ResponseSender responseSender = new ResponseSender();
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();
        RequestReceiver requestReceiver = new RequestReceiver();
        //시작 신호 보내기
        Header start_Header = new Header(Header.TYPE_START, Header.CODE_USER_ACCEPT, 0);
        outputStream.write(start_Header.getBytes());
        int menu_num=0;

        List<MenuDTO> menuList = responseReceiver.receiveMenuList(inputStream);

        System.out.println("<승인 대기 중인 메뉴>");
        printMenuWithNumber(menuList);
        if (requestReceiver.receiveAcceptMenuNumReq(inputStream)) //메뉴번호 입력요청 받기
            menu_num=responseSender.sendAcceptMenuIDAns(menuList, sc, outputStream);
        if (requestReceiver.receiveHowAcceptMenuReq(inputStream))//메뉴 승인여부 입력요청 받기
            responseSender.sendHowAcceptMenuAns(sc,menu_num, outputStream);

        if (requestReceiver.receiveResultReq(inputStream))
            System.out.println("메뉴 승인/거절이 완료되었습니다.");
        else
            System.out.println("메뉴 승인거절 실패.");
    }

    public void printMenuWithNumber(List<MenuDTO> menu) {
        int i = 1;
        System.out.println("====================================");
        for (MenuDTO menus : menu) {
            System.out.println("(" + i + ")");
            System.out.println("메뉴명 : " + menus.getMenu_name());
            System.out.println("카테고리 : " + menus.getMenu_category());
            System.out.println("가격 : " + menus.getMenu_price());
            System.out.println("====================================");
            i++;
        }
    }
}
