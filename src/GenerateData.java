import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class GenerateData {
    private Connection c = null;
    private Statement stmt = null;

    private void connectToDB() {
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:test.db");
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    private void createTables() {
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE PRODUCT " +
                    "(ID INT PRIMARY KEY     NOT NULL," +
                    " NAME           CHAR(50)    NOT NULL, " +
                    " PRICE          FLOAT     NOT NULL)";
            stmt.executeUpdate(sql);

            String purchaseSql = "CREATE TABLE PURCHASE " +
                    "(ID INT PRIMARY KEY    NOT NULL," +
                    "PRICE          FLOAT   NOT NULL)";
            stmt.executeUpdate(purchaseSql);

            String orderSql = "CREATE TABLE ORDEREDITEM " +
                    "(prid   INT     NOT NULL," +
                    "puid   INT     NOT NULL," +
                    "COUNT  INT     NOT NULL," +
                    "FOREIGN KEY(prid) REFERENCES PRODUCT(ID)," +
                    "FOREIGN KEY(puid) REFERENCES PURCHASE(ID)," +
                    "PRIMARY KEY(prid, puid));";
            stmt.executeUpdate(orderSql);

            stmt.close();
        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public void insertData() {
        try {
            stmt = c.createStatement();

            File file = new File("data.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(" ");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                double price = Double.parseDouble(data[2]);
                String sql = String.format("INSERT INTO PRODUCT (ID,NAME,PRICE) VALUES (%d, '%s', %f);", id, name, price);
                stmt.executeUpdate(sql);
            }
            stmt.close();

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public static void main( String args[] ) {
        GenerateData gen = new GenerateData();
        gen.connectToDB();
        gen.createTables();
        gen.insertData();
    }
}
