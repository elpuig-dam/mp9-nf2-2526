package mp9.nf2.tcp.apunts;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TcpSocketServer {
    static final int PORT = 9090;
    private boolean end = false;

    public void listen(){
        ServerSocket serverSocket=null;
        Socket clientSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while(!end){
                clientSocket = serverSocket.accept();
                System.out.println("Connexió amb: " + clientSocket.getInetAddress());
                TcpThreadServer th = new TcpThreadServer(clientSocket);
                th.start();
            }
            //tanquem el sòcol principal
            if(serverSocket!=null && !serverSocket.isClosed()){
                serverSocket.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public static void main(String[] args) {
        TcpSocketServer tcpSocketServer = new TcpSocketServer();
        tcpSocketServer.listen();
    }
}