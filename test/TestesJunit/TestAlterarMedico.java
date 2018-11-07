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


public class TestAlterarMedico {

@Test
public void testaAlterarMedico() throws SQLException{
        MedicoRaiz mr = new MedicoRaiz();        
        
        mr.setIdMedico(6);
        mr.setNomeMedico("Romero");
        mr.setCrm("craque");
        mr.setEspecialidade("artilheiro");
        
        mr.setEmail("romero@gmail.com");
        mr.setTelefone("2424");
        
        
        MedicoDAO.getInstancia().alterar(mr);
        
        MedicoRaiz medicoCerto = new MedicoRaiz();
        
        medicoCerto.setIdMedico(mr.getIdMedico());
        
        MedicoDAO.getInstancia().pesquisar(medicoCerto);
        
        assertEquals(mr.getNomeMedico(), medicoCerto.getNomeMedico());
        assertEquals(mr.getCrm(), medicoCerto.getCrm());
        assertEquals(mr.getEspecialidade(), medicoCerto.getEspecialidade());
        assertEquals(mr.getEmail(), medicoCerto.getEmail());
        assertEquals(mr.getTelefone(), medicoCerto.getTelefone());
        
        
    
}

}
