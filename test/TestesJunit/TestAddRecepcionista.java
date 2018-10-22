
package TestesJunit;

import Model.*;
import DAO.*;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestAddRecepcionista {
    
    /*public TestAddRecepcionista() {
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
    public void testaAddRecepcionista() throws SQLException{
        RecepcionistaRaiz rr = new RecepcionistaRaiz();
        
        rr.setNomeRecepcionista("Rodolfo");
        rr.setEmail("rodolfo@gmail.com");
        rr.setSenha("segredo");
        rr.setTelefone("12345");
        
        RecepcionistaDAO.getInstancia().adicionar(rr);
        
        RecepcionistaRaiz recepcionistaCerto  = new RecepcionistaRaiz();
        recepcionistaCerto.setIdRecepcionista(2);
        
        RecepcionistaDAO.getInstancia().pesquisar(recepcionistaCerto);
        
        assertEquals(recepcionistaCerto.getNomeRecepcionista(), rr.getNomeRecepcionista());
        assertEquals(recepcionistaCerto.getEmail(), rr.getEmail());
        assertEquals(recepcionistaCerto.getSenha(), rr.getSenha());
        assertEquals(recepcionistaCerto.getTelefone(), rr.getTelefone());
               
        
        
        
    }
}
