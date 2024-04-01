import AplicatieBancara.AplicatieBancara;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

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
    }
}
