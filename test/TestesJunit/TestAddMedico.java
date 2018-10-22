
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
        SalaRaiz sr = new SalaRaiz();
        
        sr.setIdSala(3);
        
        mr.setNomeMedico("Robisdusdbud");
        mr.setCrm("hehehsdsdee");
        mr.setEspecialidade("Radiologista");
        mr.setHorario("7 as 8");
        mr.setEmail("robesdadwgmail.com");
        mr.setTelefone("9988");
       
        mr.setSala(sr);
        
        MedicoDAO.getInstancia().adicionar(mr);
        
        MedicoRaiz medicoCerto = new MedicoRaiz();
        
        medicoCerto.setIdMedico(10);
        
        MedicoDAO.getInstancia().pesquisar(medicoCerto);
        
        assertEquals(mr.getNomeMedico(), medicoCerto.getNomeMedico());
        assertEquals(mr.getCrm(), medicoCerto.getCrm());
        assertEquals(mr.getEspecialidade(), medicoCerto.getEspecialidade());
        assertEquals(mr.getHorario(), medicoCerto.getHorario());
        assertEquals(mr.getEmail(), medicoCerto.getEmail());
        assertEquals(mr.getTelefone(), medicoCerto.getTelefone());
        assertEquals(mr.getSala().getIdSala(), medicoCerto.getSala().getIdSala());
        
    }
    
    
    
}
