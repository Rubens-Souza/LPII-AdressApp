package ch.rubens.address.util.concreate;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author rubens
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }
    
    @Override
    public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
    
}
