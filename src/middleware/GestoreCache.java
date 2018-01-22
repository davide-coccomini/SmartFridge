package middleware;

import java.io.*;


public class GestoreCache implements Serializable{
    
    private String nome;
    private String categoria;
    private String quantita;
    private String scadenza;
    private boolean valida;
    public GestoreCache(Alimento a){ //(01)
       nome=a.getNome();
       categoria=a.getCategoria();
       quantita=a.getQuantita();
       scadenza=a.getScadenza();
     }
    public GestoreCache(){ //(02)
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("../../cache/cache.bin"))){
            GestoreCache gc = (GestoreCache) ois.readObject();
            nome=gc.nome;
            categoria=gc.categoria;
            quantita=gc.quantita;
            scadenza=gc.scadenza;
            valida=true;     
        }catch(IOException | ClassNotFoundException e){
            valida=false;
            System.out.println("Errore cache"+e.getMessage());
        }
    }
    public void salvaCache(){ //(03)
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../../cache/cache.bin")))
        {
            oos.writeObject(this);
        }catch(Exception e){
            System.out.println("Errore salvataggio dati cache");
        }
    }
    public Alimento leggiCache() { //(04)
        if(valida){
            Alimento a=new Alimento(0,nome,categoria,quantita,scadenza);
            return a;
        }
        else
            return null;
    }
}
//(01): Costruttore per inizializzare i campi della classe quando si è già in possesso di un oggetto Alimento;
//(02): Costruttore per inizializzare i campi della classe quando si ha un oggetto Alimento salvato su un file binario;
//(03): Funzione utilizzata per salvare la cache generando un file binario;
//(04): Funzione utilizzata per leggere la cache salvata precedentemente su un file binario;