package ch.rubens.address.util.Validations.TypeValidation;

/**
 * Esta interface originou-se devido a necessidade de verificar se os valores
 * passados nos inputs numéricos são realmente números ou não.
 * 
 * Ela atende aos princípios: SRP, OCP, ISP, DIP. Isso pois:
 * 
 *      * SRP: Verificar se o dado é um número
 *      * OCP: Qualquer espercificação ou mudança na forma de análise só é 
 *             necessário estende-la
 *      * ISP: For segregada das funções executadas pelas antiga validação de 
 *             inputs do PersonEditDialogController
 *      * DIP: Toda implementação para verificação de número deve implementar 
 *             está interface
 * 
 * @author rubens
 */
public interface INumberValidation {

    public boolean isNumber(String number);
    
}
