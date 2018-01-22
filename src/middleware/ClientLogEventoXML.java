
package middleware;
import frontend.ParametriXML;
import com.thoughtworks.xstream.XStream;
import java.net.Socket;
import java.io.DataOutputStream;
import java.time.LocalDateTime;

public class ClientLogEventoXML extends Thread{ //(01)
    private final EventoXML evento;
    private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"; 
    private ClientLogEventoXML(EventoXML e) {
        evento = e;
        evento.IPClient = ParametriXML.ottieniParametriConfigurazione().IPClientLogXML;        
    }
      
    private String serializzaEventoInXML() { //(02)
        String stringaXml;
        XStream xstream = new XStream();
        xstream.alias("Evento", EventoXML.class);
        xstream.useAttributeFor(LocalDateTime.class,"time");
        stringaXml = XML_HEADER+System.lineSeparator()+xstream.toXML(evento);
        return stringaXml;
    }
     @Override
    public void run() { //(03)
        String indirizzo = ParametriXML.ottieniParametriConfigurazione().IPServerLogXML;
        Integer porta = ParametriXML.ottieniParametriConfigurazione().PortaServerLogXML;
        try (
            Socket invioXML = new Socket(indirizzo,porta);
            DataOutputStream os = new DataOutputStream(invioXML.getOutputStream());
        ) {
            os.writeUTF(serializzaEventoInXML());
        } catch(Exception ex) {
            System.out.println("Invio del log fallito");
        }  
    }
    public static void inviaEventoClickBottone(String applicazione,String componente,String tipo) { //(04)
        EventoXML e = new EventoXML(applicazione,tipo,componente,"");
        ClientLogEventoXML client=(new ClientLogEventoXML(e));
        client.start();
    }
   
    public static void inviaEventoModificaQuantita(String applicazione,String tipo, String componente){ //(05)
        EventoXML e = new EventoXML(applicazione,tipo,componente,"");
        ClientLogEventoXML client=(new ClientLogEventoXML(e));
        client.start();
    }
    public static void inviaEventoApplicazione(String applicazione,boolean evento) { //(06)
        String stato = (evento)?"AVVIO":"CHIUSURA";
        EventoXML e = new EventoXML(applicazione,stato,null,"");
        (new ClientLogEventoXML(e)).start();
    }
}

//(01): Classe rappresentate il lato client per la memorizzazione delle log;
//(02): Funzione che compone la stringa XML da inviare;
//(03): Funzione che avvia un socket e uno stream di output per l'invio delle log;
//(04): Funzione utilizzata per l'invio del log relativo al click di un bottone;
//(05): Funzione utilizzata per l'invio del log relativo alla modifica del campo quantità di un alimento;
//(06): Funzione utilizzata per l'invio del log relativo all'apertura e alla chiusura dell'applicazione;