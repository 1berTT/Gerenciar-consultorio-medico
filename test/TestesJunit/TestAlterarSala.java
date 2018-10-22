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

public class TestAlterarSala {

    @Test 
    public void testaAlterarSala() throws SQLException{
        SalaRaiz sr = new SalaRaiz();
        
        
        sr.setIdSala(3);
        sr.setBloco(222);
        sr.setNumeroSala(321);
        
        SalaDAO.getInstancia().alterar(sr);
        
        SalaRaiz salaCerta = new SalaRaiz();
        salaCerta.setIdSala(sr.getIdSala());
        
        SalaDAO.getInstancia().pesquisar(salaCerta);
        
        assertEquals(sr.getBloco(), salaCerta.getBloco());
        assertEquals(sr.getNumeroSala(), salaCerta.getNumeroSala());
    }
    
}
