package ch.rubens.address.util.Validations;

/**
 * Esta classe é a implementação da interface INameValidation de acordo com a
 * antiga forma feita pelo PersonEditDialogController. (DIP)
 * 
 * @author rubens
 */
public class NameValidation implements INameValidation {
    
    @Override
    public boolean isFisrtNameValid(String firstName) {
        return !(firstName == null || firstName.length() == 0);
    }

    @Override
    public boolean isLastNameValid(String lastName) {
        return !(lastName == null || lastName.length() == 0);
    }
    
}
