# Web Application per GAS

Sono parte di un GAS che ancora gestisce molte cose via mail e con appunti manuali.

<ins>Capita</ins>, ad esempio, che un gasista faccia un ordine e poi cerchi di contattare (via mail o tel) il
referente dell' ordine per sapere se la sua ordinazione è confermata o meno 😱

<ins>Capita</ins> che il referente di un ordine si faccia mandare delle mail e registri tutto su un foglio
per poi perdere o l' uno o le altre e debba chiedere nuovamente ai gasisti di ordinare 😱

<ins>Capita</ins>... insomma ho reso l' idea.

**Per questo** sviluppare un' applicazione, via web, che permetta di semplificare gli ordini e risolvere problemi come quelli elencati sopra mi pare una buona idea. <br>
L' applicazione si concentra esclusivamente sul GAS in questione, perciò non pretende di essere utilizzabile anche da altri GAS. E' una dotazione interna.

## Cosa succede

Di solito le cose funzionano così: 
 - A.  qualche volenteroso(aka *Referente*) si occupa di contattare un fornitore che conosce 
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
 - F. il referente vede il riepilogo di tutti gli ordini effettuati con i totali già calcolati e può usare questo per inviarlo al fornitore
 - G. il referente si prepara a ricevere la consegna e smistare i prodotti



## Attori

No il teatro non c' entra. Ma per capire come far funzionare un' applicazione è necessario prevedere
gli attori coinvolti. Chiarisco subito il mistero individuando i 3 attori principali:
 - Amministratori
 - Gestori
 - Gasisti

*Perché?* E' necessario capire cosa può fare la persona che entra in questa applicazione, ed è bene che non tutti possano fare determinate operazioni. Nell' elenco che segue ci sono le possibilità di ogni singolo attore. L' amministratore, oltre alle sue operazioni, può compiere anche quelle dei gestori e dei gasisti,
il gestore quelle dei gasisti e il gasista solo le sue proprie.

**1. Amministratore** <br>
L' amministratore è colei (o colui) che tutto può e vede (parlo solo del GAS naturalmente).Infatti può:
 - vedere l' elenco dei gasisti (nome, cognome, email, ruolo: semplice gasista, referente, amministratore)

 - accettare un nuovo gasista perché entri a far parte del circuito. 
 Quando un nuovo gasista vuole entrare nel circuito, si registra sull' applicazione e poi attende l'approvazione da parte dell' amministratore
 
 - promuovere un gasista a referente di ordine. 
 Dall' elenco dei gasisti l' amministratore può promuoverne uno a referente.

 - eliminare un gasista dall' elenco.
 Se per qualsiasi motivo qualcuno uscisse dal circuito

**2. Referente** <br>
 Il referente può:
 - inserire un fornitore
 - associare ad un fornitore un elenco di prodotti disponibili
 - creare un ordine selezionando, tra i prodotti legati al fornitore, quelli disponibili per quest' ordine, e inserendo una data fine
 - monitorare l' andamento degli ordini
 - estrarre l' ordine completo per inviarlo al fornitore 
 - segnare, in fase di consegna o anche dopo, da chi ha ricevuto il pagamento

**3. Gasista** <br>
 Il gasista può:
 - creare un utente e aspettare che l' amministratore lo accetti
 - partecipare ad un ordine
 - vedere le cose ordinate (sia quelle in corso che quelle già passate)

## STACK TECNOLOGICO

L' idea è quella di usare uno stack di questo tipo:
 Spring, Postgresql, Htmx, css

**SQL** <br>

```sql

CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT primary key,
  email varchar(255) NOT NULL unique,
  is_active int NOT NULL,
  password varchar(255) DEFAULT NULL,
  registration_date datetime(6) DEFAULT NULL,
  nome varchar(255) NOT NULL,
  cognome varchar(255) NOT NULL,
  tipo varchar(255) NOT NULL
);


create table fornitore(
 id int AUTO_INCREMENT PRIMARY KEY,
 nome varchar(255) NOT NULL,
 descrizione varchar(2000),
 cell int,
 mail varchar(255)
);

create table tipo_quantita(
	id int primary key,
	descrizione varchar(255)
);

insert into tipo_quantita
  values(1,'Pezzi');
  
insert into tipo_quantita
  values(2,'KG');
  
insert into tipo_quantita
    values(3,'Vasetti');



/*Al momento il prodotto è per forza relativo ad un fornitore*/
create table prodotto(
	  id int auto_increment primary key,
	  id_fornitore int not null,
    nome varchar(255),
    descrizione varchar(1000),
    tipo_quantita int,
    prezzo_unitario double,
    foreign key (id_fornitore) references fornitore(id),
    foreign key (tipo_quantita) references tipo_quantita(id)
);

create table ordine(
  id int auto_increment primary key,
  id_fornitore int not null,
  inizio date,
  fine date,
  descrizione varchar(1023),
  foreign key (id_fornitore) references fornitore(id)
);

create table prodotto_ordine(
  id_prodotto int not null,
  id_ordine int not null,
  foreign key (id_prodotto) references prodotto(id),
  foreign key (id_ordine) references ordine(id)
);

create table acquisto_ordine(
  id int auto_increment primary key,
  id_user int not null,
  id_ordine int not null,
  id_prodotto int not null,
  quantita int not null
  foreign key (id_user) references users(id) 
  foreign key (id_ordine) references ordine(id),
  foreign key (id_prodotto) references prodotto(id)
);

```
