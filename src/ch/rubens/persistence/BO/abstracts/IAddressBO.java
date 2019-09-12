package ch.rubens.persistence.BO.abstracts;

import ch.rubens.address.model.concreate.Address;

/**
 *
 * @author Aluno
 */
public interface IAddressBO extends IBO<Address> {
    
    public Address getAddress(int postalCode);
    public Address remove(int postalCode);
    public Address update(int postalCode, Address newAddressData);
    public boolean isRegisterd(int postalCode);
    
    public String getStreet(int postalCode);
    public String getCity(int postalCode);
    
}
