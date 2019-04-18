package ch.rubens.address.model.abstracts;

import java.time.LocalDate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 * A interface PersonProperty serve para fazer com que a classe 
 * ConcreatePersonProperty passe a seguir o DIP e tenha um contrato em seus métdos
 * 
 * @author rubens
 */
public interface PersonProperty extends Person {

    public StringProperty getFirstNameProperty();
    
    public StringProperty getLastNameProperty();
    
    public StringProperty getStreetProperty();
    
    public StringProperty getCityProperty();
    
    public IntegerProperty getPostalCodeProperty();
    
    public ObjectProperty<LocalDate> getBirthdayProperty();
    
}
