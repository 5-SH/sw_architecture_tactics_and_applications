package com.seungho;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class ReadEventHandler implements EventHandler {

  private Selector demultiplexer;
  private ByteBuffer inputBuffer = ByteBuffer.allocate(2048);

  public ReadEventHandler(Selector demultiplexer) {
    this.demultiplexer = demultiplexer;
  }

  @Override
  public void handleEvent(SelectionKey handle) throws Exception {
    SocketChannel socketChannel = (SocketChannel) handle.channel();

    socketChannel.read(inputBuffer);

    inputBuffer.flip();

    byte[] buffer = new byte[inputBuffer.limit()];
    inputBuffer.get(buffer);
    System.out.println("Received message from client : " + new String(buffer));
    inputBuffer.flip();

    socketChannel.register(demultiplexer, SelectionKey.OP_WRITE, inputBuffer);
  }
}
