package is.hi.flugleit;

import java.sql.*;

public class Database {
    private String url;

    protected Connection conn;

    public Database(String url) { 
        this.url = url;

        try {
            conn = DriverManager.getConnection(url);
        } 
        catch (java.sql.SQLException e) {
            System.out.println("Database connection failed");
        }
    }
}
