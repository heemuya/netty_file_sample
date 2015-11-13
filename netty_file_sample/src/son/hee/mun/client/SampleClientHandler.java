package son.hee.mun.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SampleClientHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    System.out.println("Client channelActive!");

    byte[] bytes = new byte[10];
    ByteBuf byteBuf = Unpooled.wrappedBuffer(bytes);
    ctx.writeAndFlush(byteBuf);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("Client channelRead!");

    byte[] bytes = (byte[]) msg;

    System.out.println("파일 사이즈 = " + bytes.length);

    String filePath = "C:/Temp/save001.mp4";
    File file = new File(filePath);
    FileOutputStream fos;
    try {
      fos = new FileOutputStream(file, true);
      fos.write(bytes);
      fos.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    System.out.println("Client exceptionCaught!");
    cause.printStackTrace();
    ctx.close();
  }
}
