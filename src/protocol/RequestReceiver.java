package protocol;

import persistence.StoreDTO;
import persistence.UserDTO;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequestReceiver {

    public boolean receiveUserIDReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_USER_ID) ? true : false;

        if (typeCheck && codeCheck)
            return true;
        else
            return false;
    }

    public String receiveUserInfoReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_USER_DTO) ? true : false;

        if (typeCheck && codeCheck)
            return bodyReader.readUTF();

        return "";
    }

    public boolean receiveResultReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        return typeCheck && codeCheck;
    }

    public List<UserDTO> receiveUserAcceptResultReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        List<UserDTO> Wusers = new ArrayList<UserDTO>();
        int WuserNum = inputStream.readInt();

        for (int i = 0; i < WuserNum; i++) {
            Wusers.add(UserDTO.read(inputStream));
        }

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if (typeCheck && codeCheck)
            return Wusers;
        else
            return null;
    }

    public String receiveUserIDResultReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if (typeCheck && codeCheck)
            return bodyReader.readUTF();

        return "";
    }

    public boolean receiveAcceptUserNumReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_ACCEPT_USER_NUM) ? true : false;

        if (typeCheck && codeCheck) {
            System.out.println("요청 받음");
        }
        return typeCheck && codeCheck;
    }

    public boolean receiveHowAcceptUserReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_HOW_ACCEPT_USER) ? true : false;

        return typeCheck && codeCheck;
    }

    public String receiveUserIDResult_LogInReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_FAIL) ? true : false;

        String user_id = bodyReader.readUTF();
        if (typeCheck && codeCheck) {
            return user_id;
        }

        return "";
    }

    public UserDTO receiveUserPWResult_LogInReq(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if (typeCheck && codeCheck) {
            return UserDTO.read(bodyReader);
        }

        return null;
    }

    public boolean receiveAcceptStoreNumReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_ACCEPT_STORE_NUM) ? true : false;

        return typeCheck && codeCheck;
    }

    public boolean receiveHowAcceptStoreReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_HOW_ACCEPT_STORE) ? true : false;

        return typeCheck && codeCheck;
    }

    public boolean receiveAcceptMenuNumReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_ACCEPT_STORE_NUM) ? true : false;

        return typeCheck && codeCheck;
    }

    public boolean receiveHowAcceptMenuReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);

        boolean typeCheck = (header.type == header.TYPE_REQ) ? true : false;
        boolean codeCheck = (header.code == header.CODE_HOW_ACCEPT_MENU) ? true : false;

        return typeCheck && codeCheck;
    }

    public List<StoreDTO> receiveStoreAcceptResultReq(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        List<StoreDTO> Wstores = new ArrayList<StoreDTO>();
        int WstoreNum = inputStream.readInt();

        for (int i = 0; i < WstoreNum; i++) {
            Wstores.add(StoreDTO.read(inputStream));
        }

        boolean typeCheck = (header.type == header.TYPE_RES) ? true : false;
        boolean codeCheck = (header.code == header.CODE_SUCCESS) ? true : false;

        if (typeCheck && codeCheck)
            return Wstores;
        else
            return null;
    }
}
