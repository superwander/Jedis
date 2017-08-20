package cn.itcast.udp.send;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.util.Scanner;

import org.junit.Test;

/**
 * 基于UDP协议的发送端
 * 
 * @author 王伟
 * @version 1.0, 2017-8-11 13:56:20
 */
public class UDPSendDemo {

	/**
	 * 发送消息
	 * @throws IOException 
	 */
	@Test
	public void send() throws IOException{
		//1 创建DatagramSocket对象
		DatagramSocket sendsocket = new DatagramSocket();
		/*
		 * 2 创建DatagramPacket对象，并封装数据
		 * 
		 * 构造数据报表，用来将长度为length的宝发送到指定主机上的指定端口
		 * 
		 */
		Scanner scanner = new Scanner(System.in);
		while(true){
			String messager = scanner.next();
			
			byte[] buffer = messager.getBytes();
			DatagramPacket dp = new DatagramPacket(buffer,  buffer.length, InetAddress.getByName("127.0.0.1"),10000);
			//发送消息
			sendsocket.send(dp);
			if(messager.contains("拜拜")){
				break;
			}
		}
		
		
		
		//释放资源
		sendsocket.close();
	}
}
