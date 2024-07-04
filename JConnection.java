package pointOfSale;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JConnection {
	private Connection con;

    public JConnection() {
        this.con = null;
    }

    public void mediDriver() {
        try {
            String driver = "com.mysql.cj.jdbc.Driver"; // Updated driver class name
            Class.forName(driver);
            System.out.println("Database driver loaded successfully");
        } catch (ClassNotFoundException cnfe) {
            System.out.println("Error loading database driver: " + cnfe.getMessage());
        }
    }

    public Connection condb() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/posdb";
            String user = "ROOTPOS";
            String pass = "ROOT";
            this.con = DriverManager.getConnection(url, user, pass);
            System.out.println("Database connected successfully");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database: " + e.getMessage());
            throw e; //   the exception to handle it outside
        }
        return this.con;
    }

}
