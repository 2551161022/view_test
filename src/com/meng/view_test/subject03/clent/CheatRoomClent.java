package com.meng.view_test.subject03.clent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/*
 * 聊天室客户端
 * */

public class CheatRoomClent {
    public static void main(String[] args) throws IOException {
        //建立连接
        Socket socket = new Socket("127.0.0.1",8888);
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        //开启消息接收
        new Thread(new Channl(socket)).start();

        System.out.println("输入消息并按回车发送,输入exit出");
        while(true){
            String msg = scanner.next();
            if("exit".equals(msg)){
                break;
            }
            os.writeUTF(msg);
            os.flush();
        }

        scanner.close();
        os.close();
        scanner.close();
        socket.close();

    }

    //用来接收消息的通道
    static class Channl implements Runnable{
        Socket clent;


        public Channl(Socket clent){
            this.clent = clent;
        }
        @Override
        public void run() {
            try {
                DataInputStream is = new DataInputStream(clent.getInputStream());
                String msg = is.readUTF();
                System.out.println(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}