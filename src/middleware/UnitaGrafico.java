
package middleware;


public class UnitaGrafico { //(01)
    private final String categoria;
    private final int occorrenze;
    public UnitaGrafico(String c, int o){ //(02)
        categoria=c;
        occorrenze=o;
    }
    public String getCategoria(){ //(03)
        return categoria;
    }
    public int getNumero(){ //(04)
        return occorrenze;
    }
}

//(01): Classe utilizzata a supporto di GraficoAlimenti per rappresentare un dato nel grafico;
//(02): Costruttore che inizializza la categoria e le occorrenze;
//(03): Funzione per la restituzione della categoria;
//(04): Funzione per la restituzione delle occorrenze;
