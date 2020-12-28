import redis.clients.jedis.Jedis;

public class RedisPub {
  private static final Jedis publisher = new Jedis("localhost", 6379);

  public static void main(String[] args) {
    String message = "hello";

    publisher.publish("radio", message);
  }

}
