package protocol;

//import persistence.Player;
//import persistence.Team;

import persistence.MenuDTO;
import persistence.StoreDTO;
import persistence.UserDTO;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResponseReceiver {

    public void receiveLogInResult(DataInputStream inputStream) throws IOException {

        Header header = Header.readHeader(inputStream);
        if (header.code == 0x01)
            System.out.println("로그인 성공");
        else
            System.out.println("로그인 실패");

    }

    public List<UserDTO> receiveUserList(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        int size = bodyReader.readInt();

        for (int i = 0; i < size; i++)
            userDTOs.add(UserDTO.read(bodyReader));

        return userDTOs;
    }

    public List<StoreDTO> receiveStoreList(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<StoreDTO> storeDTOS = new ArrayList<StoreDTO>();
        int size = bodyReader.readInt();

        for (int i = 0; i < size; i++)
            storeDTOS.add(StoreDTO.read(bodyReader));

        return storeDTOS;
    }

    public List<MenuDTO> receiveMenuList(DataInputStream inputStream) throws IOException {
        Header header = Header.readHeader(inputStream);
        byte[] body = new byte[header.length];
        inputStream.read(body);
        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));

        List<MenuDTO> menuDTOS = new ArrayList<MenuDTO>();
        int size = bodyReader.readInt();

        for (int i = 0; i < size; i++)
            menuDTOS.add(MenuDTO.read(bodyReader));

        return menuDTOS;
    }

//    public void receiveOnePlayer(DataInputStream inputStream) throws IOException {
//
//        Header header = Header.readHeader(inputStream);
//        byte[] body = new byte[header.bodySize];
//        inputStream.read(body);
//        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
//
//        Player player = Player.readPlayer(bodyReader);
//        System.out.println(player);
//
//    }
//
//    public void receivePlayerList(DataInputStream inputStream) throws IOException {
//
//        Header header = Header.readHeader(inputStream);
//        byte[] body = new byte[header.bodySize];
//        inputStream.read(body);
//        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
//
//        int size = bodyReader.readInt();
//
//        for(int i=0; i<size; i++) {
//            Player player = Player.readPlayer(bodyReader);
//            System.out.println(player);
//        }
//
//    }
//
//    public void receiveTeamList(DataInputStream inputStream) throws IOException {
//
//        Header header = Header.readHeader(inputStream);
//        byte[] body = new byte[header.bodySize];
//        inputStream.read(body);
//        DataInputStream bodyReader = new DataInputStream(new ByteArrayInputStream(body));
//
//        int size = bodyReader.readInt();
//
//        for(int i=0; i<size; i++) {
//            Team team = Team.readTeam(bodyReader);
//            System.out.println(team);
//        }
//
//    }


}
