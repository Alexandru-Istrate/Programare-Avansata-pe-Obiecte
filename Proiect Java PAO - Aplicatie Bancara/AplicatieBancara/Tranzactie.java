package AplicatieBancara;

abstract class Tranzactie {
    protected double suma;
    protected String descriere;
    protected AplicatieBancara.ContBancar contSursa;
    abstract void efectueazaTranzactie();
}

