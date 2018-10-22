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


public class TestAddPaciente {
    
   /* public TestAddPaciente() {
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
    public void testaAddPaciente() throws SQLException{
        PacienteRaiz pr = new PacienteRaiz();
        
        MedicoRaiz mr = new MedicoRaiz();
        mr.setIdMedico(6);
        
        pr.setCpf("21");
        pr.setMedico(mr);
        pr.setTelefone("334");
        pr.setNomePaciente("Jose");
        
        PacienteDAO.getInstancia().adicionar(pr);
        
        PacienteRaiz pacienteCerto = new PacienteRaiz();
        pacienteCerto.setIdPaciente(6);
        
        PacienteDAO.getInstancia().pesquisar(pacienteCerto);
        
        assertEquals(pacienteCerto.getCpf(), pr.getCpf());
        assertEquals(pacienteCerto.getMedico().getIdMedico(), pr.getMedico().getIdMedico());
        assertEquals(pacienteCerto.getTelefone(), pr.getTelefone());
        assertEquals(pacienteCerto.getNomePaciente(), pr.getNomePaciente());
        
        
        
    }
    
}
