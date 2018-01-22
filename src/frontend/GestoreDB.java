
package backend;

import middleware.UnitaGrafico;
import frontend.ParametriXML;
import java.sql.*;
import java.util.*;
import middleware.Alimento;
import middleware.ClientLogEventoXML;

public class GestoreDB{ //(01)
    private static final String queryInserimentoAlimento = "INSERT INTO alimenti(nome,categoria,quantita,scadenza) VALUES(?,?,?,?)";
    private static final String queryModificaAlimento = "UPDATE alimenti SET quantita=? WHERE idAlimento = ?";
    private static final String queryLetturaAlimenti = "SELECT * FROM alimenti";
    private static final String queryRimozioneAlimento = "DELETE FROM alimenti WHERE idAlimento = ?";
    private static final String querySelezioneCategorie = "SELECT categoria,COUNT(*) as occorrenze FROM alimenti GROUP BY categoria";
    private static final String indirizzoDB; 
    private static final String utenteDB;
    private static final String passwordDB;
    
    private GestoreDB() { //(02)
      
    }
    static {
        String nomeDB = "SmartFridge";
        String hostDB = ParametriXML.ottieniParametriConfigurazione().HostnameDB;
       
        int portaDB = ParametriXML.ottieniParametriConfigurazione().PortaDB;
  
        utenteDB = ParametriXML.ottieniParametriConfigurazione().UtenteDB;
        passwordDB = ParametriXML.ottieniParametriConfigurazione().PasswordDB;   
        indirizzoDB = "jdbc:mysql://"+hostDB+":"+portaDB+"/"+nomeDB;
    }
     
   
    public static int inserisciAlimento(Alimento a) {  //(03)
        try(
            Connection con = DriverManager.getConnection(indirizzoDB,utenteDB,passwordDB);
               
            PreparedStatement ps = con.prepareStatement(queryInserimentoAlimento);
        ) {
            ps.setString(1,a.getNome());
            ps.setString(2,a.getCategoria());
            ps.setString(3,a.getQuantita());
            ps.setString(4,a.getScadenza());
            ps.execute();
           
            return -1;
        } catch (SQLException ex) {
            System.out.println("Errore di inserimento di un alimento "+ex.getMessage());
            return -1;
        }        
    }
    
    public static List<Alimento> leggiAlimenti() { //(04)
       
        List<Alimento> lista = new ArrayList<>();
        try (
            Connection con = DriverManager.getConnection(indirizzoDB,utenteDB,passwordDB);                
            PreparedStatement ips = con.prepareStatement(queryLetturaAlimenti);
            ResultSet rs = ips.executeQuery();
        ) {
            while(rs.next()){
                lista.add(new Alimento(
                              rs.getInt("idAlimento"),
                              rs.getString("nome"),
                              rs.getString("categoria"),
                              rs.getString("quantita"),
                              rs.getString("scadenza"))); 
              
            }
          return lista;
        } catch(SQLException ex) {
            System.out.println("Errore di lettura degli alimenti nel frigorifero "+ex.getMessage());
        }
       return lista;
    }
    
    
    public static boolean modificaAlimento(Alimento a) { // (05)
      ClientLogEventoXML.inviaEventoModificaQuantita("SmartFridge","MODIFICA","Campo quantita");
                    
        try (
            Connection connessioneDatabase = DriverManager.getConnection(indirizzoDB,utenteDB,passwordDB);                
            PreparedStatement ps = connessioneDatabase.prepareStatement(queryModificaAlimento);
        ) {
            ps.setString(1,a.getQuantita());
            ps.setInt(2,a.getIdAlimento());
            return ps.executeUpdate() > 0;
        } catch(SQLException ex) {
            System.out.println("Errore di modifica di un alimento");
            return false;
        }
    }
    
    public static boolean rimuoviAlimento(int indice) { // (06)
  
        try (
            Connection con = DriverManager.getConnection(indirizzoDB,utenteDB,passwordDB);                
            PreparedStatement ps = con.prepareStatement(queryRimozioneAlimento);
        ) {
            ps.setInt(1,indice);
            return ps.executeUpdate() > 0;
        } catch(SQLException ex) {
            System.out.println("Errore di rimozione di un alimento");
            return false;
        }
    }
    
    public static List<UnitaGrafico> restituisciOccorrenzeCategorie(){ //(07)
         List<UnitaGrafico> lista = new ArrayList<>();
        try (
            Connection con = DriverManager.getConnection(indirizzoDB,utenteDB,passwordDB);                
            PreparedStatement ips = con.prepareStatement(querySelezioneCategorie);
            ResultSet rs = ips.executeQuery();
        ) {
            while(rs.next()){
                lista.add(new UnitaGrafico(
                              rs.getString("categoria"),
                              rs.getInt("occorrenze")
                         )); 
              
            }
          return lista;
        } catch(SQLException ex) {
            System.out.println("Errore di occorrenze delle categorie nel frigorifero "+ex.getMessage());
        }
       return lista;
    }
}

//(01): Classe utilizzata per gestire le operazioni che necessitano del database;
//(02): Costruttore che crea un'istanza della classe prendendo i parametri per la 
//      connessione dalla classe ParametriXML;
//(03): Funzione utilizzata per l'inserimento di un nuovo alimento nel frigorifero;
//(04): Funzione utilizzata per l'estrapolazione degli alimenti attualmente presenti nel frigorifero;
//(05): Funzione utilizzata per modificare la quantità di un alimento presente nel frigorifero;
//(06): Funzione utilizzata per rimuovere un alimento dal frigorifero;
//(07): Funzione utilizzata per restituire le occorrenze delle categorie degli alimenti nel frigorifero;