package io.blockio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by fubin on 2019-11-17.
 */
public class WorkerRunnable implements Runnable {
    //与客户端对应的Socket
    protected Socket clientSocket = null;
    //服务端响应的内容
    protected String serverText = null;
    //初始化变量
    public WorkerRunnable(Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText = serverText;
    }
    
    public void run() {
        try {
            //获取输入流接收数据
            InputStream input = clientSocket.getInputStream();
            //获取输出流发送数据
            OutputStream output = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " + this.serverText + " - " + time + "").getBytes());
            output.close();
            input.close();
            System.out.println("Thread name :" + Thread.currentThread().getName() +"   Request processed: " + time);
        } catch (IOException e) {
            // report exception somewhere.
            e.printStackTrace();
        }
    }
}
