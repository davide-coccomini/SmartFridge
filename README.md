SMARTFRIDGE


Documento di analisi

Premessa
SmartFridge è un programma per la gestione degli alimenti del proprio frigorifero. Ogni qualvolta si va a fare la spesa sarà sufficiente inserire i dati degli alimenti acquistati compilando il relativo form e l’applicativo si occuperà di memorizzarli e di fornire statistiche riguardo ciò che è presente nel frigorifero.

Esempi di scenario
1.	IF l’utente compila il form Inserisci alimento e preme il pulsante Inserisci:
1.1.	Il sistema preleva i valori di Nome,Categoria,Quantità,Data di scadenza;
1.2.	Il sistema memorizza e visualizza questi dati nella tabella;
1.3.	Il sistema aggiorna il grafico Alimenti considerando il nuovo alimento inserito;

2.	IF l’utente seleziona un alimento dalla tabella e preme il pulsante elimina:
2.1.	Il sistema elimina l’alimento selezionato premuto dalla tabella;
2.2.	Il sistema aggiorna il grafico Alimenti considerando l’assenza dell’alimento eliminato;
 
3.	IF  l’utente preme due volte sul valore di QUANTITA’ di un elemento nella tabella e preme invio:
3.1.	Il sistema aggiorna la QUANTITA’ per quella riga; 

File di configurazione locale in XML
All’avvio il sistema legge dal file di configurazione i seguenti dati:
-	Stili;
-	L’indirizzo IP del client, l’indirizzo IP e la porta del server di log.
-	Il nome del database, l’user, la password e la porta;


Cache locale degli input
-	Alla chiusura il sistema salva su un file binario tutti i dati inseriti nel form Inserisci alimento;
-	All’avvio il sistema carica dal file binario i dati relativi all’ultima chiusura.

Base di dati
-	Sulla base di dati vengono memorizzate,per ogni alimento inserito, le seguenti informazioni:
-	idAlimento,nome,categoria,quantita,scadenza;

File di log remoto in XML
Il sistema invia una riga di log ad ogni evento:
-	Avvio dell’applicazione
-	Pressione del pulsante inserisci;
-	Pressione del pulsante elimina;
-	Modifica del campo QUANTITA’ della tabella;
-	Termine dell’applicazione
La riga di log contiene l’indirizzo IP del client, la data-ora corrente, il nome dell’evento.










Documento di progetto
BACKEND:
Classe ServerLogEventoXML
•	Lato server per il salvataggio delle log;
Classe ValidatoreXML
•	 Valida una stringa xml;
•	Carica oggetti partendo da xml;
Classe GestoreDB
•	Esegue tutte le query al Database;
•	Gestisce il collegamento col Database;
MIDDLEWARE:
Classe Alimento
•	Contiene le informazioni relative a un dato alimento;
Classe GestoreCache
•	Estrae ed aggiorna i dati del form sulla cache;
Classe ClientLogEventoXML
•	Lato client per il salvataggio delle log;
Classe EventoXML
•	Rappresenta un evento da salvare nelle log;
Classe UnitaGrafico
•	Rappresenta un dato del grafico;


FRONTEND:
Classe AvvioSmartFridge
•	Contiene funzioni per l’avvio dell’applicazione e la costruzione dell’interfaccia;
Classe GraficoAlimenti
•	Contiene funzioni per la gestione del grafico a torta;
Classe ParametriXML
•	Ottiene i parametri di configurazione;
Classe ParametriConfigurazione
•	Contiene i parametri di configurazione necessari per il funzionamento dell’applicativo;
Classe TabellaAlimenti
•	Classe rappresentante l’oggetto tabella contenente tutti gli alimenti nel frigorifero;
Classe CellaTabellaAlimenti
•	Contiene le funzioni che supportano la modifica di un campo quantità;


 





Manuale utente

1. Inserimento di un nuovo alimento:
•	Inserire i dati relativi al nuovo alimento nel modulo nel riquadro;
•	Premere il pulsante inserisci, l’alimento sarà aggiunto al frigorifero e sarà visibile nella tabella sovrastante.
 
 
2. Rimozione di un alimento:
•	Selezionare un alimento dalla griglia sovrastante;
•	Cliccare sul pulsante elimina.

 
3. Modifica della quantità di un alimento:
•	Cliccare due volte sul campo quantità di un elemento presente nel frigorifero:
•	Inserire la nuova quantità e premere invio:





