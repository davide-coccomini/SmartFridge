package backend;
import com.thoughtworks.xstream.XStream;
import java.io.*;
import java.nio.file.*;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import org.xml.sax.*;
import org.w3c.dom.Document;

public class ValidatoreXML {
    private final String percorsoFileXML;
    private final String percorsoFileXSD;
    private final XStream stream;
    public ValidatoreXML(String percorsoXSD,String percorsoXML) {
        percorsoFileXSD = percorsoXSD;
        percorsoFileXML = percorsoXML;
        stream = new XStream();
    }
    
    public boolean validaXML(String xml) { //(01)
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document docXML =  builder.parse(new InputSource(new StringReader(xml))); 
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(new StreamSource(new File(percorsoFileXSD)));
            schema.newValidator().validate(new DOMSource(docXML));
            return true;
        } catch(SAXException ex) {
            System.out.println("Errore di validazione"+ex.getMessage());
            return false;
        } catch(IOException | ParserConfigurationException ex) {
            System.out.println("Errore"+ex.getMessage());
            return false;
        }
    }
    
    public Object caricaOggettoDaXML() { //(02)
        try {
            String fileXML = new String(Files.readAllBytes(Paths.get(percorsoFileXML)));
            if(!validaXML(fileXML))
                return null;
            return stream.fromXML(fileXML);
        } catch(Exception e) {
            System.out.println("Impossibile caricare XML"+e.getMessage());
            return null;
        }     
    }
    
    
    public XStream getStreamXML() { //(03)
        return stream;
    }
}


//    (01) Funzione che valida una stringa XML;.
//    (02) Funzione che partendo da una stringa XML, crea un oggetto;
//    (03) Funzione che restituisce l'oggetto XStream;

