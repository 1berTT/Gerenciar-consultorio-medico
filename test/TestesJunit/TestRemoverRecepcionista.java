
package TestesJunit;

import DAO.*;
import Model.*;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestRemoverRecepcionista {
    
    public TestRemoverRecepcionista() {
    }
    
    @Test
    public void testaRemoverRecepcionista() throws SQLException{
       
        RecepcionistaRaiz rr = new RecepcionistaRaiz();
        
        RecepcionistaDAO.getInstancia().deletar(2);
        
        rr.setIdRecepcionista(2);
        RecepcionistaDAO.getInstancia().pesquisar(rr);
        
        assertNull("", rr.getEmail());
        assertNull("", rr.getNomeRecepcionista());
        assertNull("", rr.getSenha());
        assertNull("", rr.getTelefone());
        
    }
}
