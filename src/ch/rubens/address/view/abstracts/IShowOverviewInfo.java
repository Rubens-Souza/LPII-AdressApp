package ch.rubens.address.view.abstracts;

import ch.rubens.address.model.abstracts.Person;

/**
 * Esta interface é responsável por exibir ou ocultar os dados na tela que é exibida
 * pelo PersonOverviewController. (SRP)
 * 
 * Ela segrega a função do controller de acordo com o princípio ISP e busca atender
 * o OCP e o DIP.
 * 
 * @author rubens
 */
public interface IShowOverviewInfo {
    
    public void loadInfo(Person person);
    public void hideInfo();
    
}
