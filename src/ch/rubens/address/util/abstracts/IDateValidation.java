package ch.rubens.address.util.abstracts;

/**
 * Essa classe se originou devido ao DateUtil, que vai contra o principio SRP
 * executando mais de uma funcionalidade. [Criada devido ao antigo DateUtil]
 * 
 * Essa interface faz com que a forma de validar a data siga o SRP, OCP, ISP e
 * DIP.
 * 
 * @author rubens
 */
public interface IDateValidation {
   
    public boolean isDateValid(String dateString);
    
}
