package com.apps.haitao.twatcher.twserver;

import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	
	private Socket socket;
	
	private InputStream is;
	
	private OutputStream os;
	
	private InputStreamReader isr;
	
	private OutputStreamWriter osw;
	
	private BufferedReader br;
	
	private BufferedWriter bw;
	
	private PrintWriter pw;
	
	//private DataInputStream dis;
	
	//private DataOutputStream dos;
	
	//private boolean read = true;
	
	//private boolean write = false;
	
	public ServerThread(Socket socket) {
		//super();
		this.socket = socket;
		is = null;
		os = null;
		isr = null;
		osw = null;
		br = null;
		bw = null;
		pw = null;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//super.run();
		try {
			initIOs();
			showSocket();
			
			//现在问题出在这！！！！！！
			//只有写完 关闭 shutdowOutput..
			String sends = sendToClient();
			System.out.println("Send To Client:" + sends);
			socket.shutdownOutput();
			
			String receives = recvFromClient();
			System.out.println("Receive From Client:" + receives);
			socket.shutdownInput();
			
		/*	DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			//out.writeUTF("已经收到你发送的信息!");
			String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
			out.writeUTF(s);
			System.out.println("send!");
			
			socket.shutdownOutput();
			
			DataInputStream in = new DataInputStream(socket.getInputStream());
			String data = in.readUTF();
			System.out.println("recv!" + data);
			InputStream in = socket.getInputStream();
			BufferedReader bufr = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			String line = "";
			StringBuilder builder = new StringBuilder();
			while ((line = bufr.readLine()) != null) {
				builder.append(line);
			}
			builder.append((line = bufr.readLine()));
			System.out.println("Receive: " + builder.toString());
			//socket.shutdownInput();
			
			
			OutputStream out  = socket.getOutputStream();
			BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
			bufw.write(builder.toString() + "222");
			bufw.newLine();
			bufw.flush();
			System.out.println("Send: " + builder.toString() + "22222");
			
			bufr.close();
			in.close();
			bufw.close();
			out.close();
			Thread.sleep(1000);*/
		} catch (Exception exp) {
			exp.printStackTrace();
		} finally {
			try {
				closeIOs();
				closeSocket();
			} catch (IOException exp) {
				exp.printStackTrace();
			}
		}
	}
	
	private void showSocket() {
		System.out.println("-----isBound:" + socket.isBound() + "-----isConnected:" + socket.isConnected() + "-----");
		System.out.println("-----isInputShutdown:" + socket.isInputShutdown() + "-----isOutputShutdow:" + socket.isOutputShutdown() + "-----");
	}
	
	private void initIOs() throws IOException {
		is = socket.getInputStream();
		os = socket.getOutputStream();
		isr = new InputStreamReader(is);
		osw = new OutputStreamWriter(os);
		br = new BufferedReader(isr);
		bw = new BufferedWriter(osw);
		pw = new PrintWriter(bw);
	}

	private String recvFromClient() throws IOException {
		String s = null;
		StringBuilder sb = new StringBuilder();
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}
		return sb.toString();
	}
	
	//用于转发给其他
	/*private void sendToOtherClient(String datas) throws IOException {
		//String s = "Your Datas";
		//pw.write(datas);
		pw.println(datas); //防止用BufferReader读取时因读取不到换行符而阻塞无法停止
		pw.flush();
		System.out.println("Now Transforming!!");
		//return datas;
	}
	*/
	
	private String sendToClient() throws IOException {
		StringBuilder sb = new StringBuilder();
		String s = null; //"To Client Msgs";
		//while
		s = "Thanks, I'm server!!!" + System.lineSeparator(); //用BufferReader读一定要有换行符!!!
		sb.append(s);
		//
		s = sb.toString();
		//os.write(s.getBytes("UTF-8"));
		//os.flush();
		//pw.write("12345688888866666666\n");
		pw.println(s);
		pw.flush();
		return s;
	}
	
	private void closeIOs() throws IOException {
		if (!socket.isInputShutdown()) {
			socket.shutdownInput();
		}
		if (!socket.isOutputShutdown()) {
			socket.shutdownOutput();
		}
		if (pw != null) {
			pw.close();
		}
		if (br != null) {
			br.close();
		}
		if (bw != null) {
			bw.close();
		}
		if (isr != null) {
			isr.close();
		}
		if (osw != null) {
			osw .close();
		}
		if (is != null) {
			is.close();
		}
		if (os != null) {
			os.close();
		}
	}
	
	private void closeSocket() throws IOException {
		socket.close();
	}
	
	/*private void concludeModeFlag() throws IOException {
		int i = is.read();
		if (i > 0) {
			read = true;
			write = false;
		} else {
			write = true;
			read = false;
		}
	}*/
	
	/*private void writeMode() throws IOException {
		String sends = sendToClient();
		System.out.println("Send To Client:" + sends);
		socket.shutdownOutput();
	}
	*/
	
	/*private void readMode() throws IOException {
		String receives = recvFromClient();
		System.out.println("Receive From Client:" + receives);
		socket.shutdownInput();
	}*/
	
}
