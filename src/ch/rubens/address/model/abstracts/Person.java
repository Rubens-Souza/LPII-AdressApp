package ch.rubens.address.model.abstracts; 
 
import java.time.LocalDate; 
 
/** 
 * Transformei a classe person em uma interface para que ela passe a seguir o  
 * principio DIP e OCP. 
 *  
 * Além disso, eu a dividi em duas implementações, pois anteriormente ela tinha 
 * atributos para descrever uma abstração de pessoa e atributos para serem usados 
 * na interface. Assim, tendo mais de uma responsabilidade. SRP 
 *  
 * Segue (SRP) (OCP) (DIP) 
 * @author rubens 
 */ 
public interface Person { 
 
    public String getFirstName(); 
    public String getLastName(); 
    public String getStreet(); 
    public String getCity(); 
    public Integer getPostalCode(); 
    public LocalDate getBirthday(); 
     
    public void setFirstName(String name); 
    public void setLastName(String name); 
    public void setStreet(String street); 
    public void setCity(String city); 
    public void setPostalCode(Integer postalCode); 
    public void setBirthday(LocalDate birthdayDate); 
     
} 
