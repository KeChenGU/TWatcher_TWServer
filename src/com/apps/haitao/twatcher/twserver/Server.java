package com.apps.haitao.twatcher.twserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
//import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Server {
	
	private static List<Socket> socketList = new ArrayList<>();
	
	//@SuppressWarnings("serial")
	public static void main(String[] args) {
		ServerSocket servSocket = null;
		try {
			servSocket = new ServerSocket(12306);
			//servSocket.bind();
			System.out.println("-----服务器开始监听------");
			System.out.println(servSocket.toString());
			while(true) {
				Socket clieSocket = servSocket.accept();
				new ServerThread(clieSocket).start();
				System.out.println("-----新线程开启-----");
				socketList.add(clieSocket);
				System.out.println(clieSocket.toString());
			}
		} catch (IOException exp) {
			exp.printStackTrace();
		} finally {
			try {
				servSocket.close();
			} catch(IOException exp) {
				exp.printStackTrace();
			}
			if (socketList != null && socketList.size() > 0) {
				Iterator<Socket> iterator = socketList.iterator();
				while (iterator.hasNext()) {
					Socket socket = iterator.next();
					System.out.println("-----------------------------------------------------------");
					System.out.println("clientHostIp:" + socket.getInetAddress() + " clientPort:" + socket.getLocalPort());
					System.out.println("serverIp:" + socket.getRemoteSocketAddress() + " serverPort:" + socket.getPort());
					System.out.println("-----------------------------------------------------------");
				}
				socketList.clear();
			}
		}
	}

	public static List<Socket> getSocketList() {
		return socketList;
	}

	public static void setSocketList(List<Socket> socketList) {
		Server.socketList = socketList;
	}
	
	
}
