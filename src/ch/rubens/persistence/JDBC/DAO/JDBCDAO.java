package ch.rubens.persistence.JDBC.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rubens
 */
public abstract class JDBCDAO {
    
    private final String Path = "jdbc:mysql://localhost:8080/AddressApp";
    private final String Username = "MyAddressApp";
    private final String Password = "2A2d2s2p";
    private Connection connection;
    
    public JDBCDAO() {
        openConnection();
    }
    
    protected Connection getConnection() {
        return connection;
    }
    
    public void openConnection() {
        
        try {
            connection = DriverManager.getConnection(Path, Username, Password);
        }
        catch (SQLException ex) {
            handleOpenConncetionError(ex);
        }
        
    }
    
    private void handleOpenConncetionError(SQLException error) {
        System.out.println("Connection with BD could not be created: " + error.toString());
    }
    
    public void closeConnection() {
        
        try {
            connection.close();
        } 
        catch (SQLException ex) {
            handleCloseConnectionError(ex);
        }
        
    }
    
    private void handleCloseConnectionError(SQLException error) {
        System.out.println("Connection with BD could not be closed: " + error.toString());
    }
    
}
