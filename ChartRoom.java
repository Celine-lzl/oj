&emsp;&emsp;本聊天室能实现的功能如下：
客户端注册、群聊、私聊、退出
要实现上述功能，我们需要分为服务器端和客户端来共同实现，具体分工如下：
服务器端实现：
维护功能：维护所有在线的客户端
注册功能：将客户端注册名称添加到服务器的客户端集合里
群聊功能：客户端发送消息，其余所有客户端都能接收到消息
私聊功能：客户端与指定客户端发送和接收数据
退出功能：从服务器客户端集合中移除客户端
客户端实现：
注册功能：创建Socket，给服务器发送注册执行
群聊功能：给客户端发送和接收数据
私聊功能：客户端指定客户端（用户）发送数据
退出功能：给服务器发送退出指令（消息）
&emsp;&emsp;写代码时，将服务器端和客户端分为两个包。服务器包里包含两个类，
一个是服务端框架类，另一个是具体方法的实现类。客户端包分为三个类，一个是客户端框架，
一个是从服务器读取数据类，另一个是写数据到服务器类。

插入分类图

服务器包代码如下：

MulThreadServer类代码：

package chartRoomMulityThread;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//多线程服务器端
public class MulThreadServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(60000);
            System.out.println("服务器启动..."+serverSocket.getLocalSocketAddress());
            //线程池
            ExecutorService executorService = Executors
                    .newFixedThreadPool(25);
            while(true){
                Socket client = serverSocket.accept();
                System.out.println("有客户端连接到服务器"+
                        client.getRemoteSocketAddress());
                executorService.execute(new HandlerClient(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

HandlerClient类代码：

package chartRoomMulityThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class HandlerClient implements Runnable {
    private static final Map<String,Socket> ONLINE_CLIENT_Map =
                                    new ConcurrentHashMap<>();
    private final Socket client;

    public HandlerClient(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            InputStream clientInput = client.getInputStream();
            Scanner scanner = new Scanner(clientInput);
            while(true){
                String data = scanner.nextLine();
                //注册
                if(data.startsWith("register:")){
                    String userName = data.split(":")[1];
                    register(userName);
                    continue;
                }
                if(data.startsWith("groupChat:")){
                    String message = data.split(":")[1];
                    groupChat(message);
                    continue;
                }
                if(data.startsWith("privateChat")){
                    String[] segments = data.split(":");
                    String targetUserName = segments[1];
                    String message = segments[2];
                    privateChat(targetUserName,message);
                    continue;
                }
                //退出
                if(data.equals("bye")){
                    bye();
                    continue;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //注册
    private void register(String userName){
        ONLINE_CLIENT_Map.put(userName,this.client);
        printOnlineClient();
        this.sendMessage(this.client,"恭喜"+userName+"注册成功",false);
    }
    //打印当前在线人数
    private void printOnlineClient(){
        System.out.println("当前在线人数："+ONLINE_CLIENT_Map.size()+
                            "用户名如下：");
        for(String userName:ONLINE_CLIENT_Map.keySet()){
            System.out.println(userName);
        }
    }
    //群聊
    private void groupChat(String message){
        for(Map.Entry<String,Socket> entry:ONLINE_CLIENT_Map.entrySet()){
            Socket target = entry.getValue();
            if(target.equals(this.client)){
                continue;
            }
            this.sendMessage(target,message,true);
        }
    }
    private String getCurrentUserName(){
        for(Map.Entry<String,Socket> entry:ONLINE_CLIENT_Map.entrySet()){
            Socket target = entry.getValue();
            if(target.equals(this.client)){
                return entry.getKey();
            }
        }
        return " ";
    }
    //私聊
    private void privateChat(String targetUserName,String message){
             Socket target = ONLINE_CLIENT_Map.get(targetUserName);
             if(target == null){
                 this.sendMessage(client,"用户不存在"+targetUserName,false);
             }else{
                 String currentUserName = this.getCurrentUserName();
                 this.sendMessage(target,"<"+currentUserName+":"+message,true);
             }
    }
    //退出
    private void bye() {
          for(Map.Entry<String,Socket> entry:ONLINE_CLIENT_Map.entrySet()){
              Socket target = entry.getValue();
              if(target.equals(this.client)){
                  ONLINE_CLIENT_Map.remove(entry.getKey());
                  break;
              }
          }
    }

    private void sendMessage(Socket target,String message,boolean prefix){
        String currentUserName = this.getCurrentUserName();
        OutputStream clientOutput = null;
        try {
            clientOutput = target.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(clientOutput);
            if(prefix) {
                writer.write("" + currentUserName + ":" + message + "\n");
            }else {
                    writer.write(message+"\n");
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

客户端包下的代码：
MulThreadClient类下的代码：

package com.bittech.chartroom;

import java.io.IOException;
import java.net.Socket;

public class MulThreadClient {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("127.0.0.1",60000);
            new ReadDataFromServerThread(client).start();
            new WriteDataToServeThread(client).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

ReadDataFromServerThread类下的代码

package com.bittech.chartroom;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ReadDataFromServerThread extends Thread {
    private final Socket client;
    public ReadDataFromServerThread(Socket client){
        this.client = client;
    }
    public void run(){
        InputStream clientInput = null;
        try {
            clientInput = this.client.getInputStream();
            Scanner scanner = new Scanner(clientInput);
            while(true){
                String data = scanner.nextLine();
                System.out.println("来自服务器的消息："+data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


WriteDataToServeThread类下的代码

package com.bittech.chartroom;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

//客户端给服务端发送数据
public class WriteDataToServeThread extends Thread{
    private final Socket client;

    public WriteDataToServeThread(Socket client) {
        this.client = client;
    }
    public void run(){
        OutputStream clientOutput = null;
        try {
            clientOutput = this.client.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(clientOutput);
            Scanner scanner = new Scanner(System.in);
            while(true){
                System.out.print("请输入>");
                String data = scanner.nextLine();
                writer.write(data+"\n");
                writer.flush();
                if(data.equals("bye")){
                    break;
                }
            }
            this.client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
