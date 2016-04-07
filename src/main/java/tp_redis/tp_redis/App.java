package tp_redis.tp_redis;

import java.util.List;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
//    	example1();
//    	example2();
//    	example3();
    	example4();
    }
    
    private static void example1() {
    	Jedis jedis = new Jedis("localhost");
		jedis.set("foo","bar");
		String value = jedis.get("foo");
		System.out.println(value);
    }
    
    private static void example2() {
    	Jedis jedis = new Jedis("localhost");
    	System.out.println(jedis.get("counter"));
    	jedis.incr("counter");
    	System.out.println(jedis.get("counter"));
    }
    
    private static void example3() throws InterruptedException {
    	String cacheKey = "cachekey";
    	Jedis jedis = new Jedis("localhost");
    	// adding a new key
    	jedis.set(cacheKey, "cached value");
    	// setting the ttl in seconds
    	jedis.expire(cacheKey, 15);
    	// getting the remaining ttl
    	System.out.println("TTL: "+ jedis.ttl(cacheKey));
    	Thread.sleep(1000);
    	System.out.println("TTL: "+ jedis.ttl(cacheKey));
    	// getting the cache value
    	System.out.println("Cached value: "+ jedis.get(cacheKey));
    	
    	// wait for the TTL finishs
    	Thread.sleep(15000);

    	// trying to get the expired key
    	System.out.println("Expired Key:" + jedis.get(cacheKey));
    }
    
    private static void example4() {
    	String cacheKey = "languages";
		Jedis jedis = new Jedis("localhost");
		// Adding a set as value
		jedis.sadd(cacheKey, "Java");
		jedis.sadd(cacheKey, "C#");
		jedis.sadd(cacheKey, "Python"); // SADD
		// Getting all values in the set: SMEMBERS
		System.out.println("Languages: " + jedis.smembers(cacheKey));
		// Adding new values
		jedis.sadd(cacheKey, "Java");
		jedis.sadd(cacheKey, "Ruby");
		// Getting the values... it doesn't allow duplicates
		System.out.println("Languages: " + jedis.smembers(cacheKey));
		
		String cacheKey2 = "liste";
		jedis.rpush(cacheKey2, "toto");
		jedis.rpush(cacheKey2, "titi");
		jedis.rpush(cacheKey2, "tutu");
		jedis.rpush(cacheKey2, "toi");
		jedis.rpush(cacheKey2, "lui");
		jedis.rpush(cacheKey2, "elle");
//		System.out.println(jedis.lrange(cacheKey2, 1, 1));
		
		int pas = 2;
		for (int i=0;i<cacheKey2.length();i= i+pas) {
			System.out.println(subEx(i, pas, cacheKey2));
			System.out.println("----------");
		}
    }
    
    private static List<String> subEx(int start, int nbItems, String cacheKey2) {
		Jedis jedis = new Jedis("localhost");
    	List<String> id_list = jedis.lrange(cacheKey2,start,start+nbItems-1);
    	return id_list;
    }
}
