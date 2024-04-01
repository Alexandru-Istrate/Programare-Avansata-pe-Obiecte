

Clase/Obiecte: AplicatieBancara, Banca, Client, ContBancar, Card, ExtrasCont, Tranzactie, Depozit, Retragere, Transfer, PlataCard, GeneratorRandom.
 
Pentru a utiliza sistemul :
import AplicatieBancara.AplicatieBancara

Acțiuni/interogări

1. Creeaza banca

AplicatieBancara.Banca banca = AplicatieBancara.creeazaBanca(“numeBanca”, “codBanca”);


2. Creeaza client

AplicatieBancara.Client client = AplicatieBancara.creeazaClient(“nume”, “cnp”);


3. Creeaza cont bancar  
AplicatieBancara.ContBancar contBancar = client.creeazaCont(banca);

client este instanță a clasei AplicatieBancara.Client
banca este instante a clasei AplicatieBancara.Banca

4. Creeaza card

AplicatieBancara.Card card = client.creeazaCard(contBancar);

client este instanță a clasei AplicatieBancara.Client
contBancar este instanta a clasei AplicatieBancara.ContBancar si a fost creat prin apelul client.creeazaCont

5. Depozit in cont bancar

client.depozit(contBancar, 100);

client este instanță a clasei AplicatieBancara.Client
contBancar este instanta a clasei AplicatieBancara.ContBancar si a fost creat prin apelul client.creeazaCont

6. Retragere suma din cont bancar

client.retragere(contBancar, 100);

client este instanță a clasei AplicatieBancara.Client
contBancar este instanta a clasei AplicatieBancara.ContBancar si a fost creat prin apelul client.creeazaCont.

7. Transfer bancar.

client.transfer(contBancarSursa, contBancarDestinatie, 100);

client este instanță a clasei AplicatieBancara.Client
contBancarSursa este instanta a clasei AplicatieBancara.ContBancar si a fost creat prin apelul client.creeazaCont.
contBancarDestinatie este instanta a clasei AplicatieBancara.ContBancar

8. Plata cu cardul

client.platesteCard(card, 100);

client este instanță a clasei AplicatieBancara.Client


9. Genereaza extras de cont

client.genereazaExtras(contBancar);

client este instanță a clasei AplicatieBancara.Client
contBancar este instanta a clasei AplicatieBancara.ContBancar si a fost creat prin apelul client.creeazaCont.

Exemplu output:

Extras de cont pentru: Vlad Grigore
Data generare: 2024-04-01
Sold curent: 54.5
Tranzactii:
Depozit{Cont Sursa = RO00BNR7735972033636308, Suma = +30.0, Descriere = 'Depozit'}
PlataCard{Cont Sursa = RO00BNR7735972033636308, Suma = -25.5, Descriere = 'Plata Card'}
Transfer{Cont Sursa = RO00BNR4720829765667288, Cont Destinatie = RO00BNR7735972033636308, Suma = 50.0, Descriere = 'Transfer'}

10. Afisare carduri asociate unui cont bancar

client1.afisareCarduri(contBancar);

client este instanță a clasei AplicatieBancara.Client
contBancar este instanta a clasei AplicatieBancara.ContBancar si a fost creat prin apelul client.creeazaCont

11. Interogare sold total al unui client, calculat din toate conturile sale bancare.

int soldTotal = client.calculeazaSoldTotal()

client este instanță a clasei AplicatieBancara.Client

12. Afisare lista tuturor clientilor unei banc

 banca.afisareClienti();	
 		sau
 banca.afisareClientiCuConturi();

banca este instante a clasei AplicatieBancara.Banca











