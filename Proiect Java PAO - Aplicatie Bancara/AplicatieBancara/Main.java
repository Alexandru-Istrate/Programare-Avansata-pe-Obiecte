package AplicatieBancara;

import AplicatieBancara.AplicatieBancara;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import AplicatieBancara.BazaDeDate;

public class Main {

    static void printDelimitator(String descriere){
        for (int i = 0; i < 150; i++)
            System.out.print('-');
        System.out.println();
        System.out.println(descriere);
        System.out.println();
    }

    public static void main(String[] args) {
        AplicatieBancara.Banca banca = AplicatieBancara.creeazaBanca("BNR", "BNR");

        AplicatieBancara.Client client1 = AplicatieBancara.creeazaClient("Vlad Grigore", "5011130653214");
        AplicatieBancara.Client client2 = AplicatieBancara.creeazaClient("Mirabela Voichita", "4030605215521");
        AplicatieBancara.Client client3 = AplicatieBancara.creeazaClient("Alin Constantin", "5020310212943");

        AplicatieBancara.ContBancar contBancarClient1 = client1.creeazaCont(banca);
        AplicatieBancara.ContBancar contBancarClient2 = client2.creeazaCont(banca);
        AplicatieBancara.ContBancar contBancarClient3 = client3.creeazaCont(banca);


        client1.depoziteaza(contBancarClient1, 30);
        client2.depoziteaza(contBancarClient2,100);
        client3.depoziteaza(contBancarClient3, 49.99);

        AplicatieBancara.Card card = client1.creeazaCard(contBancarClient1);
        client1.platesteCuCardul(card, 25.5);

        client2.transfera(contBancarClient2, contBancarClient1, 50);

        client2.retrage(contBancarClient2, 20);


        printDelimitator("Generare extras de cont");
        client1.genereazaExtras(contBancarClient1);
        printDelimitator("Generare extras de cont");
        client2.genereazaExtras(contBancarClient2);


        List<AplicatieBancara.Client> list =  new ArrayList<>(List.of(client1,client2,client3));
        Collections.sort(list);

        printDelimitator("Lista de clienti, ordonata crescator");
        System.out.println(list);

        printDelimitator("Carduri asociate unui cont bancar");
        client1.afiseazaCarduri(contBancarClient1);

        printDelimitator("Sold total al unui client");
        System.out.println(client1.calculeazaSoldTotal());

        printDelimitator("Lista tuturor clientilor unei banci, alaturi de conturile bancare asociate");
        banca.afiseazaClientiCuConturi();

        printDelimitator("Incercarea de a realiza operatiuni bancare pe contul altui client");
        client2.retrage(contBancarClient3, 1000);
        /*
        BazaDeDate bd = BazaDeDate.getInstanta("jdbc:mysql://localhost:3306/proiect_pao", "root", "", "log.csv");
        AplicatieBancara.Client client5 = AplicatieBancara.creeazaClient("Stanica Ionescu", "5120807323421");
        bd.actualizeazaSold(contBancarClient1);

        bd.insereazaBanca(banca);
        bd.insereazaClient(client1);
        bd.insereazaContBancar(contBancarClient1);
        bd.insereazaClient(client2);
        bd.insereazaClient(client3);
        bd.insereazaContBancar(contBancarClient2);
        bd.insereazaContBancar(contBancarClient3);
        bd.insereazaCard(card);

        bd.insereazaClient(client4);
        bd.insereazaContBancar(contBancarClient4);
        bd.insereazaCard(cardProba);


        AplicatieBancara.Client client4 = AplicatieBancara.creeazaClient("Jon Jonovici", "5121212292929");
        AplicatieBancara.ContBancar contBancarClient4 =  client4.creeazaCont(banca, "BNR001241232562351466");
        AplicatieBancara.Card cardProba =  client4.creeazaCard(contBancarClient4, "544232352112322");

        bd.insereazaClient(client4);
        bd.insereazaContBancar(contBancarClient4);
        bd.insereazaCard(cardProba);
        client4.depoziteaza(contBancarClient4, 500);
        bd.actualizeazaSold(contBancarClient4);

        bd.afiseazaBanci();
        bd.afiseazaClienti();
        bd.afiseazaConturiBancare();
        bd.afiseazaCarduri();
        */

    }

}
