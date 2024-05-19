package AplicatieBancara;
import java.sql.*;

public class BazaDeDate {
    private static BazaDeDate instanta;
    private Connection connection;



    private BazaDeDate(String url){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private BazaDeDate(String url, String user, String password){
        try {
            this.connection = DriverManager.getConnection(url, user, password);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static BazaDeDate getInstanta(String url){
        if (instanta == null){
            instanta = new BazaDeDate(url);
        }
        return instanta;
    }

    public static BazaDeDate getInstanta(String url, String user, String password){
        if (instanta == null){
            instanta = new BazaDeDate(url, user, password);
        }
        return instanta;
    }

    public void insereazaBanca(AplicatieBancara.Banca banca){
        try {

            String query = "INSERT INTO banci VALUES(?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, banca.getCodBanca());
            statement.setString(2, banca.getDenumire());
            statement.executeUpdate();
        }
        catch(SQLException e){
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
        }
        catch(SQLException e){
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
        }
        catch(SQLException e){
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
        }
        catch(SQLException e){
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
        }
        catch(SQLException e){
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
        }
        catch(SQLException e){
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
        }
        catch(SQLException e){
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
        }
        catch(SQLException e){
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
        }
        catch (SQLException e){
            System.out.println("A aparut o problema la actualizare sold");
            e.printStackTrace();
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
