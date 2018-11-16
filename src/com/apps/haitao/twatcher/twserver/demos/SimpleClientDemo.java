package com.apps.haitao.twatcher.twserver.demos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClientDemo {
	
	public static void main(String[] args) {
		try {
			 
	        Socket socket = new Socket("自己计算机的IP地址", 30000);
	        //设置10秒之后即认为是超时
	        socket.setSoTimeout(10000);
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                socket.getInputStream()));
	        @SuppressWarnings("unused")
			String line = br.readLine();

	        //show.setText("来自服务器的数据："+line);

	        br.close();
	        socket.close();

	    } catch (UnknownHostException e) {
	        // TODO Auto-generated catch block
	        //Log.e("UnknownHost", "来自服务器的数据");
	        e.printStackTrace();
	    } catch (IOException e) {
	        //Log.e("IOException", "来自服务器的数据");
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	}
}

