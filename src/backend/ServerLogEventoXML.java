
package backend;

import java.io.DataInputStream;
import java.net.*;
import java.nio.file.*;



public class ServerLogEventoXML { //(01)

    private final Integer portaServer;
    private ServerLogEventoXML(Integer ps) { //(02)
        portaServer = ps;
    }
    
    private void appendiAFile(String xml) {  //(03)
        ValidatoreXML validatore = new ValidatoreXML("../../xml/evento.xsd",null);
        
        if(!validatore.validaXML(xml)) { 
            return;
        }
        String ls = System.lineSeparator(); 
     
        xml+=ls+ls;
        try {
            if(!Files.exists(Paths.get("../../xml/log.xml")))
                Files.createFile(Paths.get("../../xml/log.xml"));
            Files.write(Paths.get("../../xml/log.xml"),xml.getBytes(),StandardOpenOption.APPEND); 
        } catch(Exception e) {
            System.out.println("Impossibile effettuare la scrittura");
        }
    }

    private void esegui() { //(04)
        while(true) {
            System.out.println("Sono in attesa...");
            try(
                ServerSocket sock= new ServerSocket(portaServer);
                Socket s = sock.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
            ){
                String eventoXML = (String)dis.readUTF(); 
                System.out.println("Ricevuto: "+eventoXML);
                appendiAFile(eventoXML);                
            }catch(Exception ex){
                System.out.println("Errore generico");
                ex.printStackTrace();
                return;
            }
        }   
    }
    public static void main(String[] args) { //(05)
        if(args.length>=1) {
           ServerLogEventoXML sl=new ServerLogEventoXML(Integer.parseInt(args[0])); 
           sl.esegui(); 
        }else{
           System.out.println("Porta mancante");
           System.exit(-1);
        }
     
    }
    
}

//(01): Classe rappresentante il server ricevente le log inviate dal client;
//(02): Costruttore che setta il valore della porta;
//(03): Funzione che, data una stringa XML, la valida secondo il file evento.xsd
//      e, se la validazione è stata effettuata con successo, salva l'elemento sul file di log;
//(04): Funzione per l'esecuzione del server di log che si mette in attesa per l'arrivo delle log;
//(05): Entry-point che prende come argomento un vettore di stringhe contenente la porta del server;