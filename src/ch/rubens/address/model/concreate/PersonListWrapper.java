package ch.rubens.address.model.concreate;

import ch.rubens.address.model.abstracts.IListWrapper;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * A classe PersonListWrapper serve para carregar e salvar os dados do XML
 * 
 * Ela implementa IListWrapper para seguir os princípios OCP e DIR.
 * 
 * @author rubens
 */
@XmlRootElement(name = "persons")
public class PersonListWrapper implements IListWrapper<PersonPropertyAdapter> {

  private List<PersonPropertyAdapter> listPersons;

   @Override
   @XmlElement(name = "person")
   public List<PersonPropertyAdapter> getList() {
       return listPersons;
   }

   @Override
   public void setList(List<PersonPropertyAdapter> list) {
       listPersons = list;
   }
    
}
