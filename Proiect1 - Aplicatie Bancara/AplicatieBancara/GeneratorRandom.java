package AplicatieBancara;
import java.util.Random;

 final class GeneratorRandom {

    private GeneratorRandom(){
    }

     static StringBuilder genereazaNumarCard(){
        Random random = new Random();
        StringBuilder numarCard = new StringBuilder();

        for(int i=0; i<16; i++){
            numarCard.append(random.nextInt(10));
        }
        return numarCard;
    }

     static StringBuilder genereazaCvv(){
        Random random = new Random();
        StringBuilder cvv = new StringBuilder();

        for(int i=0; i<3; i++){
            cvv.append(random.nextInt(10));
        }
        return cvv;
    }

     static StringBuilder genereazaIban(String codTara,String cheieControl, AplicatieBancara.Banca banca){
        Random random = new Random();
        String codBanca = banca.getCodBanca();
        StringBuilder iban = new StringBuilder(codTara + cheieControl + codBanca);
        for(int i=0; i<16; i++){
            iban.append(random.nextInt(10));
        }

        return iban;

    }
}
