


Definirea Sistemului


Clase/Obiecte: AplicatieBancara, Banca, Client, ContBancar, Card, ExtrasCont, Tranzactie, Depozit, Retragere, Transfer, PlataCard, GeneratorRandom.
 
Pentru a utiliza sistemul :
import AplicatieBancara.AplicatieBancara

Acțiuni/interogări

1. Creează o bancă
AplicatieBancara.Banca banca = AplicatieBancara.creeazaBanca("numeBanca", "codBanca");

2. Creează un client
AplicatieBancara.Client client = banca.creeazaClient("nume", "cnp");

3. Creează un cont bancar pentru client
AplicatieBancara.ContBancar contBancar = client.creeazaCont();

4. Creează un card pentru client
AplicatieBancara.Card card = client.creeazaCard(contBancar);

5. Depozitează suma în cont bancar
client.depoziteaza(contBancar, 100);

6. Retrage suma din cont bancar
client.retrage(contBancar, 100);

7. Transferă suma între două conturi bancare
client.transfera(contBancarSursa, contBancarDestinatie, 100);

8. Plătește cu cardul
client.platesteCuCardul(card, 100);

9. Generează extras de cont
client.genereazaExtras(contBancar);

10. Afișează cardurile asociate unui cont bancar
client.afiseazaCarduri(contBancar);

11. Calculează soldul total al clientului
int soldTotal = client.calculeazaSoldTotal();

12. Afișează lista tuturor clienților unei bănci
banca.afiseazaClienti();
banca.afiseazaClientiCuConturi();












