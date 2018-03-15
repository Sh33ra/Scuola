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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.net.ConnectException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientConnessioneTCP {

    /**
     * @param args the command line arguments
     */
    Socket connection = null;
    String serverAddress;
    int port;
    Gestione gc; 
    static boolean controllo;
    public ClientConnessioneTCP() {
        serverAddress = "localhost";
        port = 2000;
    }

    public static void main(String[] args) throws Exception {
            ClientConnessioneTCP cli=new ClientConnessioneTCP();
            cli.openCon();
                controllo=true;
                while(controllo==true){
            cli.scrivi();
            cli.leggi();
                }
    }

    public void openCon() throws Exception {
        //oggetto da usare per realizzare la connessione TCP
        //nome o IP del server
        //porta del server in ascolto
        //apertura della connessione al server sulla porta specificata
        try {
            gc=new Gestione();

            connection = new Socket(serverAddress, port);
            System.out.println("Connessione aperta");
            System.out.println("Benvenuto");
            //gc.lista();
            //dataOut.close();
            //dataIn.close();
        } catch (ConnectException e) {
            System.err.println("Server non disponibile!");
        } catch (UnknownHostException e1) {
            System.err.println("Errore DNS!");
        } catch (IOException e2) {//
            System.err.println(e2);
            e2.printStackTrace();
        } //chiusura della connnessione
        
    }
    public void closeCon(){
      try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            } catch (IOException e) {
                System.err.println("Errore nella chiusura della connessione!");
            }  
    }
    public void scrivi() throws IOException {
        BufferedReader tastiera=new BufferedReader(new InputStreamReader(System.in));
        DataOutputStream dataOut;
        dataOut = new DataOutputStream(connection.getOutputStream());
        dataOut.writeUTF(tastiera.readLine());
    }
    public void leggi() throws IOException {
        DataInputStream dataIn = new DataInputStream(connection.getInputStream());
        System.out.println(dataIn.readUTF());
    }
}
