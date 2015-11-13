package son.hee.mun.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class SampleClientInitializer extends ChannelInitializer<SocketChannel> {

  protected void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline p = ch.pipeline();
    p.addLast(new ByteArrayDecoder());
    p.addLast(new ByteArrayEncoder());
    p.addLast(new SampleClientHandler());
  }

}
