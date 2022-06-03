package com.meng.view_test.subject03.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatRoomServer {

    static List<Channl> channls = new CopyOnWriteArrayList<>();
    public static void main(String[] args) throws IOException {


        //配置端口
        int port = 8888;
        ServerSocket server = new ServerSocket(port);
        System.out.println("server start on " + port);
        while (true){
            Socket clent = server.accept();
            String userId = UUID.randomUUID().toString();

            //生成通道
            Channl channl = new Channl(clent, userId);
            channls.add(channl);
            new Thread(channl).start();
        }

    }

    static class Channl implements Runnable{
        String userName;
        String userId;
        Socket clent;
        Channl(Socket clent,String userId){
            this.userId = userId;
            this.clent = clent;
        }


        @Override
        public void run() {
            try {
                DataInputStream is = new DataInputStream(clent.getInputStream());

                //发送就收到的信息
                while(true){
                    String msg = is.readUTF();
                    if(msg.startsWith("setUser:")){
                        this.userName = msg.split(":")[1];
                    }else{
                        msg = userName + ":" +msg;
                        System.out.println(msg);

                        for(Channl channl:channls){
                            if(!userId.equals(channl.userId)){
                                OutputStream outputStream = channl.clent.getOutputStream();
                                DataOutputStream os = new DataOutputStream(outputStream);
                                os.writeUTF(msg);
                                os.flush();
                            }
                        }
                    }

                }
            } catch (IOException e) {
                //断开连接移除服务
                channls.removeIf(channl -> userName.equals(channl.userName));
                System.out.println("user:" + userName + "disconnect this server");
            }
        }
    }
}