package ch.rubens.address.util.Validations;

/**
 * Esta interface originou-se devido ao método de validação dos inputs de 
 * PersonEditDialogController. Ela foi separada para que ela estabeleça um 
 * contrato em relação a forma de validação de endereços.
 * 
 * Ela atende aos princípios: SRP, OCP, ISP, DIP
 * 
 * @author rubens
 */
public interface IAddressValidation {
    
    public boolean isStreetValid(String street);
    public boolean isPostalCodeValid(String postalCode);
    public boolean isCityValid(String city);
    
}
