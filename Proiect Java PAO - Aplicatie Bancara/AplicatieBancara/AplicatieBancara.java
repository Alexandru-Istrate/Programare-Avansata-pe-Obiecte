package AplicatieBancara;

import java.time.LocalDate;
import java.util.*;

public final class AplicatieBancara {

    public static final class Client implements Comparable<Client> {

        private String nume;

        private final String cnp;

        private final List<ContBancar> conturiBancare;

        Client(String nume,String cnp) {
            this.nume = nume;
            this.cnp = cnp;
            this.conturiBancare = new ArrayList<>();
        }

        public String getNume() {
            return nume;
        }

        public String getCnp() {
            return cnp;
        }

        public void setNume(String nume) {
            this.nume = nume;
        }

        public ContBancar creeazaCont(Banca banca){
            return ContBancar.deschideCont(this, banca);
        }

        public ContBancar creeazaCont(Banca banca, String iban){
            return ContBancar.deschideCont(this, banca, new StringBuilder(iban));
        }

        void adaugaContBancar(ContBancar contBancar){
            conturiBancare.add(contBancar);
        }

        public double soldulContului(ContBancar contBancar){
            if( contBancar.getProprietar() == this)
                return contBancar.getSoldCurent();
            else{
                System.out.println("*** Acest client nu este proprietarul contului ***");
                return -1;
            }
        }

        public Card creeazaCard(ContBancar contBancar) {
            if (contBancar.getProprietar() == this) {
                return Card.creeazaCard(this, contBancar);
            } else {
                System.out.println("*** Acest client nu este proprietarul contului ***");
                return null;
            }
        }

        public Card creeazaCard(ContBancar contBancar, String numarCard) {
            if (contBancar.getProprietar() == this) {
                return Card.creeazaCard(this, contBancar, new StringBuilder(numarCard));
            } else {
                System.out.println("*** Acest client nu este proprietarul contului ***");
                return null;
            }
        }

        public List<Card> getCarduri(ContBancar contBancar){
            if(contBancar.getProprietar() == this)
                return contBancar.getCarduri();
            else {
                System.out.println("*** Acest client nu este proprietarul contului ***");
                return null;
            }
        }

        public void afiseazaCarduri(ContBancar contBancar){
            if(contBancar.getProprietar() == this){
                System.out.println("Carduri pentru contul " + contBancar.getIban());
                for(Card card : getCarduri(contBancar)){
                    System.out.println(card);
                }
            }
            else {
                System.out.println("*** Acest client nu este proprietarul contului ***");
            }
        }

        public void platesteCuCardul(Card card, double suma) {
            if (card.getContBancar().getProprietar() == this) {
                PlataCard plataCard = new PlataCard(card.getContBancar(), suma);
                plataCard.efectueazaTranzactie();
            } else {
                System.out.println("*** Acest client nu este proprietarul contului ***");
            }
        }

        public void depoziteaza(ContBancar contBancar, double suma){
            if(contBancar.getProprietar() == this) {
                Depozit depozit = new Depozit(contBancar, suma);
                depozit.efectueazaTranzactie();
            }
            else
                System.out.println("*** Acest client nu este proprietarul contului ***");
        }

        public void retrage(ContBancar contBancar,double suma){
            if(contBancar.getProprietar() == this) {
                Retragere retragere = new Retragere(contBancar, suma);
                retragere.efectueazaTranzactie();
            }
            else
                System.out.println("*** Acest client nu este proprietarul contului ***");
        }

        public void transfera(ContBancar contSursa, ContBancar contDestinatie, double suma){
            if(contSursa.getProprietar() == this) {
                Transfer transfer = new Transfer(contSursa, contDestinatie, suma);
                transfer.efectueazaTranzactie();
            }
            else
                System.out.println("*** Acest client nu este proprietarul contului ***");
        }

        public void genereazaExtras(ContBancar contBancar) {
            if(contBancar.getProprietar() == this) {
                System.out.println();
                System.out.println("Extras de cont pentru: " + nume);
                System.out.println("Data generare: " + contBancar.getExtrasCont().getDataGenerare());
                System.out.println("Sold curent: " + contBancar.getSoldCurent());
                System.out.println("Tranzactii:");

                for (Tranzactie tranzactie : contBancar.getExtrasCont().getTranzactii()) {
                    System.out.println(tranzactie);
                }
            }
            else
                System.out.println("*** Acest client nu este proprietarul contului ***");
        }

        public double calculeazaSoldTotal(){
            double suma = 0;

            for(ContBancar contBancar : conturiBancare){
                suma += contBancar.getSoldCurent();
            }

            return suma;
        }

        @Override
        public int compareTo(Client other) {
            return this.nume.compareTo(other.nume);
        }


        @Override
        public String toString() {
            return "Client{" +
                    "nume='" + nume + '\'' +
                    ", cnp=" + cnp +
                    ", conturiBancare=" + conturiBancare +
                    '}';
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Client client = (Client) o;
            return Objects.equals(cnp, client.cnp);
        }

        @Override
        public int hashCode() {
            return Objects.hash(cnp);
        }
    }


    public final static class Banca {

        private String denumire;

        private String codBanca;

        private final HashMap<Client, List<ContBancar>> clienti;
        protected Banca(String denumire, String codBanca) {
            this.denumire = denumire;
            this.codBanca = codBanca;
            this.clienti = new HashMap<>();
        }

        public String getDenumire() {
            return denumire;
        }

        public String getCodBanca(){
            return codBanca;
        }

        public HashMap<Client, List<ContBancar>> getClienti() {
            return clienti;
        }

        public void setDenumire(String denumire) {
            this.denumire = denumire;
        }

        public void setCodBanca(String codBanca) {
            this.codBanca = codBanca;
        }

         void adaugaContClientului(Client client, ContBancar contBancar) {
            if (clienti.containsKey(client)) {
                List<ContBancar> listaConturi = clienti.get(client);
                listaConturi.add(contBancar);
            } else {
                List<ContBancar> listaConturi = new ArrayList<>();
                listaConturi.add(contBancar);
                clienti.put(client, listaConturi);
            }
        }

        public void afiseazaClienti(){
            System.out.print("Clientii " + this.denumire + " : ");
            for(Map.Entry<Client, List<ContBancar>> entry : clienti.entrySet()){
                System.out.print(entry.getKey().getNume() + ", ");
            }
            System.out.println();
        }

        public void afiseazaClientiCuConturi() {
            System.out.println("Clientii " + this.denumire + " : ");
            for (Map.Entry<Client, List<ContBancar>> entry : clienti.entrySet()) {
                Client client = entry.getKey();
                List<ContBancar> conturiClient = entry.getValue();

                System.out.println("Client: " + client.getNume());
                System.out.println("Conturi bancare:");
                for (ContBancar contBancar : conturiClient) {
                    System.out.println("- " + contBancar);
                }
            }
        }

        @Override
        public String toString() {
            return "Banca{" +
                    "denumire='" + denumire + '\'' +
                    '}';
        }

    }

    public final static class ContBancar implements Comparable<ContBancar>{
        private final StringBuilder iban;
        private final Client proprietar;
        private final Banca banca;
        private double soldCurent;

        private final List<Card> carduri;

        private final ExtrasCont extrasCont;

        private ContBancar(Client client, Banca banca) {
            this.proprietar = client;
            this.banca = banca;
            this.iban = GeneratorRandom.genereazaIban("RO","00", banca);
            this.carduri = new ArrayList<>();
            this.extrasCont = new ExtrasCont();
            client.adaugaContBancar(this);
            banca.adaugaContClientului(client, this);
        }

        private ContBancar(Client client, Banca banca, StringBuilder iban) {
            this.proprietar = client;
            this.banca = banca;
            this.iban = iban;
            this.carduri = new ArrayList<>();
            this.extrasCont = new ExtrasCont();
            client.adaugaContBancar(this);
            banca.adaugaContClientului(client, this);
        }

        static ContBancar deschideCont(Client client, Banca banca){
            return new ContBancar(client, banca);
        }
        static ContBancar deschideCont(Client client, Banca banca, StringBuilder iban){
            return new ContBancar(client, banca, iban);
        }

         Client getProprietar() {
            return proprietar;
        }

         Banca getBanca() {
            return banca;
        }

         StringBuilder getIban() {
            return iban;
        }

         double getSoldCurent() {
            return soldCurent;
        }

         ExtrasCont getExtrasCont() {
            return extrasCont;
        }

        public List<Card> getCarduri() {
            return carduri;
        }

         void setSoldCurent(double soldCurent) {
            this.soldCurent = soldCurent;
        }

        void adaugaCard(Card card){
            carduri.add(card);
        }

        @Override
        public int compareTo(ContBancar other) {
            return Double.compare(this.soldCurent, other.soldCurent);
        }

        @Override
        public String toString() {
            return "ContBancar{" +
                    "Banca=" + banca.getDenumire() +
                    ", iban=" + iban +
                    ", proprietar=" + proprietar.getNume() +
                    ", soldCurent=" + soldCurent +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ContBancar that = (ContBancar) o;
            return Objects.equals(iban, that.iban);
        }

        @Override
        public int hashCode() {
            return Objects.hash(iban);
        }
    }

    public final static class Card {
        private final ContBancar contBancar;

        private final StringBuilder numarCard;

        private final LocalDate dataExpirare;

        private final StringBuilder cvv;

        private Card(ContBancar contBancar) {
            this.contBancar = contBancar;
            this.numarCard = GeneratorRandom.genereazaNumarCard();
            this.dataExpirare = LocalDate.now().plusYears(4);
            this.cvv = GeneratorRandom.genereazaCvv();
            contBancar.adaugaCard(this);
        }

         static Card creeazaCard(Client client, ContBancar contBancar){
            if(contBancar.getProprietar() == client){
                return new Card(contBancar);
            }
            System.out.println("*** Acest client nu este proprietarul contului. Operatia creeazaCard a returnat null ***");
            return null;
        }

        private Card(ContBancar contBancar, StringBuilder numarCard) {
            this.contBancar = contBancar;
            this.numarCard = numarCard;
            this.dataExpirare = LocalDate.now().plusYears(4);
            this.cvv = GeneratorRandom.genereazaCvv();
            contBancar.adaugaCard(this);
        }

        static Card creeazaCard(Client client, ContBancar contBancar, StringBuilder numarCard){
            if(contBancar.getProprietar() == client){
                return new Card(contBancar, numarCard);
            }
            System.out.println("*** Acest client nu este proprietarul contului. Operatia creeazaCard a returnat null ***");
            return null;
        }

         StringBuilder getNumarCard() {
            return numarCard;
        }

         LocalDate getDataExpirare() {
            return dataExpirare;
        }

         StringBuilder getCvv() {
            return cvv;
        }

        ContBancar getContBancar() {
            return contBancar;
        }

        @Override
        public String toString() {
            return "Card{" +
                    "Proprietar=" + contBancar.getProprietar().getNume() +
                    ", numarCard=" + numarCard +
                    ", dataExpirare=" + dataExpirare +
                    ", cvv=" + cvv +
                    '}';
        }
    }

    public static Banca creeazaBanca(String denumire, String codBanca){
        return new Banca(denumire, codBanca);
    }

    public static Client creeazaClient(String nume,String cnp){
        return new Client(nume,cnp);
    }
}
