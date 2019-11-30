package ch.rubens.address.util.Validations;

import ch.rubens.address.util.Validations.IAddressValidation;

/**
 * Esta classe é a implementação da interface IAddressValidation de acordo com a
 * antiga forma feita pelo PersonEditDialogController. (DIP)
 * 
 * @author rubens
 */
public class AddressValidation implements IAddressValidation {
    
    @Override
    public boolean isStreetValid(String street) {
        return !(street == null || street.length() == 0);
    }

    @Override
    public boolean isPostalCodeValid(String postalCode) {
        
        INumberValidation numberValidator = new NumberValidation();
        
        boolean isEmpty = (postalCode == null || postalCode.length() == 0);
        boolean postalCodeIsNumber = numberValidator.isNumber(postalCode);
        
        return (postalCodeIsNumber && !isEmpty);
    }

    @Override
    public boolean isCityValid(String city) {
        return !(city == null || city.length() == 0);
    }
    
}
