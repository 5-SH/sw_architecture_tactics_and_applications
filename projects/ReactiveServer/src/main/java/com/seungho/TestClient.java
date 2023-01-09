package com.seungho;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClient {
  public static void main(String[] args) {
    System.out.println("Client ON " + 9993 + " port");
    int messageCount = 0;
    while (true) {
      try {
        String message;

        Socket socket = new Socket("127.0.0.1", 9993);
        OutputStream out = socket.getOutputStream();
        message = ("TEST" + " hello world" + ++messageCount);
        out.write(message.getBytes());

        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[50];
        inputStream.read(buffer);

        System.out.println("Received message : " + new String(buffer));

        Thread.sleep(1000);
      } catch (UnknownHostException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
