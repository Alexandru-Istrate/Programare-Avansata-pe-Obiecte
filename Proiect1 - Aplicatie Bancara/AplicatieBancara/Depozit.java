package AplicatieBancara;

 final class Depozit extends Tranzactie{
     Depozit(AplicatieBancara.ContBancar contSursa, double suma) {
        this.contSursa = contSursa;
        this.suma = suma;
        this.descriere = "Depozit";
    }
     void efectueazaTranzactie(){
        double soldCurent = contSursa.getSoldCurent();
        ExtrasCont extrasContSursa = contSursa.getExtrasCont();
        contSursa.setSoldCurent(soldCurent + suma);
        extrasContSursa.adaugaTranzactie(this);
    }

    @Override
    public String toString() {
        return "Depozit{" +
                "Cont Sursa = " + contSursa.getIban() +
                ", Suma = +" + suma +
                ", Descriere = '" + descriere + '\'' +
                '}';
    }
}
