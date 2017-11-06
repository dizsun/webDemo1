package util;
import java.util.HashMap;
import java.util.HashSet;

import redis.clients.jedis.Jedis;

public class RedisUtil
{

    public void addHashMap(String key, HashMap<String,String> map)
    {
        Jedis  redis = new Jedis ("127.0.0.1",6379);//连接redis
        redis.hmset(key, map);
        redis.close();
    }
    public HashSet queryHashMapByKey(String key)
    {
        Jedis  redis = new Jedis ("127.0.0.1",6379);//连接redis
        //return (HashSet) redis.hkeys(key);
        HashSet result = (HashSet) redis.hvals(key);
        redis.close();
        return result;
    }

    public String queryString(String key)
    {
        Jedis  redis = new Jedis ("127.0.0.1",6379);//连接redis
        String value = redis.get(key);
        redis.close();
        return value;
    };
    public void addString(String key,String value)
    {
        Jedis  redis = new Jedis ("127.0.0.1",6379);//连接redis
        //  redis.auth("redis");//验证密码
        //系统中所有key：
        /*Set keys = redis.keys("*");
        Iterator t1=keys.iterator() ;
        while(t1.hasNext()){
            Object obj1=t1.next();
            System.out.println(obj1);
        }*/
        //DEL 移除给定的一个或多个key。如果key不存在，则忽略该命令。
        //redis.del("key");
        //expire 设置Key的过期时间(以秒为单位)
//        redis.expire(key, 3600*24);
        //TTL 返回给定key的剩余生存时间(time to live)(以秒为单位)
        //redis.ttl("key");

        //PERSIST key 移除给定key的生存时间。
        //redis.persist("key");

        //EXISTS 检查给定key是否存在。
        boolean flag = redis.exists(key);
        if(!flag){
            redis.set(key, value);
            redis.expire(key, 3600);
            redis.close();
        }
    }
    public boolean checkKey(String key){
        Jedis  redis = new Jedis ("127.0.0.1",6379);//连接redis
        boolean flag = redis.exists(key);
        redis.close();
        return flag;
    }
    public void removeKey(String key){
        Jedis  redis = new Jedis ("127.0.0.1",6379);//连接redis
        redis.del(key);
        redis.close();
    }
}