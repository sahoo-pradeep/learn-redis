import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RedisSub {
  private static final Jedis subscriber = new Jedis("localhost", 6379);

  public static void main(String[] args) {
    System.out.println("Subscribing to radio channel");

    subscriber.subscribe(new JedisPubSub() {
      @Override
      public void onMessage(String channel, String message) {
        super.onMessage(channel, message);
        System.out.println("Received: " + message);
      }
    }, "radio");
  }

}
