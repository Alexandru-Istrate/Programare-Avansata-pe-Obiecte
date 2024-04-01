package AplicatieBancara;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

final class ExtrasCont {
    private LocalDate dataGenerare;
    private List<Tranzactie> tranzactii;

     ExtrasCont() {
        this.dataGenerare = LocalDate.now();
        this.tranzactii = new ArrayList<>();
    }

     LocalDate getDataGenerare() {
        return dataGenerare;
    }

     List<Tranzactie> getTranzactii() {
        return tranzactii;
    }

     void adaugaTranzactie(Tranzactie tranzactie) {
        tranzactii.add(tranzactie);
    }
}
