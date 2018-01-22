
package middleware;

import javafx.beans.property.*;

public class Alimento{ //(01)
    public final int idAlimento;
    public final SimpleStringProperty nome;
    public final SimpleStringProperty categoria;
    public SimpleStringProperty quantita;
    public final SimpleStringProperty scadenza; 
    
    public Alimento(int nuovoId,String nuovoNome,String nuovaCategoria,String nuovaQuantita,String nuovaScadenza) //(02)
    {
     this.idAlimento=nuovoId;
     this.nome=new SimpleStringProperty(nuovoNome);
     this.categoria=new SimpleStringProperty(nuovaCategoria);
     this.quantita=new SimpleStringProperty(nuovaQuantita);
     this.scadenza=new SimpleStringProperty(nuovaScadenza);
    }
    @Override
     public String toString() { return getNome();}
     public int getIdAlimento() {return idAlimento;}
     public String getNome() {return nome.get();}
     public String getCategoria() {return categoria.get();}
     public String getQuantita() {return quantita.get();}
     public String getScadenza() {return scadenza.get();}
     
     public void setQuantita(String q){
         quantita=new SimpleStringProperty(q);
     }
}

//(01): Classe bean che rappresenta la tabella Alimento del database
//(02): Costruttore per la classe Alimento che inizializza i vari parametri di un oggetto Alimento

