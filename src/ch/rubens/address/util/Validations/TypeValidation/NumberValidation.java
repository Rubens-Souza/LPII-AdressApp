package ch.rubens.address.util.Validations.TypeValidation;

/**
 * Esta classe é a implementação da interface INumberValidation de acordo com a
 * antiga forma feita pelo PersonEditDialogController (DIP)
 * 
 * @author rubens
 */
public class NumberValidation implements INumberValidation {

    @Override
    public boolean isNumber(String number) {
        
        try {
           Integer.parseInt(number);
        } 
        catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
}
