import java.util.Map;
import java.util.Set;
import redis.clients.jedis.Jedis;

public class RedisOperation {

  private static final Jedis jedis = new Jedis("localhost", 6379);

  public static void main(String[] args) {
    //strings();
    //lists();
    //sets();
    //hashes();
    //sortedSet();
  }

  private static void strings() {
    jedis.set("name", "Pradeep");
    String val = jedis.get("name");
    System.out.println("Redis get(): " + val);
  }

  private static void lists() {
    //LPUSH -> push to head (to a queue). RPUSH -> push to tail
    jedis.lpush("queue.tasks", "first", "second");
    jedis.lpush("queue.tasks", "third");

    long length = jedis.llen("queue.tasks");
    System.out.println("Length: " + length);

    //RPOP -> pop from right
    String popped = jedis.rpop("queue.tasks");
    System.out.println("Popped from right: " + popped);
  }

  private static void sets() {
    jedis.sadd("places", "mumbai");
    jedis.sadd("places", "mumbai");
    jedis.sadd("places", "bangalore");
    jedis.sadd("places", "delhi");

    Set<String> res = jedis.smembers("places");
    System.out.println(res);

    boolean mumbaiExists = jedis.sismember("places", "mumbai");
    System.out.println("mumbai exists: " + mumbaiExists);
  }

  private static void hashes() {
    jedis.hset("user", "name", "pradeep");
    jedis.hset("user", "age", "26");

    String name = jedis.hget("user", "name");
    System.out.println("Name is: " + name);

    Map<String, String> user = jedis.hgetAll("user");
    System.out.println("User: " + user);
  }

  private static void sortedSet() {
    jedis.zadd("players", 3, "pradeep");
    jedis.zadd("players", 1, "john");
    jedis.zadd("players", 2, "mark");

    Set<String> playersFromTo = jedis.zrange("players", 0, 0);
    System.out.println("First (lowest score): " + playersFromTo);

    //reverse range
    Set<String> reversePlayers = jedis.zrevrange("players", 0, 0);
    System.out.println("First (highest score) : " + reversePlayers);

    //ranking is 0 based
    long rankPradeep = jedis.zrevrank("players", "pradeep");
    System.out.println("Pradeep ranking: " + rankPradeep);
  }

}
