package AplicatieBancara;

 final class Retragere extends Tranzactie{

     Retragere(AplicatieBancara.ContBancar contSursa, double suma) {
        this.contSursa = contSursa;
        this.suma = suma;
        this.descriere = "Retragere";
    }
     void efectueazaTranzactie(){
        double soldCurent = contSursa.getSoldCurent();
        ExtrasCont extrasContSursa = contSursa.getExtrasCont();

        if(soldCurent - suma >= 0) {
            contSursa.setSoldCurent(soldCurent - suma);
            extrasContSursa.adaugaTranzactie(this);
        }
        else {
            System.out.println("Fonduri insuficiente :(");
        }
    }


    @Override
    public String toString() {
        return "Retragere{" +
                "Cont Sursa = " + contSursa.getIban() +
                ", Suma = -" + suma +
                ", Descriere = '" + descriere + '\'' +
                '}';
    }
}
