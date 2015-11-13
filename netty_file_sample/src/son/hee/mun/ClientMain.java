package son.hee.mun;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import son.hee.mun.client.SampleClientInitializer;

public class ClientMain {

  public static void main(String[] args) {

    EventLoopGroup workerGroup = new NioEventLoopGroup();
    try {
      Bootstrap b = new Bootstrap();
      b.group(workerGroup);
      b.channel(NioSocketChannel.class);
      b.option(ChannelOption.SO_KEEPALIVE, true);
      b.handler(new SampleClientInitializer());

      String ip = "127.0.0.1";
      int port = 8888;
      ChannelFuture f = b.connect(ip, port).sync();
      System.out.println("Client Start : " + ip + ":" + port);
      f.channel().closeFuture().sync();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      workerGroup.shutdownGracefully();
    }
  }
}
