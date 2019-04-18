package ch.rubens.address.model.concreate;

import ch.rubens.address.model.abstracts.ListWrapper;
import ch.rubens.address.model.abstracts.PersonProperty;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A classe PersonListWrapper serve para carregar e salvar os dados do XML
 * Ela implementa ListWrapper para seguir os princípios OCP e DIR.
 * @author rubens
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper implements ListWrapper<ConcreatePersonProperty> {

  private List<ConcreatePersonProperty> listPersons;

   @Override
   @XmlElement(name = "person")
   public List<ConcreatePersonProperty> getList() {
       return listPersons;
   }

   @Override
   public void setList(List<ConcreatePersonProperty> list) {
       listPersons = list;
   }
    
}
