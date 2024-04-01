
# Definirea Sistemului

## Clase/Obiecte

AplicatieBancara, Banca, Client, ContBancar, Card, ExtrasCont, Tranzactie, Depozit, Retragere, Transfer, PlataCard, GeneratorRandom.
 
## Pentru a utiliza sistemul
```java
import AplicatieBancara.AplicatieBancara
```
## Acțiuni/interogări

1. Creează o bancă
```java
AplicatieBancara.Banca banca = AplicatieBancara.creeazaBanca("numeBanca", "codBanca");
```
2. Creează un client
 ```java
AplicatieBancara.Client client = AplicatieBancara.creeazaClient("nume", "cnp");
```
3. Creează un cont bancar pentru client
```java
AplicatieBancara.ContBancar contBancar = client.creeazaCont(banca);
```
4. Creează un card pentru client
```java
AplicatieBancara.Card card = client.creeazaCard(contBancar);
```
5. Depozitează suma în cont bancar
```java
client.depoziteaza(contBancar, 100);
```
6. Retrage suma din cont bancar
```java
client.retrage(contBancar, 100);
```
7. Transferă suma între două conturi bancare
```java
client.transfera(contBancarSursa, contBancarDestinatie, 100);
```
8. Plătește cu cardul
```java
client.platesteCuCardul(card, 100);
```
9. Generează extras de cont
```java  
client.genereazaExtras(contBancar);
```
10. Afișează cardurile asociate unui cont bancar
```java
client.afiseazaCarduri(contBancar);
```
11. Calculează soldul total al clientului
```java
int soldTotal = client.calculeazaSoldTotal();
```
12. Afișează lista tuturor clienților unei bănci
```java
banca.afiseazaClienti();
banca.afiseazaClientiCuConturi();
```













