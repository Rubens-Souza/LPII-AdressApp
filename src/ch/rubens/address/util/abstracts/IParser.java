package ch.rubens.address.util.abstracts;

/**
 * IParser é uma interface para padronizar a assinatura da conversão, mas com a
 * implementação livre para alterar de acordo com a necessidade. [Criada devido 
 * ao antigo DateUtil]
 *
 * Segue os princípios SRP, OCP, DIP
 * 
 * @author rubens
 * @param <ParseeType> Tipo deverá ser convertido
 * @param <TargetType> Tipo que será resultante da conversão
 */
public interface IParser<ParseeType, TargetType> {
    
    public TargetType parse(ParseeType object);
    
}
