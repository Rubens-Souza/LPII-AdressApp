package ch.rubens.persistence.JDBC.DAO;

import ch.rubens.address.model.concreate.Person;
import ch.rubens.persistence.DAO.abstracts.IPersonDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rubens
 */
public class PersonJDBCDAO extends JDBCDAO implements IPersonDAO {

    private final String TABLE_NAME = "person";
    private final String TABLE_ID_COLUMN_NAME = "id";
    private final String TABLE_FIRST_NAME_COLUMN_NAME = "firstName";
    private final String TABLE_LAST_NAME_COLUMN_NAME = "lastName";
    private final String TABLE_BIRTHDAY_COLUMN_NAME = "birthday";
    
    @Override
    public Person getPerson(int personId) {
        
        String query = "SELECT * FROM " + TABLE_NAME + 
                       " WHERE " +  TABLE_ID_COLUMN_NAME + " = " + "'" + personId + "';";
        
        openConnection();
        Connection connection = getConnection();
        
        Person person = null;
        
        try {
            
            PreparedStatement queryCall = connection.prepareStatement(query);
            ResultSet queryResult = queryCall.executeQuery();

            if (queryResult != null && queryResult.next()) {
                
                Integer id = queryResult.getInt(TABLE_ID_COLUMN_NAME);
                String firstName = queryResult.getString(TABLE_FIRST_NAME_COLUMN_NAME);
                String lastName = queryResult.getString(TABLE_LAST_NAME_COLUMN_NAME);
                LocalDate birthday = queryResult.getObject(TABLE_BIRTHDAY_COLUMN_NAME, LocalDate.class);
                
                person = new Person(id);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setBirthday(birthday);
                
            }
            
        } 
        catch (SQLException ex) {
            
            System.out.println("Error on getting person from table: " + TABLE_NAME + ". " + ex);
            person = null;
            
        }
        finally {
            
            closeConnection();
            return person;
            
        }
        
    }

    @Override
    public Person remove(int personId) {
        
        String query = "DELETE FROM " + TABLE_NAME + 
                            " WHERE " + TABLE_ID_COLUMN_NAME + " = " + personId;
        
        openConnection();
        Connection connection = getConnection();
        
        Person personDeleted = null;
        
        try {
            
            personDeleted = getPerson(personId);
            PreparedStatement queryCall = connection.prepareStatement(query);
            queryCall.execute();
            
        }
        catch (SQLException ex) {
            
            System.out.println("Error on person deletion in table: " + TABLE_NAME + ". " + ex);
            personDeleted = null;
            
        }
        finally {
            
            closeConnection();
            return personDeleted;
            
        }
        
    }

    @Override
    public Person update(int personId, Person newPersonData) {
        
        String query = "update " + TABLE_NAME +
                       " set " + 
                            TABLE_ID_COLUMN_NAME + " = " + "'" + newPersonData.getId() + "', " +
                            TABLE_FIRST_NAME_COLUMN_NAME + " = " + "'" + newPersonData.getFirstName() + "', " +
                            TABLE_LAST_NAME_COLUMN_NAME + " = " + "'" + newPersonData.getLastName() + "', " +
                            TABLE_BIRTHDAY_COLUMN_NAME + " = " + "'" + newPersonData.getBirthday() + "'" +
                       " where " + TABLE_ID_COLUMN_NAME + " = " + "'" + personId + "'";
        
        openConnection();
        Connection connection = getConnection();
        
        Person oldPersonData = null;
        
        try {
            
            oldPersonData = getPerson(personId);
            PreparedStatement queryCall = connection.prepareStatement(query);
            queryCall.executeUpdate();
            
        } 
        catch (SQLException ex) {
            
            System.out.println("Error on person updating in table: " + TABLE_NAME + ". " + ex);
            oldPersonData = null;
            
        }
        finally {
            
            closeConnection();
            return oldPersonData;
            
        }
        
        
    }

    @Override
    public boolean isRegistered(int personId) {
       
        Person personSearched = getPerson(personId);
        return (personSearched != null);
        
    }

    @Override
    public String getPersonFirstName(int personId) {
        
        String query = "SELECT " + TABLE_FIRST_NAME_COLUMN_NAME + " FROM " + TABLE_NAME 
                            + " WHERE " + TABLE_ID_COLUMN_NAME + " = " + "'" + personId + "';";
        
        openConnection();
        Connection connection = getConnection();
        
        String firstName = "";
        
        try {
            
            PreparedStatement queryCall = connection.prepareStatement(query);
            ResultSet queryResult = queryCall.executeQuery();
            
            if (queryResult != null && queryResult.next()) {
                firstName = queryResult.getString(TABLE_FIRST_NAME_COLUMN_NAME);
            }
            
        }
        catch (SQLException ex) {
            
            System.out.println("Error on getting person first name in table: " + TABLE_NAME + ". " + ex);
            firstName = "";
            
        }
        finally {
            
            closeConnection();
            return firstName;
            
        }
        
    }

    @Override
    public String getPersonLastName(int personId) {
        
        String query = "SELECT " + TABLE_LAST_NAME_COLUMN_NAME + " FROM " + TABLE_NAME 
                            + " WHERE " + TABLE_ID_COLUMN_NAME + " = " + "'" + personId + "';";
        
        openConnection();
        Connection connection = getConnection();
        
        String lastName = "";
        
        try {
            
            PreparedStatement queryCall = connection.prepareStatement(query);
            ResultSet queryResult = queryCall.executeQuery();
            
            if (queryResult != null && queryResult.next()) {
                lastName = queryResult.getString(TABLE_LAST_NAME_COLUMN_NAME);
            }
            
        }
        catch (SQLException ex) {
            
            System.out.println("Error on getting person last name in table: " + TABLE_NAME + ". " + ex);
            lastName = "";
            
        }
        finally {
            
            closeConnection();
            return lastName;
            
        }
        
    }

    @Override
    public LocalDate getPersonBirthday(int personId) {
        
        String query = "SELECT " + TABLE_BIRTHDAY_COLUMN_NAME + " FROM " + TABLE_NAME 
                            + " WHERE " + TABLE_ID_COLUMN_NAME + " = " + "'" + personId + "';";
        
        openConnection();
        Connection connection = getConnection();
        
        LocalDate birthday = null;
        
        try {
            
            PreparedStatement queryCall = connection.prepareStatement(query);
            ResultSet queryResult = queryCall.executeQuery();
            
            if (queryResult != null && queryResult.next()) {
                birthday = queryResult.getObject(TABLE_BIRTHDAY_COLUMN_NAME, LocalDate.class);
            }
            
        }
        catch (SQLException ex) {
            
            System.out.println("Error on getting person birthday in table: " + TABLE_NAME + ". " + ex);
            birthday = null;
            
        }
        finally {
            
            closeConnection();
            return birthday;
            
        }
        
    }

    @Override
    public List<Person> listAll() {
        
        String query = "SELECT * FROM " + TABLE_NAME;
        
        openConnection();
        Connection connection = getConnection();
        ArrayList<Person> personsList = new ArrayList<Person>();
        
        try {
            
            PreparedStatement queryCall = connection.prepareStatement(query);
            ResultSet queryResult = queryCall.executeQuery();
            
            while (queryResult.next()) {
                
                Integer id = queryResult.getInt(TABLE_ID_COLUMN_NAME);
                String firstName = queryResult.getString(TABLE_FIRST_NAME_COLUMN_NAME);
                String lastName = queryResult.getString(TABLE_LAST_NAME_COLUMN_NAME);
                LocalDate birthday = queryResult.getObject(TABLE_BIRTHDAY_COLUMN_NAME, LocalDate.class);
                
                Person person = new Person(id);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setBirthday(birthday);
                
                personsList.add(person);
                
            }
            
        } 
        catch (SQLException ex) {
            
            System.out.println("Error when recovering all persons from Database");
            personsList.clear();
            
        }
        finally {
            
            closeConnection();
            return personsList;
            
        }
        
    }

    @Override
    public boolean add(Person data) {
        
        boolean wasPersonInserted = false;
        
        String query = "INSERT INTO " + TABLE_NAME + 
                          " VALUES (" +
                                data.getId() + ", " +
                                "'" + data.getFirstName() + "', " + 
                                "'" + data.getLastName() + "', " +
                                "'" + data.getBirthday().toString() + "');";
        
        openConnection();
        Connection connection = getConnection();
        
        try {
            
            PreparedStatement queryCall = connection.prepareStatement(query);
            queryCall.execute();
            
            wasPersonInserted = true;
            
        }
        catch (SQLException ex) {
            
            System.out.println("Error to insert a peron on " + TABLE_NAME + ": " + ex);
            wasPersonInserted = false;
            
        }
        finally {
            
            closeConnection();
            
            return wasPersonInserted;
            
        }
        
    }

    @Override
    public Person remove(Person data) {
        
        return remove(data.getId());
        
    }

    @Override
    public boolean update(Person oldData, Person newData) {
        
        return  update(oldData.getId(), newData) != null;
        
    }

    @Override
    public boolean isRegistered(Person data) {
        
        return isRegistered(data.getId());
        
    }
    
}
