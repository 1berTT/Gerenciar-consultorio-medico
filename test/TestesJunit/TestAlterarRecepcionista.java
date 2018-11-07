package TestesJunit;

import DAO.RecepcionistaDAO;
import Model.RecepcionistaRaiz;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestAlterarRecepcionista {

    @Test
    public void testaAlterarRecepcionista() throws SQLException{
        RecepcionistaRaiz rr = new RecepcionistaRaiz();
   
        
        rr.setIdRecepcionista(2);
        rr.setNomeRecepcionista("Rodo");
        rr.setEmail("rodo@gmail.com");
        rr.setSenha("segredodsda");
        rr.setTelefone("12332");
        
        RecepcionistaDAO.getInstancia().alterar(rr);
        
        RecepcionistaRaiz recepcionistaCerto  = new RecepcionistaRaiz();
        recepcionistaCerto.setIdRecepcionista(rr.getIdRecepcionista());
        
        RecepcionistaDAO.getInstancia().pesquisar(recepcionistaCerto);
        
        assertEquals(rr.getNomeRecepcionista(), recepcionistaCerto.getNomeRecepcionista());
        assertEquals(rr.getEmail(), recepcionistaCerto.getEmail());
        assertEquals(rr.getSenha(), recepcionistaCerto.getSenha());
        assertEquals(rr.getTelefone(), recepcionistaCerto.getTelefone());
        
    }
    
}
