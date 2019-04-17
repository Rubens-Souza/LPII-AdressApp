package ch.rubens.address.model.concreate;

import ch.rubens.address.model.abstracts.ListWrapper;
import ch.rubens.address.model.abstracts.PersonProperty;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rubens
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper implements ListWrapper<PersonProperty> {

  private List<PersonProperty> listPersons;

   @Override
   @XmlElement(name = "person")
   public List<PersonProperty> getList() {
       return listPersons;
   }

   @Override
   public void setList(List<PersonProperty> list) {
       listPersons = list;
   }
    
}
