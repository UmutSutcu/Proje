package DatabaseConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {
     Connection connection;

    {
        try {
            connection = (Connection) DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/bank-system","root","12345");
                    System.out.println("System successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
