package ch.rubens.address.view.abstracts;

/**
 * Esta interface estabelece o contrato da forma tratamento das entradas de 
 * deve ser feita. Ele originou-se devido a necessidade de classes como 
 * PersonEditDialogController validar suas entradas.
 * 
 * Ela busca atender ao: SRP, OCP, ISP, DIP
 * 
 * @author rubens
 */
public interface IInputValidation {
    
    public boolean validateInputEntries();
    public void invalidInputEntrieWarning(String text);
    
}
