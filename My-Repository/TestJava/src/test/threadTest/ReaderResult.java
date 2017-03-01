package test.threadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class ReaderResult implements Runnable { 
//    Calculator c; 
	static int num = 0;
	private static Jedis jedis;//非切片额客户端连接
    private static JedisPool jedisPool;//非切片连接池
    private static ShardedJedis shardedJedis;//切片额客户端连接
    private static ShardedJedisPool shardedJedisPool;//切片连接池
    
    static{
    	initialPool(); 
        initialShardedPool(); 
        shardedJedis = shardedJedisPool.getResource(); 
        jedis = jedisPool.getResource(); 
    }
    
    public ReaderResult() { 
       
    } 
    
    private static void initialPool(){ 
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxActive(20); 
        config.setMaxIdle(5); 
        config.setMaxWait(1000l); 
        config.setTestOnBorrow(false); 
        
        jedisPool = new JedisPool(config,"127.0.0.1",6379);
    }
    
    /** 
     * 初始化切片池 
     */ 
    private static void initialShardedPool() { 
        // 池基本配置 
        JedisPoolConfig config = new JedisPoolConfig(); 
        config.setMaxActive(20); 
        config.setMaxIdle(5); 
        config.setMaxWait(1000l); 
        config.setTestOnBorrow(false); 
        // slave链接 
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
        shards.add(new JedisShardInfo("127.0.0.1", 6379, "master")); 

        // 构造池 
        shardedJedisPool = new ShardedJedisPool(config, shards); 
    } 

	public ShardedJedis getShardedJedis() {
		return shardedJedis;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

    public void run() { 
    	shardedJedis.lpush("thread", String.valueOf(num++));
    	
            /*synchronized (c) { 
                    try { 
                            System.out.println(Thread.currentThread() + "等待计算结果。。。"); 
                            c.wait(); 
                    } catch (InterruptedException e) { 
                            e.printStackTrace(); 
                    } 
                    System.out.println(Thread.currentThread() + "计算结果为：" + c.thread); 
            } */
    } 

    public static void main(String[] args) { 
    	//启动三个线程，分别获取计算结果 
		try {
			ReaderResult rr = new ReaderResult();
			new Thread(rr).start(); 
		} finally {
			System.out.println(shardedJedis.lrange("thread", 0, -1));
		}
		
		/*Timer timer = new Timer();
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println(shardedJedis.lrange("thread", 0, -1));
			}
		};
		timer.schedule(timerTask, 3000);*/
    } 
}