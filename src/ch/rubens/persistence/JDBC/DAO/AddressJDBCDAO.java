package ch.rubens.persistence.JDBC.DAO;

import ch.rubens.address.model.concreate.Address;
import ch.rubens.persistence.DAO.abstracts.IAddressDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rubens
 */
public class AddressJDBCDAO extends JDBCDAO implements IAddressDAO {

    private final String TABLE_NAME = "address";
    private final String TABLE_ID_COLUMN_NAME = "postalCode";
    private final String TABLE_STREET_COLUMN_NAME = "street";
    private final String TABLE_CITY_COLUMN_NAME = "city";
    
    @Override
    public Address getAddress(int postalCode) {
        
        String query = "SELECT * FROM " + TABLE_NAME +
                        " WHERE " + TABLE_ID_COLUMN_NAME + " = " + "'" + postalCode + "';";
        
        openConnection();
        Connection connection = getConnection();
        
        Address address = null;
        
        try {
            
            PreparedStatement queryCall = connection.prepareStatement(query);
            ResultSet queryResult = queryCall.executeQuery();
            
            if (queryResult != null && queryResult.next()) {
                
                Integer postalCodeRecovered = queryResult.getInt(TABLE_ID_COLUMN_NAME);
                String street = queryResult.getString(TABLE_STREET_COLUMN_NAME);
                String city = queryResult.getString(TABLE_CITY_COLUMN_NAME);
                
                address = new Address(postalCodeRecovered);
                address.setCity(city);
                address.setStreet(street);
                
            }
            
        }
        catch (SQLException ex) {
            
            System.out.println("Error when tring to get address data in table: " + TABLE_NAME + ". " + ex);
            address = null;
            
        }
        finally {
            
            closeConnection();
            return address;
            
        }
        
    }

    @Override
    public Address remove(int postalCode) {
        
        String query = "DELETE FROM " + TABLE_NAME + 
                        " WHERE " + TABLE_ID_COLUMN_NAME + " = " + postalCode;
        
        openConnection();
        Connection connection = getConnection();
        
        Address addressDeleted = null;
        
        try {
            
            addressDeleted = getAddress(postalCode);
            PreparedStatement queryCall = connection.prepareStatement(query);
            queryCall.execute();
            
        }
        catch (SQLException ex) {
            
            System.out.println("Error on address deletion in table: " + TABLE_NAME + ". " + ex);
            addressDeleted = null;
            
        }
        finally {
            
            closeConnection();
            return addressDeleted;
            
        }
        
    }

    @Override
    public Address update(int postalCode, Address newAddressData) {
        
        String query = "UPDATE " + TABLE_NAME + 
                        " SET " +
                            TABLE_ID_COLUMN_NAME + " = " + "'" + newAddressData.getPostalCode() + "', " +
                            TABLE_CITY_COLUMN_NAME + " = " + "'" + newAddressData.getCity() + "', " +
                            TABLE_STREET_COLUMN_NAME + " = " + "'" + newAddressData.getStreet() + "'" +
                        " WHERE " + TABLE_ID_COLUMN_NAME + " = " + "'" + postalCode + "'";
        
        openConnection();
        Connection connection = getConnection();
        
        Address oldAddress = null;
        
        try {
            
            oldAddress = getAddress(postalCode);
            PreparedStatement queryCall = connection.prepareStatement(query);
            queryCall.executeUpdate();
            
        }
        catch (SQLException ex) {
            
            System.out.println("Error on address updating in table: " + TABLE_NAME + ". " + ex);
            oldAddress = null;
            
        }
        finally {
            
            closeConnection();
            return oldAddress;
            
        }
        
    }

    @Override
    public boolean isRegistered(int postalCode) {
        
        Address addressSearched = getAddress(postalCode);
        return (addressSearched != null);
        
    }

    @Override
    public String getStreet(int postalCode) {
        
        String query = "SELECT " + TABLE_STREET_COLUMN_NAME + " FROM " + TABLE_NAME +
                        " WHERE " + TABLE_ID_COLUMN_NAME + " = " + "'" + postalCode + "';";
        
        openConnection();
        Connection connection = getConnection();
        
        String street = "";
        
        try {
            
            PreparedStatement queryCall = connection.prepareStatement(street);
            ResultSet queryResult = queryCall.executeQuery();
            
            if (queryResult != null && queryResult.next()) {
                street = queryResult.getString(TABLE_STREET_COLUMN_NAME);
            }
            
        }
        catch (SQLException ex) {
            
            System.out.println("Error on getting address street in table: " + TABLE_NAME + ". " + ex);
            street = "";
            
        }
        finally {
            
            closeConnection();
            return street;
            
        }
        
    }

    @Override
    public String getCity(int postalCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Address> listAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Address data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address remove(Address data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Address oldData, Address newData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRegistered(Address data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
