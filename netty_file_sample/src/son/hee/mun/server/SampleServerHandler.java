package son.hee.mun.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SampleServerHandler extends ChannelInboundHandlerAdapter {

  int totalSize = 0;

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) {
    System.out.println("Server channelRead!");
    byte[] bytes = (byte[]) msg;

    totalSize += bytes.length;

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
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    System.out.println("Server channelReadComplete!");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    System.out.println("Server exceptionCaught!");
    cause.printStackTrace();
    ctx.close();
  }
}
