
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


public class TestRemoverPaciente {
    
    public TestRemoverPaciente() {
    }
    
    @Test
    public void testaRemoverPaciente() throws SQLException{
        PacienteRaiz pr = new PacienteRaiz();
        
        PacienteDAO.getInstancia().deletar(5);
        
        pr.setIdPaciente(5);
        PacienteDAO.getInstancia().pesquisar(pr);
        
        assertNull("", pr.getCpf());
        assertNull("", pr.getMedico());
        assertNull("", pr.getNomePaciente());
        assertNull("", pr.getTelefone());
           
    }
}
