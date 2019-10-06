package ch.rubens.persistence.JDBC.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author rubens
 */
public abstract class JDBCDAO {
    
    private final String Driver = "com.mysql.cj.jdbc.Driver";
    private final String Time_Zone = "?useTimezone=true&serverTimezone=UTC";
    private final String Database_Name = "address_app_database";
    private final String Path = "jdbc:mysql://127.0.0.1:3306/" + Database_Name + Time_Zone;
    private final String Username = "MyAddressAppUser";
    private final String Password = "2A2d2s2p";
    private Connection connection;
    
    public JDBCDAO() {

    }
    
    protected Connection getConnection() {
        return connection;
    }
    
    public void openConnection() {
        
        try {
            
            try {
                Class.forName(Driver);
            } 
            catch (ClassNotFoundException ex) {
                handleDriverError(ex);
            }
            
            connection = DriverManager.getConnection(Path, Username, Password);
            
        }
        catch (SQLException ex) {
            handleOpenConncetionError(ex);
        }
        
    }
    
    private void handleOpenConncetionError(SQLException error) {
        System.out.println("Connection with BD could not be created: " + error.toString());
    }
    
    private void handleDriverError(ClassNotFoundException error) {
        System.out.println("The driver could no be found: " + error.toString());
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
