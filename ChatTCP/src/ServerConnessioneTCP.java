/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 *
 * @author Daniel
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerConnessioneTCP {
//attributi
    //dichiarazione
    
    //costruttore
    //inizializzazione
    
    ServerSocket sSocket = null;
    Socket connection;
    static boolean controllo;
    int port;
    public ServerConnessioneTCP() throws IOException{
         port=2000;
        sSocket = new ServerSocket(port);
       
    }
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        //variabili
        ServerConnessioneTCP ser = new ServerConnessioneTCP();
        ser.openCon();
        controllo=true;
        while(controllo==true){
        ser.leggi();
        ser.scrivi();
        }
        // porta del server maggiore di 1024 
        
        //oggetto ServerSocket necessario per accettare richieste dal client
        
        //oggetto da usare per realizzare la connessione TCP
        
    }
    public void openCon(){
        
            try {
                // il server si mette in ascolto sulla porta voluta
                
                System.out.println("In attesa di connessioni!");
                //si è stabilita la connessione
                connection = sSocket.accept();
                System.out.println("Connessione stabilita!");
                System.out.println("Socket server: " + connection.getLocalSocketAddress());
                System.out.println("Socket client: " + connection.getRemoteSocketAddress());

                //dataIn.close();
                //dataOut.close();
            } catch (IOException e) {
                System.err.println("Errore di I/O!");
            }

            //chiusura della connessione con il client
            
    }

    public void closeCon(){
        try {
                if (sSocket != null) {
                    sSocket.close();
                }
            } catch (IOException ex) {
                System.err.println("Errore nella chiusura della connessione!");
            }
            System.out.println("Connessione chiusa!");
        }
    public void scrivi() throws IOException {
        
        BufferedReader tastiera=new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dataOut;
        dataOut = new DataOutputStream(connection.getOutputStream());
        dataOut.writeUTF(tastiera.readLine());
    }
    public void leggi() throws IOException {
        DataInputStream dataIn;
        dataIn = new DataInputStream(connection.getInputStream());
        System.out.println(dataIn.readUTF());
    }
}
