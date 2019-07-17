package ch.rubens.address.model.abstracts;

import java.util.Collection;
import javafx.collections.ObservableList;

/**
 * Esta interface busca usar o padrão singleton na lista de pessoas (antes presente no main)
 * para que ela possa ser acessada por qualquer código sem necessecidade de uma referência
 * direta. Além de ser uma informção única que deve ser compartilhada entre eles.
 * 
 * @author rubens
 */
public interface IPersonListSingleton {
    
    public void addPerson(IPerson person);
    public void addAll(Collection<IPerson> list);
    
    public IPerson removePerson(int index);
    public IPerson removePerson(IPerson person);
    
    public IPerson getPerson(int index);
    public IPerson getPerson(String firstName);
    
    public void clear();
    
    public ObservableList<IPerson> getObservableList();
    
}
