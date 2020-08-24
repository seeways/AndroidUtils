package com.jty.myutils.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketUtils {

    /**
     * getSocketClient(String host, int port)
     *
     * @param h The server host.
     * @param p The port specified with the server.
     * @return String server's msg
     * @throws IOException It's socket throws exception.
     */
    public String getSocketClient(String h, int p, String msg) throws IOException {

        String host = "127.0.0.1";
        int port = 10086;

        if (null != h && !h.equals("")) {
            host = h;
            port = p;
        }

        // create a connection with the server
        Socket socket = new Socket(host, port);

        // create the outputStream using the socket object
        OutputStream outputStream = socket.getOutputStream();
        String message = "No message!";
        if (msg != null && !msg.equals("")) {
            message = msg;
        }

        outputStream.write(message.getBytes(StandardCharsets.UTF_8));

        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len;
        StringBuilder sb = new StringBuilder();
        while ((len = inputStream.read(bytes)) != -1) {
            //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
            sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
        }

        inputStream.close();
        outputStream.close();
        socket.close();

        return sb.toString();
    }

    public static void SocketServerStart(final int port, final MessageCallBack callBack) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                String msg = "No message.";
                while (null != msg && !msg.equals("")) {
                    msg = new SocketUtils().getSocketServer(port);
                    callBack.message(msg);

                    //EventBus.getDefault().post(msg);
                }

            }

        }).start();
    }
    MessageCallBack MessageCallBack;
    private void setMsgCallBack(MessageCallBack MessageCallBack) {
        this.MessageCallBack = MessageCallBack;
    }

    public interface MessageCallBack {
        void message(String msg);
    }

    private String getSocketServer(int port) {
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("waiting...");

            Socket socket = server.accept();

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            //只有当客户端关闭它的输出流的时候，服务端才能取得结尾的-1
            while ((len = inputStream.read(bytes)) != -1) {
                // 注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len, StandardCharsets.UTF_8));
            }

            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("I got the message.".getBytes(StandardCharsets.UTF_8));

            inputStream.close();
            outputStream.close();
            socket.close();
            server.close();

            return sb.toString();
        } catch (BindException e) {
            System.out.println("端口使用中....");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}
