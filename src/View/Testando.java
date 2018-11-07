
package View;

import DAO.ConsultaDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DAO.RecepcionistaDAO;
import DAO.SalaDAO;
import Model.ConsultaRaiz;
import Model.MedicoRaiz;
import Model.PacienteRaiz;
import Model.RecepcionistaRaiz;
import Model.SalaRaiz;
import java.sql.SQLException;


public class Testando {

    public static void main(String args[]) throws SQLException{
        
        /*
        SalaRaiz sr = new SalaRaiz();
        
        sr.setIdSala(3);
        
        sr.setBloco(4);
        sr.setNumeroSala(22);
        
        SalaDAO sd = new SalaDAO();
        
        sd.deletar(sr.getIdSala());
        */
        
       
        
        /*
        RecepcionistaRaiz r = new RecepcionistaRaiz();
        
        r.setIdRecepcionista(2);
        
        r.setEmail("aaaaaa");
        r.setNomeRecepcionista("Willi Wonka");
        r.setSenha("jbbbbbb");
        r.setTelefone("jbbbba");
        
        RecepcionistaDAO rd = new RecepcionistaDAO();
        
        rd.deletar(r.getIdRecepcionista());
        
        */
        
        
        /*
        MedicoRaiz mr = new MedicoRaiz();
        
        mr.setIdMedico(3);
        
        mr.setCrm("maneiro");
        mr.setEmail("gmail");
        mr.setEspecialidade("podologo");
        mr.setNomeMedico("Ranxswe");
        mr.setTelefone("11111222");
        mr.setHorario("o dia todo");
        
        SalaRaiz sr = new SalaRaiz();
        sr.setIdSala(1);
        
        mr.setSala(sr);
        
        
        MedicoDAO md = new MedicoDAO();
        
        md.deletar(mr.getIdMedico());
        */
        
        /*
        PacienteRaiz p = new PacienteRaiz();
        
        p.setIdPaciente(3);
        
       p.setEmail("hotmail");
       p.setNomePaciente("eyevaldoe");
       p.setTelefone("wd");
       
       MedicoRaiz m = new MedicoRaiz();
       m.setIdMedico(1);
       
       p.setMedico(m);
       
       
        PacienteDAO pd = new PacienteDAO();
        
        pd.listar();
       
        */
        
        /*
        MedicoRaiz m = new MedicoRaiz();
        m.setIdMedico(1);
        
        PacienteRaiz p = new PacienteRaiz();
        p.setIdPaciente(1);
        
        SalaRaiz s = new SalaRaiz();
        s.setIdSala(1);
        
        ConsultaRaiz hr = new ConsultaRaiz();
        
        hr.setDataConsulta("12ffr");
        hr.setMedico(m);
        hr.setPaciente(p);
        hr.setSala(s);
        hr.setIdHistorico(2);
        
        ConsultaDAO hd = new ConsultaDAO();
        
        
        hd.listar();
        */
        
        
        //JLogin jl = new JLogin();
        //jl.setVisible(true);
        
        ConsultaRaiz cr = new ConsultaRaiz();
        
        cr.setDataConsulta("12/21/2018");
        cr.setHoraConsulta("13:00");
        
        MedicoRaiz mr = new MedicoRaiz();
        SalaRaiz sr = new SalaRaiz();
        PacienteRaiz pr = new PacienteRaiz();
        
        mr.setIdMedico(1);
        MedicoDAO.getInstancia().pesquisar(mr);
        
        pr.setIdPaciente(1);
        PacienteDAO.getInstancia().pesquisar(pr);
        
        sr.setIdSala(2);
        SalaDAO.getInstancia().pesquisar(sr);
        
        cr.setMedico(mr);
        cr.setPaciente(pr);
        cr.setSala(sr);
        
        ConsultaDAO.getInstancia().adicionar(cr);
        
    }
    
}
