package com.apps.haitao.twatcher.twserver.demos;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSocketDemo {
	
	private Socket socket;  
	  
    /**  
     * 建立服务端连接  
     */  
    public void conn() {  
        new Thread() {  
  
            @Override  
            public void run() {  
  
                try {  
                    socket = new Socket("100.100.28.26", 9999);  
                    //Log.e("JAVA", "建立连接：" + socket);  
                } catch (UnknownHostException e) {  
                    e.printStackTrace();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }.start();  
    }  
  
    /**  
     * 发送消息 
     */  
    public void send() {  
        new Thread() {  
            @Override  
            public void run() {  
  
                try {  
                    // socket.getInputStream()  
                    DataOutputStream writer = new DataOutputStream(socket.getOutputStream());  
                    writer.writeUTF("嘿嘿，你好啊，服务器.."); // 写一个UTF-8的信息  
                      
                    System.out.println("发送消息");  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }.start();  
    }  
}
