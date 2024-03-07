# Web Application per GAS

Sono parte di un GAS che ancora gestisce molte cose via mail e con appunti manuali.

<ins>Capita</ins>, ad esempio, che un gasista faccia un ordine e poi cerchi di contattare (via mail o tel) il
gestore dell' ordine per sapere se la sua ordinazione è confermata o meno 😱

<ins>Capita</ins> che il gestore di un ordine si faccia mandare delle mail e registri tutto su un foglio
per poi perdere o l' uno o le altre e debba chiedere nuovamente ai gasisti di ordinare 😱

<ins>Capita</ins>... insomma ho reso l' idea.

**Per questo** sviluppare un' applicazione, via web, che permetta di semplificare gli ordini e risolvere problemi come quelli elencati sopra mi pare una buona idea. <br>
L' applicazione si concentra esclusivamente sul GAS in questione, perciò non pretende di essere utilizzabile anche da altri GAS. E' una dotazione interna.

## Cosa succede

Di solito le cose funzionano così: 
 - A.  qualche volenteroso(aka *Gestore*) si occupa di contattare un fornitore che conosce 
 - B.  si fa dare una disponibilità di prodotti e i relativi costi
 - C.  stabilisce una data di consegna della merce
 - D.  organizza queste informazioni in un excel (online o meno)
 - E.  stabilisce una data finale per effettuare gli ordini
 - F.  invia mail a tutti i gasisti pregandoli di fare un ordine entro la data limite
 - G.  finiti gli ordini li raccoglie e li invia al fornitore
 - H. si prepara a ricevere la consegna e smistare i prodotti

Per i punti A,B,H l' applicazione non serve a niente. Per tutti gli altri invece sì.

## Cosa dovrebbe succedere

Ecco come potrebbero funzionare invece le cose:
 - A. qualche volenteroso si occupa di contattare un fornitore che conosce 
 - B.  si fa dare una disponibilità di prodotti e i relativi costi 
 - C. nell' applicazione crea un ordine inserendo i prodotti disponibili e una data in cui termina l' ordine.
 - D. i gasisti ricevono in automatico una mail che li informa che è presente un nuovo ordine. Se qualcuno non legge la mail, ma si collega all' applicazione, vede comunque gli ordini aperti al momento.
 - E. il gasista effettua l' ordine dall' applicazione
 - F. il gestore vede il riepilogo di tutti gli ordini effettuati con i totali già calcolati e può usare questo per inviarlo al fornitore
 - G. il gestore si prepara a ricevere la consegna e smistare i prodotti



## Attori

No il teatro non c' entra. Ma per capire come far funzionare un' applicazione è necessario prevedere
gli attori coinvolti. Chiarisco subito il mistero individuando 3 attori principali:
 - Amministratori
 - Gestori
 - Gasisti

*Perché?* E' necessario capire cosa può fare la persona che entra in questa applicazione, ed è bene che non tutti possano fare determinate operazioni. Nell' elenco che segue ci sono le possibilità di ogni singolo attore. L' amministratore, oltre alle sue operazioni, può compiere anche quelle dei gestori e dei gasisti,
il gestore quelle dei gasisti e il gasista solo le sue proprie.

**1. Amministratore** <br>
L' amministratore è colei (o colui) che tutto può e vede (parlo solo del GAS naturalmente).Infatti può:
 - vedere l' elenco dei gasisti (nome, cognome, email)
 - accettare un nuovo gasista perché entri a far parte del circuito
 - promuovere un gasista a gestore di ordine

**2. Gestore** <br>
 Il gestore può:
 - inserire un fornitore
 - associare ad un fornitore un elenco di prodotti disponibili
 - creare un ordine selezionando, tra i prodotti legati al fornitore, quelli disponibili per quest' ordine, e inserendo una data fine
 - monitorare l' andamento degli ordini
 - estrarre l' ordine completo per inviarlo al fornitore 

**3. Gasista** <br>
 Il gasista può:
 - creare un utente e aspettare che l' amministratore lo accetti
 - partecipare ad un ordine
 - vedere le cose ordinate (sia quelle in corso che quelle già passate)

## STACK TECNOLOGICO

L' idea è quella di usare un full stack di questo tipo:
 Spring, Postgresql (Supabase), Htmx, css(tailwind?)
