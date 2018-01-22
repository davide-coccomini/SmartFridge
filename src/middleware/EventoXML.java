
package middleware;


import java.io.*;
import java.text.*;
import java.util.*;

public class EventoXML implements Serializable { //(01)
    public final String tipoEvento;
    public final String nomeApplicazione;
    public final String nomeComponente;
    public String IPClient;
    public String orario;
    
    public EventoXML(String applicazione,String t,String componente,String ip) { //(02)
        tipoEvento = t;
        nomeApplicazione = applicazione;
        nomeComponente = componente;
        IPClient = ip;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy 'alle' HH:mm:ss z");
        Date today = Calendar.getInstance().getTime();
        orario = df.format(today);
    }
}

//(01): Classe che rappresenta un evento XML da mandare al server di log;
//(02): Costruttore che inizializza i campi di un oggetto EventoXML;
