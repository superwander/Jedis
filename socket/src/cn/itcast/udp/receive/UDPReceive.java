package cn.itcast.udp.receive;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import org.junit.Test;

/**
 * UDP协议的接收端
 * 
 * @author 王伟
 * @version 1.0, 2017-8-11 15:51:54
 */
public class UDPReceive {

	/**
	 * 1,创建DatagramSocket对象
	 * 2,创建DatagramPacket对象
	 * 3,接收数据存储到DatagramPacket对象中
	 * 4,获取DatagramPacket对象的内容
	 * 5,释放流资源
	 * @throws IOException 
	 */
	@Test
	public void receive() throws IOException{
		//创建DatagramSocket对象,并指定端口号
		DatagramSocket receiveSocket = new DatagramSocket(10000);
		//2,创建DatagramPacket对象, 创建一个空的仓库
		byte[] buffer = new byte[1024];
		DatagramPacket dp = new DatagramPacket(buffer, 1024);
		//接收数据存储到DatagramPacket对象中
		while(true){
			receiveSocket.receive(dp);
			//获取DatagramPacket 对象的内容
			//谁发来的数据 getAddress()
			InetAddress address = dp.getAddress();
			String hostAddress = address.getHostAddress();//获取主机地址
			String hostName = address.getHostName();
			System.out.println(hostAddress +"           "+hostName);
			//发来了什么数据
			byte[] data = dp.getData();
			//发来数据的长度
			int length = dp.getLength();
			//显示收到的数据
			String datacontent = new String(data,0,length);
			System.out.println(datacontent);
			//循环出口
			if(datacontent.contains("拜拜")){
				break;
			}
			
		}
		//释放资源
		receiveSocket.close();
	}
}
