
package TestesJunit;

import DAO.*;
import Model.MedicoRaiz;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestRemoverMedico {
    
    @Test
    public void testaRemoverMedico() throws SQLException{
        MedicoDAO.getInstancia().deletar(3);
        
        MedicoRaiz mr = new MedicoRaiz();
        mr.setIdMedico(3);
        
        MedicoDAO.getInstancia().pesquisar(mr);
        
        assertNull("", mr.getEmail());
        assertNull("", mr.getCrm());
        assertNull("", mr.getNomeMedico());
        assertNull("", mr.getTelefone());
        assertNull("", mr.getEspecialidade());
        
        
    
        
    }
    
}
