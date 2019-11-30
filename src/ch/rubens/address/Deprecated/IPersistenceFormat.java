package ch.rubens.address.Deprecated;

import java.util.List;

/**
 *
 * Esta classe serve para estabelecer uma interface padrão para as diferentes formas
 * de salvar dados do sistema.
 * 
 * @author rubens
 * @param <T> Forma que o dado será salvo
 * @param <G> Forma que o dado será carregado
 */
public interface IPersistenceFormat<T, G> {

    public void save(T data);
    
    public List load(G data);
    
}
