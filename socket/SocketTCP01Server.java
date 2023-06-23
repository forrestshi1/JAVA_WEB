package socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 编写服务器和客户端，服务器端在9999端口监听，客户端连接到服务器端口，
 * 发送"hello, server"，再退出。服务器端打印接收到的数据，退出。
 *
 */

public class SocketTCP01Server {
    public static void main(String[] args) throws IOException {
        //思路
        //1. 在本机 的9999端口监听, 等待连接
        //   细节: 要求在本机没有其它服务在监听9999
        //   细节：这个 ServerSocket 可以通过 accept() 返回多个Socket[多个客户端连接服务器的并发]
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务端，在9999端口监听，等待连接..");
        //2. 当没有客户端连接9999端口时，程序会 阻塞, 等待连接
        //   如果有客户端连接，则会返回Socket对象，程序继续

        Socket socket1 = serverSocket.accept();

        System.out.println("服务端 socket =" + socket1.getClass());
        //
        //3. 通过socket.getInputStream() 读取客户端写入到数据通道的数据, 显示
        InputStream inputStream = socket1.getInputStream();
        //4. IO读取
        byte[] buf = new byte[1024];//缓冲区，存储读取到的数据，最多读取1kb
        int readLen = 0;
        while ((readLen = inputStream.read(buf)) != -1) {
            System.out.println(new String(buf, 0, readLen));//根据读取到的实际长度，显示内容.
        }
        //5.关闭流和socket
        inputStream.close();
        socket1.close();
        serverSocket.close();//关闭

    }
}
