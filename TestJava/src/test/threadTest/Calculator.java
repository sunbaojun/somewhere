package test.threadTest;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class Calculator extends Thread { 
    int total;
    Thread thread;
    private ShardedJedis shardedJedis;//切片额客户端连接
    private ShardedJedisPool shardedJedisPool;//切片连接池
    
    public Calculator() { 
        initialShardedPool(); 
        shardedJedis = shardedJedisPool.getResource(); 
    } 
    
    /** 
     * 初始化切片池 
     */ 
    private void initialShardedPool() { 
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

	public void setShardedJedis(ShardedJedis shardedJedis) {
		this.shardedJedis = shardedJedis;
	}

	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	public void run() { 
		System.out.println(shardedJedis);
		shardedJedis.lpush("thread", String.valueOf(Thread.currentThread()));
		thread = Thread.currentThread();
            /*synchronized (this) { 
                    for (int i = 0; i < 101; i++) { 
                            total += i; 
                    } 
                  //通知所有在此对象上等待的线程 
                    notifyAll(); 
            } */
         System.out.println(thread);
    } 
}