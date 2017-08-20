package cn.itcast.tcp.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

import org.junit.Test;

/**
 * TCP协议客户端
 * 
 * @author 王伟
 * @version 1.0， 2017-8-11 19:04:48
 */
public class TCPClient {

	/**
	 * 客户端程序
	 * @throws IOException 
	 */
	@Test
	public void client() throws IOException{
		InetAddress address = InetAddress.getByName("127.0.0.1");
		Socket socket = new Socket(address, 8888);
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("你好".getBytes());
		socket.shutdownOutput();
		InputStream inputStream = socket.getInputStream();
		byte[] buf = new byte[1024];
		int len = inputStream.read(buf);
		System.out.println(new String(buf, 0, len));
		outputStream.close();
		socket.close();
	}
}
