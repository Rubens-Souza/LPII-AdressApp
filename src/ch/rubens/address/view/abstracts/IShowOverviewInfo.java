package ch.rubens.address.view.abstracts;

import ch.rubens.address.model.abstracts.Person;

/**
 *
 * @author rubens
 */
public interface IShowOverviewInfo {
    
    public void loadInfo(Person person);
    public void hideInfo();
    
}
