package son.hee.mun.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class SampleClientHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    System.out.println("Client channelActive!");

    String filePath = "C:/Temp/sample001.mp4";
    File file = new File(filePath);
    InputStream is = null;
    try {
      is = new FileInputStream(file);
      byte[] bytes = IOUtils.toByteArray(is);
      System.out.println("bytes 사이즈 = " + bytes.length);

      ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
      ctx.writeAndFlush(byteBuf);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    byte[] bytes = new byte[10];
    ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
    ctx.writeAndFlush(byteBuf);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("Client channelRead!");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    System.out.println("Client exceptionCaught!");
    cause.printStackTrace();
    ctx.close();
  }
}
