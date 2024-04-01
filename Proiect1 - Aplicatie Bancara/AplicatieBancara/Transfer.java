package AplicatieBancara;

 class Transfer extends Tranzactie{

    protected AplicatieBancara.ContBancar contDestinatie;
    public Transfer(AplicatieBancara.ContBancar contSursa, AplicatieBancara.ContBancar contDestinatie, double suma) {
        this.contSursa = contSursa;
        this.contDestinatie = contDestinatie;
        this.suma = suma;
        this.descriere = "Transfer";
    }
    public void efectueazaTranzactie(){
        double soldCurentSursa = contSursa.getSoldCurent();
        double soldCurentDestinatie = contDestinatie.getSoldCurent();
        ExtrasCont extrasContSursa = contSursa.getExtrasCont();
        ExtrasCont extrasContDestinatie = contDestinatie.getExtrasCont();
        if(soldCurentSursa - suma >= 0) {
            contSursa.setSoldCurent(soldCurentSursa - suma);
            contDestinatie.setSoldCurent(soldCurentDestinatie + suma);
            extrasContSursa.adaugaTranzactie(this);
            extrasContDestinatie.adaugaTranzactie(this);
        }
        else System.out.println("Fonduri insuficiente :(");
    }

    @Override
    public String toString() {

        return "Transfer{" +
                "Cont Sursa = " + contSursa.getIban() +
                ", Cont Destinatie = " + contDestinatie.getIban() +
                ", Suma = " + suma +
                ", Descriere = '" + descriere + '\'' +
                '}';
    }
}
