package TestesJunit;

import DAO.*;
import Model.*;
import Model.*;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestAlterarPaciente {

    @Test
    public void testaAlterarPaciente() throws SQLException{
        PacienteRaiz pr = new PacienteRaiz();
        
        MedicoRaiz mr = new MedicoRaiz();
        mr.setIdMedico(4);
        
        pr.setIdPaciente(3);
        pr.setCpf("12");
        pr.setMedico(mr);
        pr.setTelefone("334");
        pr.setNomePaciente("Josefiltness");
        
        PacienteDAO.getInstancia().alterar(pr);
        
        PacienteRaiz pacienteCerto = new PacienteRaiz();
        pacienteCerto.setIdPaciente(pr.getIdPaciente());
        
        PacienteDAO.getInstancia().pesquisar(pacienteCerto);
        
        assertEquals( pr.getCpf(), pacienteCerto.getCpf());
        assertEquals(pr.getMedico().getIdMedico(), pacienteCerto.getMedico().getIdMedico());
        assertEquals(pr.getTelefone(), pacienteCerto.getTelefone());
        assertEquals(pr.getNomePaciente(), pacienteCerto.getNomePaciente());
    }
    
}
