package ch.rubens.address.view.abstracts;

import ch.rubens.address.model.abstracts.IPerson;

/**
 * Esta interface é responsável por exibir ou ocultar os dados na tela em que é
 * exibida. (SRP)
 * 
 * Ela segrega a função do controller de acordo com o princípio ISP e busca atender
 * o OCP e o DIP.
 * 
 * @author rubens
 */
public interface IShowPersonInfo {
    
    public void loadInfo(IPerson person);
    public void hideInfo();
    
}
