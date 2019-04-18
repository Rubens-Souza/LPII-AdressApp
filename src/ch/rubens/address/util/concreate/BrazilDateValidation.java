package ch.rubens.address.util.concreate;

import ch.rubens.address.util.abstracts.IDateValidation;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Esta classe implementa a interface IDatevalidation de acordo com o padrão de 
 * data usado no Brasil. [Antigo método de DateUtil.isDateValid()]
 * 
 * @author rubens
 */
public class BrazilDateValidation implements IDateValidation {

    private final String DATE_PATTERN = "dd/MM/yyyy";
    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    
    @Override
    public boolean isDateValid(String dateString) {
        
        try {
            
            LocalDate ld = LocalDate.parse(dateString, DATE_FORMATTER);
            String result = ld.format(DATE_FORMATTER);
            
            return result.equals(dateString);
            
        } catch (DateTimeParseException e) {
        
            System.out.println("Erro em BrazilDateValidation, método isDateValid(String dateString) \n" + e);
            
        }
        
        return false;
    }
    
}
