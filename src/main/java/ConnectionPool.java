import java.time.Duration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class ConnectionPool {

  public static final JedisPool jedisPool =
      new JedisPool(buildPoolConfig(), "localhost", 6379);

  private static JedisPoolConfig buildPoolConfig() {
    //copy pasted
    JedisPoolConfig poolConfig = new JedisPoolConfig();
    poolConfig.setMaxTotal(128);
    poolConfig.setMaxIdle(128);
    poolConfig.setMinIdle(16);
    poolConfig.setTestOnBorrow(true);
    poolConfig.setTestOnReturn(true);
    poolConfig.setTestWhileIdle(true);
    poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
    poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
    poolConfig.setNumTestsPerEvictionRun(3);
    poolConfig.setBlockWhenExhausted(true);
    return poolConfig;
  }

  public static void main(String[] args) {
    try (Jedis jedis = jedisPool.getResource()) {
      jedis.set("car", "Audi");
      System.out.println(jedis.get("car"));
    } catch (Exception e) {
      System.err.println(e);
    }
  }

}
