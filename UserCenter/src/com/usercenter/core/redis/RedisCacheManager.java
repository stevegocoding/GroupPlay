package com.usercenter.core.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


public class RedisCacheManager {
    
    private static final Logger               Log = LoggerFactory.getLogger(RedisCacheManager.class);
    
    private volatile static RedisCacheManager redisCacheManager;

    private static JedisPool                  jedisPool;

    /**
     * @throws java.lang.Exception
     */
    public RedisCacheManager() {
        
        ResourceBundle bundle = ResourceBundle.getBundle("redis");

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        
        // 控制一个pool最多有多少个状态为idle的jedis实例
        jedisPoolConfig.setMaxActive(Integer.valueOf(bundle.getString("maxActive")));

        // 最大能够保持空闲状态的对象数
        jedisPoolConfig.setMaxIdle(Integer.valueOf(bundle.getString("maxIdle")));
        
        // 超时时间
        jedisPoolConfig.setMaxWait(Integer.valueOf(bundle.getString("maxWait")));
        
        // 在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
        jedisPoolConfig.setTestOnBorrow(Boolean.valueOf(bundle.getString("testOnBorrow")));
        
        // 在还会给pool时，是否提前进行validate操作
        jedisPoolConfig.setTestOnReturn(Boolean.valueOf(bundle.getString("testOnReturn")));
        
        String host = bundle.getString("host");
        String password = bundle.getString("password");
        int port = Integer.valueOf(bundle.getString("port"));
        int timeout = Integer.valueOf(bundle.getString("timeout"));
        
        // 构造连接池
        if ("".equals(password))
            setJedisPool(new JedisPool(jedisPoolConfig, host, port));
        else {
            setJedisPool(new JedisPool(jedisPoolConfig, host, port, timeout, password));
        }
    }

    public static RedisCacheManager getInstance() {
        if (redisCacheManager == null) {
            synchronized (RedisCacheManager.class) {
                if (redisCacheManager == null) {
                    redisCacheManager = new RedisCacheManager();
                }
            }
        }
        return redisCacheManager;
    }

    public void close() {
        jedisPool.destroy();
    }

    @Override
    protected void finalize() {
        jedisPool.destroy();
    }

    /**
     * 校验key是否存在
     * 
     * @param key
     * @return
     */
    public boolean exists(String key) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
        }
        return false;
    }

    /**
     * 根据key 增加
     * 
     * @param key
     * @param integer
     * @return
     */
    public long incrBy(String key, long integer) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            return jedis.incrBy(key, integer).longValue();
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
        }
        return 0;
    }

    /**
     * 根据key 减少
     * 
     * @param key
     * @param integer
     * @return
     */
    public long decrBy(String key, long integer) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            return jedis.decrBy(key, integer).longValue();
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
        }
        return 0;
    }

    /**
     * 根据key 删除
     * 
     * @param key
     * @param integer
     * @return
     */
    public long del(String... keys) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            return jedis.del(keys).longValue();
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
        }
        return 0;
    }

    /**
     * 设置String对象到队列中
     * @param key
     * @param value
     */
    public boolean setString(String key, String value) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
            return false;
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
        }
        return true;
    }

    /**
     * 从队列中获取一个String对象
     * @param key
     * @return
     */
    @SuppressWarnings("finally")
    public String getString(String key) {
        Jedis jedis = null;
        String retString = "";
        try {
            // 从池中获取一个jedis实例
            jedis = jedisPool.getResource();
            retString = jedis.get(key);
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
            return retString;
        }
    }

    /**
     * 设置一段字节流到队列中
     * @param key
     * @param value
     */
    public void setBytes(String key, byte[] value) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            jedis.set(key.getBytes(), value);
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 从队列中获取一段字节流
     * @param key
     * @return
     */
    @SuppressWarnings("finally")
    public byte[] getBytes(String key) {
        Jedis jedis = null;
        byte[] retString = null;
        try {
            // 从池中获取一个jedis实例
            jedis = jedisPool.getResource();
            retString = jedis.get(key.getBytes());
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
            return retString;
        }
    }

    /**
     * 设置一个对象到队列中
     * @param key
     * @param object
     */
    public void setObject(String key, Object object) {
        Jedis jedis = null;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            jedis.set(key.getBytes(), serialize(object));
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 从队列中获取一个对象
     * @param key
     * @param object
     */
    @SuppressWarnings("finally")
    public Object getObject(String key) {
        Jedis jedis = null;
        Object ret = "";
        try {
            // 从池中获取一个jedis实例
            jedis = jedisPool.getResource();
            ret = deserialize(jedis.get(key.getBytes()));
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
            return ret;
        }
    }

    /**
     * 设置一个对象字符串数组到队列头部中，如果list不存在，则创建一个list
     * @param key
     * @param object
     * @return 返回插入后list的整体长度
     */
    @SuppressWarnings("finally")
    public long lpush(String key, String... strings) {
        Jedis jedis = null;
        long ret = -1;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            ret = jedis.lpush(key, strings).longValue();
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
            return ret;
        }
    }

    /**
     * 设置一个对象字符串数组到队列尾部中，如果list不存在，则创建一个list
     * @param key
     * @param object
     * @return 返回插入后list的整体长度
     */
    @SuppressWarnings("finally")
    public long rpush(String key, String... strings) {
        Jedis jedis = null;
        long ret = -1;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            ret = jedis.rpush(key, strings).longValue();
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
            return ret;
        }
    }

    /**
     * 设置一个对象字符串数组到队列中，如果list不存在，不会创建一个list
     * @param key
     * @param object
     * @return 返回插入后list的整体长度
     */
    @SuppressWarnings("finally")
    public long lpushx(String key, String string) {
        Jedis jedis = null;
        long ret = -1;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            ret = jedis.lpushx(key, string).longValue();
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
            return ret;
        }
    }

    /**
     * 设置一个对象字符串数组到队列尾部中，如果list不存在，不会创建一个list
     * @param key
     * @param object
     * @return 返回插入后list的整体长度
     */
    @SuppressWarnings("finally")
    public long rpushx(String key, String string) {
        Jedis jedis = null;
        long ret = -1;
        // 从池中获取一个jedis实例
        try {
            jedis = jedisPool.getResource();
            ret = jedis.rpushx(key, string).longValue();
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
            return ret;
        }
    }

    /**
     * 从list头部中获取一个对象
     * @param key
     * @param object
     */
    @SuppressWarnings("finally")
    public String lpop(String key) {
        Jedis jedis = null;
        String ret = null;
        try {
            // 从池中获取一个jedis实例
            jedis = jedisPool.getResource();
            ret = jedis.lpop(key);
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
            return ret;
        }
    }

    /**
     * 从list尾部中获取一个对象
     * @param key
     * @param object
     */
    @SuppressWarnings("finally")
    public String rpop(String key) {
        Jedis jedis = null;
        String ret = null;
        try {
            // 从池中获取一个jedis实例
            jedis = jedisPool.getResource();
            ret = jedis.rpop(key);
        } catch (Exception e) {
            Log.error("Redis link Exception： ", e);
            // 销毁对象
            jedisPool.returnBrokenResource(jedis);
        } finally {
            // 还会到连接池
            jedisPool.returnResource(jedis);
            return ret;
        }
    }

    /**
     * 获取连接池对象
     * @return
     */
    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * 设置连接池对象
     * @return
     */
    public static void setJedisPool(JedisPool jedisPool) {
        RedisCacheManager.jedisPool = jedisPool;
    }

    
    @SuppressWarnings("finally")
    public static byte[] serialize(Object o) throws Exception {
        byte[] ret=null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            ObjectOutputStream oos = new ObjectOutputStream(baos); 
            oos.writeObject(o); // write this object 
            ret = baos.toByteArray(); 
            oos.flush();
            oos.close();
        } catch (Exception e) {
            System.out.println("Gearman serialize failed");
        }finally{
            return ret;
        }
    }

    public static Object deserialize(byte[] bytes) throws IOException,
            ClassNotFoundException {
        // To build a kind of input stream, the underlying input stream in bytes
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bais);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }
}
