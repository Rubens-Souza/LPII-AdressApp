package ch.rubens.address.view.abstracts;

/**
 * Esta interface é responsável por fazer qualquer operação relacionada a Person
 * exibida no PersonOverviewController. (SRP)
 * 
 * Ela segrega a função do controller de acordo com o princípio ISP e busca atender
 * o OCP e o DIP.
 * 
 * @author rubens
 */
public interface IPersonManipulation {

    public void deletePerson();
    public void newPerson();
    public void editPerson();
    
}
