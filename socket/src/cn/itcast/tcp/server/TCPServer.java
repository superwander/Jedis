package cn.itcast.tcp.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * 	TCP 协议服务端
 * 
 * @author 王伟
 * @version 1.0, 2017-8-11 19:01:18
 */
public class TCPServer {

	/**
	 * 服务端
	 * @throws IOException 
	 */
	@Test
	public void server() throws IOException{
		
		ServerSocket serverSocket = new ServerSocket(8888);
		//监听并1获取套接字
		Socket socket = serverSocket.accept();
		InetAddress address = socket.getLocalAddress();
		System.out.println(address.getHostName());
		//获取套接字的输入流
		InputStream inputStream = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		//读取输入流，获得内容
		while((len = inputStream.read(buf))!=-1){
			String content = new String(buf,0,len);
			System.out.println(content);
		}
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("你好，已连接".getBytes());
		socket.close();
		//serverSocket.close();服务器流通常不关闭
	}
}
