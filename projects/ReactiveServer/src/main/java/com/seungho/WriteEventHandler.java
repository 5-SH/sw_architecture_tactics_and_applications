package com.seungho;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class WriteEventHandler implements EventHandler {

  private Selector demultiplexer;

  public WriteEventHandler(Selector demultiplexer) {
    this.demultiplexer = demultiplexer;
  }

  @Override
  public void handleEvent(SelectionKey handle) throws Exception {
    SocketChannel socketChannel = (SocketChannel) handle.channel();
    ByteBuffer inputBuffer = (ByteBuffer) handle.attachment();

    Charset charset = Charset.defaultCharset();

    byte[] buffer = new byte[inputBuffer.limit()];
    inputBuffer.get(buffer);
    String outString = "[Echo] " + new String(buffer);
    ByteBuffer outputBuffer = charset.encode(outString);

    socketChannel.write(outputBuffer);
    inputBuffer.clear();
    socketChannel.close();
  }
}
