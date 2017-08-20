package cn.itcast.maven.queue;

import java.util.Random;
import java.util.UUID;

import redis.clients.jedis.Jedis;

/**
 * 模拟消息队列
 */
public class JedisQueue {

	public static void main(String[] args) {
		
		JedisProducer producer = new JedisProducer();
		JedisConsumer consumer = new JedisConsumer();
		new Thread(producer).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		new Thread(consumer).start();
	}
}

/**
 * 生产者
 */
class JedisProducer implements Runnable{

	public void run() {
		
		Jedis jedis = new Jedis("192.168.169.129");
		jedis.del("producer");
		Random random = new Random();
		while(true){
			try {
				Thread.sleep(1000+random.nextInt(3)*1000);
				//生产消息
				String message = UUID.randomUUID().toString();
				System.out.println("生产了一条消息"+message);
				jedis.lpush("producer", message);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

/**
 * 消费者
 */
class JedisConsumer implements Runnable{

	public void run() {
		Jedis jedis = new Jedis("192.168.169.129");
		jedis.del("consumer");
		Random random = new Random();
		while(true){
			try {
				Thread.sleep(1000);
				String rpoplpush = jedis.rpoplpush("producer", "consumer");
				if(random.nextInt(9)%4==0){
					jedis.lpop("consumer");
					System.out.println(rpoplpush+"  执行成功");
				}else {
					jedis.rpoplpush("consumer", "producer");
					System.out.println(rpoplpush+" 执行失败");
				}
				
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
