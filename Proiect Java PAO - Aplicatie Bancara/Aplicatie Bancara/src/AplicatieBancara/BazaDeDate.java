package AplicatieBancara;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BazaDeDate {
    private static BazaDeDate instanta;
    private Connection connection;

    private CSVWriter csvWriter;



    private BazaDeDate(String url, String pathToCsvFile){
        try {
            boolean newFile = false;
            this.connection = DriverManager.getConnection(url);
            File csvFile = new File(pathToCsvFile);
            if (!csvFile.exists()) {
                csvFile.createNewFile();
                newFile = true;
            }
            FileWriter fileWriter = new FileWriter(pathToCsvFile, true);
            this.csvWriter = new CSVWriter(fileWriter);
            if(newFile){
                initializeazaCsv();
            }
        }
        catch(Exception e){
            System.out.println("A aparut o problema la instantierea clasei BazaDeDate");
            e.printStackTrace();
        }
    }

    private BazaDeDate(String url, String user, String password, String pathToCsvFile){
        try {
            boolean newFile = false;
            this.connection = DriverManager.getConnection(url, user, password);
            File csvFile = new File(pathToCsvFile);
            if (!csvFile.exists()) {
                csvFile.createNewFile();
                newFile = true;
            }
            FileWriter fileWriter = new FileWriter(pathToCsvFile, true);
            this.csvWriter = new CSVWriter(fileWriter);
            if(newFile){
                initializeazaCsv();
            }
        }
            catch(Exception e){
            System.out.println("A aparut o problema la instantierea clasei BazaDeDate");
            e.printStackTrace();
        }
    }

    public static BazaDeDate getInstanta(String url, String pathToCsvFile){
        if (instanta == null){
            instanta = new BazaDeDate(url, pathToCsvFile);
        }
        return instanta;
    }

    public static BazaDeDate getInstanta(String url, String user, String password, String pathToCsvFile){
        if (instanta == null){
            instanta = new BazaDeDate(url, user, password, pathToCsvFile);
        }
        return instanta;
    }

    private void initializeazaCsv(){
        try {
            String[] header = {"nume_actiune", "timestamp"};
            csvWriter.writeNext(header);
            csvWriter.flush();
        }
        catch(Exception e){
            System.out.println("A aparut o eroare la initilaizarea fisierului csv");
            e.printStackTrace();
        }
    }

    public void insereazaBanca(AplicatieBancara.Banca banca){
        try {
            String query = "INSERT INTO banci VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, banca.getCodBanca());
            statement.setString(2, banca.getDenumire());
            statement.executeUpdate();

            String[] addToCsv = {"Inserare banca", Instant.now().toString()};
            csvWriter.writeNext(addToCsv);
            csvWriter.flush();
        }
        catch(Exception e){
            System.out.println("A aparut o problema la inserare banca");
            e.printStackTrace();
        }
    }

    public void insereazaClient(AplicatieBancara.Client client){
        try {
            String query = "INSERT INTO clienti VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getCnp());
            statement.setString(2, client.getNume());
            statement.executeUpdate();

            String[] addToCsv = {"Inserare client", Instant.now().toString()};
            csvWriter.writeNext(addToCsv);
            csvWriter.flush();
        }
        catch(Exception e){
            System.out.println("A aparut o problema la inserare client");
            e.printStackTrace();
        }
    }

    public void insereazaContBancar(AplicatieBancara.ContBancar contBancar){
        try {
            String query = "INSERT INTO conturi_bancare VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contBancar.getIban().toString());
            statement.setString(2, contBancar.getBanca().getCodBanca());
            statement.setString(3, contBancar.getProprietar().getCnp());
            statement.setDouble(4, contBancar.getSoldCurent());
            statement.executeUpdate();

            String[] addToCsv = {"Inserare cont bancar", Instant.now().toString()};
            csvWriter.writeNext(addToCsv);
            csvWriter.flush();
        }
        catch(Exception e){
            System.out.println("A aparut o problema la inserare cont bancar");
            e.printStackTrace();
        }
    }

    public void insereazaCard(AplicatieBancara.Card card){
        try {
            String query = "INSERT INTO carduri VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, card.getNumarCard().toString());
            statement.setString(2, card.getCvv().toString());
            statement.setDate(3, Date.valueOf(card.getDataExpirare()));
            statement.setString(4, card.getContBancar().getIban().toString());
            statement.executeUpdate();

            String[] addToCsv = {"Inserare card", Instant.now().toString()};
            csvWriter.writeNext(addToCsv);
            csvWriter.flush();
        }
        catch(Exception e){
            System.out.println("A aparut o problema la inserare card");
            e.printStackTrace();
        }
    }

    public void stergeBanca(AplicatieBancara.Banca banca){
        try{
            String query = "DELETE FROM banci WHERE cod_banca = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, banca.getCodBanca());
            statement.executeUpdate();

            String[] addToCsv = {"Stergere banca", Instant.now().toString()};
            csvWriter.writeNext(addToCsv);
            csvWriter.flush();
        }
        catch(Exception e){
            System.out.println("A aparut o problema la stergere banca");
            e.printStackTrace();
        }
    }

    public void stergeClient(AplicatieBancara.Client client){
        try{
            String query = "DELETE FROM clienti WHERE cnp_client = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getCnp());
            statement.executeUpdate();

            String[] addToCsv = {"Stergere client", Instant.now().toString()};
            csvWriter.writeNext(addToCsv);
            csvWriter.flush();
        }
        catch(Exception e){
            System.out.println("A aparut o problema la stergere client");
            e.printStackTrace();
        }
    }

    public void stergeContBancar(AplicatieBancara.ContBancar contBancar){
        try{
            String query = "DELETE FROM conturi_bancare WHERE iban = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, contBancar.getIban().toString());
            statement.executeUpdate();

            String[] addToCsv = {"Stergere cont bancar", Instant.now().toString()};
            csvWriter.writeNext(addToCsv);
            csvWriter.flush();
        }
        catch(Exception e){
            System.out.println("A aparut o problema la stergere cont bancar");
            e.printStackTrace();
        }
    }

    public void stergeCard(AplicatieBancara.Card card){
        try{
            String query = "DELETE FROM carduri WHERE numar_card = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, card.getNumarCard().toString());
            statement.executeUpdate();

            String[] addToCsv = {"Stergere card", Instant.now().toString()};
            csvWriter.writeNext(addToCsv);
            csvWriter.flush();
        }
        catch(Exception e){
            System.out.println("A aparut o problema la stergere cont bancar");
            e.printStackTrace();
        }
    }

    public void actualizeazaSold(AplicatieBancara.ContBancar contBancar){
        try{
            String query =  "UPDATE conturi_bancare " +
                            "SET sold = ? " +
                            "WHERE iban = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDouble(1, contBancar.getSoldCurent());
            statement.setString(2, contBancar.getIban().toString());
            statement.executeUpdate();

            String[] addToCsv = {"Actualizare sold", Instant.now().toString()};
            csvWriter.writeNext(addToCsv);
            csvWriter.flush();
        }
        catch (Exception e){
            System.out.println("A aparut o problema la actualizare sold");
            e.printStackTrace();
        }
    }

    List<AplicatieBancara.Banca> citesteBanci(){
        List<AplicatieBancara.Banca> banci = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM banci";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String codBanca = rs.getString("cod_banca");
                String denumire = rs.getString("nume_banca");
                AplicatieBancara.Banca banca = AplicatieBancara.creeazaBanca(codBanca, denumire);
                banci.add(banca);
            }
        }
        catch (SQLException e){
            System.out.println("A aparut o problema la citire banci");
            e.printStackTrace();
        }
        return banci;
    }

    List<AplicatieBancara.Client> citesteClienti(){
        List<AplicatieBancara.Client> clienti = new ArrayList<>();
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM clienti";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String cnpClient = rs.getString("cnp_client");
                String numeClient = rs.getString("nume_client");
                AplicatieBancara.Client client = AplicatieBancara.creeazaClient(numeClient, cnpClient);
                clienti.add(client);
            }

            List<AplicatieBancara.Banca> banci = citesteBanci();
            citesteConturiSiCarduri(banci, clienti);
        }
        catch (SQLException e){
            System.out.println("A aparut o problema la citire clienti");
            e.printStackTrace();
        }
        return clienti;
    }

    private void citesteConturiSiCarduri(List<AplicatieBancara.Banca> banci, List<AplicatieBancara.Client> clienti){
        HashMap<String, AplicatieBancara.Banca> hashMapBanci = new HashMap<>();
        HashMap<String, AplicatieBancara.Client> hashMapClienti = new HashMap<>();
        HashMap<String, AplicatieBancara.ContBancar> hashMapConturi = new HashMap<>();

        for(AplicatieBancara.Banca b : banci){
            String codBanca = b.getCodBanca();
            hashMapBanci.put(codBanca, b);
        }

        for(AplicatieBancara.Client c : clienti){
            String cnpClient = c.getCnp();
            hashMapClienti.put(cnpClient, c);
        }

        try{
            Statement statement = connection.createStatement();
            String queryConturi = "SELECT * FROM conturi_bancare";
            ResultSet rsConturi = statement.executeQuery(queryConturi);
            while(rsConturi.next()){
                String iban = rsConturi.getString("iban");
                String codBanca = rsConturi.getString("cod_banca");
                String cnpClient = rsConturi.getString("cnp_client");
                Double sold = rsConturi.getDouble("sold");

                AplicatieBancara.Banca banca = hashMapBanci.get(codBanca);
                AplicatieBancara.Client client = hashMapClienti.get(cnpClient);
                if(client != null && banca != null) {
                    AplicatieBancara.ContBancar cont = client.creeazaCont(banca, iban);
                    cont.setSoldCurent(sold);
                    hashMapConturi.put(iban, cont);
                }
            }

            String queryCarduri = "SELECT * FROM carduri";
            ResultSet rsCarduri = statement.executeQuery(queryCarduri);
            while(rsCarduri.next()){
                String numarCard = rsCarduri.getString("numar_card");
                String cvv = rsCarduri.getString("cod_cvv");
                LocalDate dataExpirare = rsCarduri.getDate("data_expirare").toLocalDate();
                String iban = rsCarduri.getString("iban");

                AplicatieBancara.ContBancar contBancar = hashMapConturi.get(iban);
                if(contBancar != null) {
                    AplicatieBancara.Client client = contBancar.getProprietar();
                    if (client != null) {
                        client.creeazaCard(contBancar, numarCard, cvv, dataExpirare);
                    }
                }
            }
        }
        catch(SQLException e){
            System.out.println("A aparut o problema la citire conturi bancare si carduri");
        }
    }

    public void afiseazaBanci(){
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM banci";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println("cod_banca: " + rs.getString("cod_banca") + " | nume_banca: " + rs.getString("nume_banca"));
            }
        }
        catch (SQLException e){
            System.out.println("A aparut o problema la afisare banci");
            e.printStackTrace();
        }
    }

    public void afiseazaClienti(){
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM clienti";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println("cnp_client: " + rs.getString("cnp_client") + " | nume_client: " + rs.getString("nume_client"));
            }
        }
        catch (SQLException e){
            System.out.println("A aparut o problema la afisare clienti");
            e.printStackTrace();
        }
    }

    public void afiseazaConturiBancare(){
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM conturi_bancare";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println(
                        "IBAN: " + rs.getString("iban") +
                                " | cod_banca: " + rs.getString("cod_banca") +
                                " | cnp_client: " + rs.getString("cnp_client") +
                                " | sold: " + rs.getDouble("sold")
                );
            }
        }
        catch (SQLException e){
            System.out.println("A aparut o problema la afisare banci");
            e.printStackTrace();
        }
    }

    public void afiseazaCarduri(){
        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM carduri";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println(
                        "numar_card: " + rs.getString("numar_card") +
                                " | cod_cvv: " + rs.getString("cod_cvv") +
                                " | data_expirare: " + rs.getDate("data_expirare") +
                                " | iban: " + rs.getString("iban")
                );
            }
        }
        catch (SQLException e){
            System.out.println("A aparut o problema la afisare banci");
            e.printStackTrace();
        }
    }

}
