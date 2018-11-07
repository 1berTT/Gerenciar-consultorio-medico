
package TestesJunit;

import DAO.SalaDAO;
import Model.SalaRaiz;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestAddSala {
    
   /* public TestAddSala() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    */
    
    
    @Test
    public void testaADDSala() throws SQLException{
        
        SalaRaiz sr = new SalaRaiz();
      
        
        sr.setBloco(2);
        sr.setNumeroSala(22);
        
        SalaDAO.getInstancia().adicionar(sr);
        
        SalaRaiz salaCerta = new SalaRaiz();
        salaCerta.setIdSala(5);
        
        SalaDAO.getInstancia().pesquisar(salaCerta);
        
        assertEquals(salaCerta.getBloco(), sr.getBloco());
        assertEquals(salaCerta.getNumeroSala(), sr.getNumeroSala());
        
        
        
        
    }
    
    
}
