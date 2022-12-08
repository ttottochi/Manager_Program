package control;

import persistence.StoreDTO;
import persistence.UserDTO;
import protocol.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StoreAcceptController {
    public void handleAccept(Scanner sc, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
        ResponseSender responseSender = new ResponseSender();
        ResponseReceiver responseReceiver = new ResponseReceiver();
        RequestSender requestSender = new RequestSender();
        RequestReceiver requestReceiver = new RequestReceiver();
        //시작 신호 보내기
        Header start_Header = new Header(Header.TYPE_START, Header.CODE_STORE_ACCEPT, 0);
        outputStream.write(start_Header.getBytes());
        int store_id = 0;

        List<StoreDTO> storeList = responseReceiver.receiveStoreList(inputStream);

        System.out.println("<승인 대기 중인 가게>");
        printStoreWithNumber(storeList);
        requestSender.sendRequestReceiveAcceptStoreNumReq(outputStream);
        if (requestReceiver.receiveAcceptStoreNumReq(inputStream)) //가게번호 입력요청 받기
            store_id = responseSender.sendAcceptStoreIDAns(storeList, sc, outputStream);
        if (requestReceiver.receiveHowAcceptStoreReq(inputStream))//가게 승인여부 입력요청 받기
            responseSender.sendHowAcceptStoreAns(sc, store_id, outputStream);

        List<StoreDTO> Wstores = new ArrayList<StoreDTO>();

        if ((Wstores = requestReceiver.receiveStoreAcceptResultReq(inputStream)) != null)
            System.out.println("가게 승인/거절이 완료되었습니다.");
        else
            System.out.println("가게 승인거절 실패.");

        printStoreWithNumber(Wstores);
    }

    public void printStoreWithNumber(List<StoreDTO> store) {
        int i = 1;
        System.out.println("====================================");
        for (StoreDTO stores : store) {
            System.out.println("(" + i + ")");
            System.out.println("가게명 : " + stores.getStore_name());
            System.out.println("가게주 : " + stores.getUser_id());
            System.out.println("휴대폰번호 : " + stores.getStore_phone());
            System.out.println("주소 : " + stores.getStore_address());
            System.out.println("영업시간 : " + stores.getStore_time());
            System.out.println("코멘트 : " + stores.getStore_info());
            System.out.println("====================================");
            i++;
        }
    }
}
