
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



public class TestRemoverSala {
    
    @Test 
    public void testaRemoverSala() throws SQLException{
       
        SalaRaiz sr = new SalaRaiz();
        
        //sd.deletar(5);
        
        sr.setIdSala(5);
        SalaDAO.getInstancia().pesquisar(sr);
        
        
        assertEquals(0, sr.getNumeroSala());
        assertEquals(0, sr.getBloco());
        
    }
    
}
