package ch.rubens.address.Deprecated;

import java.util.List;

/**
 * Esta interface genérica foi adicionada para que a classe PersonListWrapper:
      * Passe a seguir o OCP já que qualquer classe pode implementar IListWrapper
      * Passe a seguir o DIP tendo uma interface como referência
      * Tenha a assinatura dos métodos padronizadas
 * 
 * @author rubens
 * @param <T> Tipo de lista que será utilizada pela implementação do Wrapper
 */
public interface IListWrapper<T> {

    public List<T> getList();
    
    public void setList(List<T> list);
    
}
