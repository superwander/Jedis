package cn.itcast.maven.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * Hello world!
 *
 */
public class JedisDemo1 {
    
	private Jedis jedis;
	
	@Before 
	public void init(){
		jedis = new Jedis("192.168.169.129");
	}
	
	@Test
	public void test(){
		//添加key和value
		//jedis.set("school", "itcast");
		//在原来的内容上追加内容
		jedis.append("company", "is a good school");
		//判断并添加key和value 如果key存在，则不添加
		//Long setnx = jedis.setnx("city", "西安");
		//获取key对应value的字符串长度
		//Long len = jedis.strlen("company");
		//jedis.set("age", "1");
		//key对应的value自增，如果key存在，则自增，不存在，则自动创建key,默认值为0，并自增指定增量
		//Long incrBy = jedis.incrBy("age", 1);
		//替换内容
		//jedis.setrange("company", 0, "itheima");
		String string = jedis.get("company");	
		System.out.println(string);
		
	}
	
	/**
	 * 集合练习
	 */
	@Test
	public void test1(){
		
		//jedis.lpush("l-key-01", "itcast","itheima");
		//将指定索引的value进行替换
		jedis.lset("l-key-01", 1, "csdn");
		//根据索引获取value
		String index = jedis.lindex("l-key-01", 1);
		System.out.println(index);
		//按索引范围查询结果
		List<String> list = jedis.lrange("l-key-01", 0, -1);
		//String lpop = jedis.lpop("l-key-01");
		//System.out.println(lpop);
		System.out.println(list);
		
	}
	
	/**
	 * hash类型
	 */
	@Test
	public void test2() {
		
		//设置信息
		jedis.hset("h-key-01", "name", "王尼玛");
		jedis.hset("h-key-01", "age", "20");
		jedis.hset("h-key-01", "desc", "大头死变态");
		
		Map<String, String> all = jedis.hgetAll("h-key-01");
		Set<String> keySet = all.keySet();
		for (String key : keySet) {
			System.out.println(key+"    "+all.get(key));
		}
		
		Set<String> set = jedis.hkeys("h-key-01");
		List<String> hvals = jedis.hvals("h-key-01");
		System.out.println("所有的键");
		for (String key : set) {
			System.out.println(key);
		}
		System.out.println("所有的值");
		for (String val : hvals) {
			System.out.println(val);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "唐马儒");
		map.put("age", "20");
		map.put("job", "鉴黄师");
		jedis.hmset("h-key-02", map);
		List<String> list = jedis.hmget("h-key-02", "name","age","job");
		Boolean boolean1 = jedis.hexists("h-key-02", "desc");
		System.out.println(boolean1);
		System.out.println(list);
	}
	
	/**
	 * set集合练习
	 */
	@Test
	public void test3() {
		
		jedis.sadd("s-key-01","a");
		jedis.sadd("s-key-01", "b");
		jedis.sadd("s-key-01", "c");
		jedis.sadd("s-key-01", "d");
		jedis.sadd("s-key-01", "e");
		jedis.sadd("s-key-01", "a");
		
		jedis.sadd("s-key-02","g");
		jedis.sadd("s-key-02", "h");
		jedis.sadd("s-key-02", "j");
		jedis.sadd("s-key-02", "d");
		jedis.sadd("s-key-02", "e");
		jedis.sadd("s-key-02", "a");
		Long long1 = jedis.scard("s-key-01");
		System.out.println(long1);
		Set<String> set = jedis.smembers("s-key-01");
		for (String value : set) {
			System.out.println(value);
		}
		//并集
		Set<String> sunion = jedis.sunion("s-key-01","s-key-02");
		System.out.println("并集内容");
		for (String value : sunion) {
			System.out.println(value);
		}
		//差集
		System.out.println("差集内容s-key-01-----s-key-02");
		Set<String> set2 = jedis.sdiff("s-key-01","s-key-02");
		for (String value : set2) {
			System.out.println(value);
		}
		//差集
		System.out.println("差集内容s-key-02-----s-key-01");
		Set<String> set1 = jedis.sdiff("s-key-02","s-key-01");
		for (String value : set1) {
			System.out.println(value);
		}
		//交集
		Set<String> set3 = jedis.sinter("s-key-02","s-key-01");
		System.out.println("交集内容");
		for (String value : set3) {
			System.out.println(value);
		}
		
	}
	
	/**
	 * SortedSet练习
	 */
	@Test
	public void test4() {
		for (int i = 0; i < 11; i++) {
			jedis.zadd("z-key-01", 10-i, "a"+i);
		}
		
		Set<Tuple> withScores = jedis.zrangeWithScores("z-key-01", 0, -1);
		//升序
		System.out.println("升序");
		for (Tuple tuple : withScores) {
			System.out.println(tuple.getElement()+"     "+tuple.getScore());
		}
		Set<Tuple> withScores2 = jedis.zrevrangeWithScores("z-key-01", 0, 10);
		System.out.println("降序");
		for (Tuple tuple : withScores2) {
			System.out.println(tuple.getElement()+"     "+tuple.getScore());
		}
		jedis.zincrby("z-key-01", 1, "b");
		System.out.println(jedis.zcard("z-key-01"));
	}
}

