package ch.rubens.address.model;

import ch.rubens.address.model.concreate.PersonProperty;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rubens
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper {

  private List<PersonProperty> persons;  
  
  @XmlElement(name = "person")
  public List<PersonProperty> getPersons(){ return persons; }
  
  public void setPersons(List<PersonProperty> persons) { this.persons = persons; }
    
}
