package mp9.nf2.tcp.apunts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TcpThreadServer extends Thread {
    private Socket clientSocket;
    private Scanner sc = new Scanner(System.in);

    public TcpThreadServer(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        boolean farewellMessage=false;
        String clientMessage="client connectat...";
        BufferedReader in=null;
        PrintStream out=null;
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out= new PrintStream(clientSocket.getOutputStream());
            System.out.println("Canals d'e/s establerts...");
            do{
                //processem el missatge del client i generem la resposta. Si
                //clientMessage és buida generarem el missatge de benvinguda
                String dataToSend = processData(clientMessage);
                out.println(dataToSend);
                out.flush();
                System.out.println("waiting...");
                clientMessage=in.readLine();
                farewellMessage = isFarewellMessage(clientMessage);
            }while((clientMessage) != null && !farewellMessage);
        } catch (IOException ex) {
            Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeClient(clientSocket);

    }

    private String processData(String data) {
        System.out.println("Clientt$ " + data);
        System.out.print("$ ");
        return sc.nextLine();
    }

    private boolean isFarewellMessage(String msg) {
        if(msg.equals("bye")) return true;
        else return false;
    }

    private void closeClient(Socket clientSocket){
        //si falla el tancament no podem fer gaire cosa, només enregistrar
        //el problema
        try {
            //tancament de tots els recursos
            if(clientSocket!=null && !clientSocket.isClosed()){
                if(!clientSocket.isInputShutdown()){
                    clientSocket.shutdownInput();
                }
                if(!clientSocket.isOutputShutdown()){
                    clientSocket.shutdownOutput();
                }
                clientSocket.close();
            }
        } catch (IOException ex) {
            //enregistrem l'error amb un objecte Logger
            Logger.getLogger(TcpSocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
