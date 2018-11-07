
package TestesJunit;

import DAO.MedicoDAO;
import Model.MedicoRaiz;
import Model.SalaRaiz;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestAddMedico {
    
    public TestAddMedico() {
    }
    
   /* @BeforeClass
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
    public void testaAddMedico() throws SQLException{
        MedicoRaiz mr = new MedicoRaiz();
        
        mr.setNomeMedico("Francisco");
        mr.setCrm("233-443-121-45");
        mr.setEspecialidade("Cirurgiao");
        
        mr.setEmail("francisco@gmail.com");
        mr.setTelefone("(88) 9 9988-7766");
       
       
        
        MedicoDAO.getInstancia().adicionar(mr);
        
        MedicoRaiz medicoCerto = new MedicoRaiz();
        
        medicoCerto.setIdMedico(25);
        
        MedicoDAO.getInstancia().pesquisar(medicoCerto);
        
        assertEquals(mr.getNomeMedico(), medicoCerto.getNomeMedico());
        assertEquals(mr.getCrm(), medicoCerto.getCrm());
        assertEquals(mr.getEspecialidade(), medicoCerto.getEspecialidade());
        assertEquals(mr.getEmail(), medicoCerto.getEmail());
        assertEquals(mr.getTelefone(), medicoCerto.getTelefone());
       
        
    }
    
    
    
}
