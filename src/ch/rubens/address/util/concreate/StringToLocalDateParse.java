package ch.rubens.address.util.concreate;

import ch.rubens.address.util.abstracts.IParser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author rubens
 */
public class StringToLocalDateParse implements IParser<String, LocalDate> {

    private String datePattern;
    private DateTimeFormatter dateFormatter;
    
    public StringToLocalDateParse(String datePattern) {
        
        setDatePattern(datePattern);
        
    }
    
    @Override
    public LocalDate parse(String object) {
        
        try{
            return dateFormatter.parse(object, LocalDate::from);
        }
        catch (DateTimeParseException e) {
            System.out.println("Erro em StringToLocalDateParse, método parse(String object) \n" + e);
            return null;
        }
        
    }
    
    public String getDatePattern() {
        
        return datePattern;
        
    }
    
    public void setDatePattern(String datePattern) {
        
        this.datePattern = datePattern;
        dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        
    }
    
}
