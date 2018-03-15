
import java.io.*;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class Gestione {
    String stato;
    String scelta;
    BufferedReader tastiera;
    Gestione g;
    ClientConnessioneTCP cli;
    Socket connection;
    public void Gestione(Socket connection){
        cli=new ClientConnessioneTCP();
        this.connection=connection;
    }
    public void lista()throws Exception{
        g=new Gestione();
        System.out.println("Lista comandi");
        System.out.println(":autore *nome* ==> Per impostare il nome dell'autore");
        System.out.println(":stato ==> Per cambiare lo stato attuale(online/offline)");
        System.out.println(":smile ==> Per stampare un emoticon");
        System.out.println(":echo ==> Per rinviare l'ultimo messaggio ricevuto");
        System.out.println(":end ==> Per terminare la connessione");
        g.scrivi();
    }
    public void scrivi()throws Exception{
        tastiera=new BufferedReader(new InputStreamReader(System.in));
        String app=tastiera.readLine();
        DataOutputStream dataOut = new DataOutputStream(connection.getOutputStream());
        dataOut.writeUTF(app);
        dataOut.flush();
        
        g=new Gestione();
        if(app.equals(":menu")){
        g.lista();
                
        }
    }
    public void leggi()throws Exception{
        DataInputStream dataIn = new DataInputStream(connection.getInputStream());
        System.out.println(dataIn.readUTF());
    }
    public void autore()throws Exception{
        
    }
    public void linea()throws Exception{
        
    }
    public void smile()throws Exception{
        //String str = "An 😀awesome 😃string with a few 😉emojis!";
        //String result = EmojiParser.parseToAliases(str);
        //System.out.println(result);
    }
    public void echo()throws Exception{
        
    }
    public void end()throws Exception{
        try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Connessione chiusa!");
                }
            } catch (IOException e) {
                System.err.println("Errore nella chiusura della connessione!");
            }  
    }
    }
    

    

