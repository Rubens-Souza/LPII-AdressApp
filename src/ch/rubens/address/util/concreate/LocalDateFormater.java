package ch.rubens.address.util.concreate;

import ch.rubens.address.util.abstracts.IFormater;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Esta classe implementa a interface IFormater para converter uma LocalDate em
 * uma String. [Antigo métdo do DateUtil.format()]
 * 
 * @author rubens
 */
public class LocalDateFormater implements IFormater<LocalDate> {

    private String datePattern;
    private DateTimeFormatter dateFormatter;
    
    public LocalDateFormater(String datePattern) {
        
        setDatePatter(datePattern);
        
    }
    
    @Override
    public String format(LocalDate object) {
        
        if (object == null)
            return null;
        
        return dateFormatter.format(object);
        
    }
    
   public String getDatePattern() {
        
        return datePattern;
        
    }
    
    public void setDatePatter(String datePattern) {
        
        this.datePattern = datePattern;
        dateFormatter = DateTimeFormatter.ofPattern(datePattern);
        
    }
    
}
