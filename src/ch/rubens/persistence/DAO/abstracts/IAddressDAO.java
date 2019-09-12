package ch.rubens.persistence.DAO.abstracts;

import ch.rubens.address.model.concreate.Address;

/**
 *
 * @author Aluno
 */
public interface IAddressDAO extends IDAO<Address> {

    public Address getAddress(int postalCode);
    public Address remove(int postalCode);
    public Address update(int postalCode, Address newAddressData);
    public boolean isRegistered(int postalCode);
    
    public String getStreet(int postalCode);
    public String getCity(int postalCode);
    
}
