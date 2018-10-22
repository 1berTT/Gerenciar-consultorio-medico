

package DAO;

import Control.ConnectionFactory;
import Model.HistoricoRaiz;
import Model.MedicoRaiz;
import Model.PacienteRaiz;
import Model.SalaRaiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HistoricoDAO {
    
    
     private Connection connection;

    private static HistoricoDAO historicoDAO;

    public static HistoricoDAO getInstancia() throws SQLException {
        if (historicoDAO == null) {
            historicoDAO = new HistoricoDAO();
        }

        return historicoDAO;
    }

    public HistoricoDAO() throws SQLException {

        connection = ConnectionFactory.getConnection();
    }

    
    
    public void pesquisar(HistoricoRaiz historico) throws SQLException {

        connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM HISTORICO WHERE id_Historico = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, historico.getIdHistorico());
            result = pstmt.executeQuery();

            while (result.next()) {

                historico.setIdHistorico(result.getInt("id_Historico"));
                historico.setDataConsulta(result.getString("data_Consulta"));
                
                PacienteRaiz paciente = new PacienteRaiz();
                paciente.setIdPaciente(result.getInt("paciente"));
                PacienteDAO.getInstancia().pesquisar(paciente);
                historico.setPaciente(paciente);
                
                MedicoRaiz medico = new MedicoRaiz();
                medico.setIdMedico(result.getInt("medico"));
                MedicoDAO.getInstancia().pesquisar(medico);
                historico.setMedico(medico);
                
                SalaRaiz sala = new SalaRaiz();
                sala.setIdSala(result.getInt("sala"));
                SalaDAO.getInstancia().pesquisar(sala);
                historico.setSala(sala);
                
                
                
            }
            
            
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
    
    public void adicionar(HistoricoRaiz historico) throws SQLException {

        String sql = "INSERT INTO HISTORICO(data_Consulta, paciente, medico, sala) VALUES(?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, historico.getDataConsulta());
            pstmt.setInt(2, historico.getPaciente().getIdPaciente());
            pstmt.setInt(3, historico.getMedico().getIdMedico());
            pstmt.setInt(4, historico.getSala().getIdSala());
            
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
    
    public void alterar(HistoricoRaiz historico) throws SQLException {

        String sql = "UPDATE HISTORICO SET data_Consulta=?, paciente=?, medico=?, sala=? WHERE id_Historico=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, historico.getDataConsulta());
            pstmt.setInt(2, historico.getPaciente().getIdPaciente());
            pstmt.setInt(3, historico.getMedico().getIdMedico());
            pstmt.setInt(4, historico.getSala().getIdSala());
            pstmt.setInt(5, historico.getIdHistorico());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
    
    public void deletar(Integer id) throws SQLException {

        String sql = "DELETE FROM HISTORICO WHERE id_Historico=? ";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }
    }

    
    
    
    public List<HistoricoRaiz> listar() {

        String sql = "SELECT * FROM HISTORICO";

        try {
            
           
            
            List<HistoricoRaiz> historicos = new ArrayList<HistoricoRaiz>();
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            ResultSet rlst = pstmt.executeQuery();
            while (rlst.next()) {
                HistoricoRaiz hr = new HistoricoRaiz();
                hr.setIdHistorico(rlst.getInt("id_Historico"));
                hr.setDataConsulta(rlst.getString("data_Consulta"));
                
                PacienteRaiz paciente = new PacienteRaiz();
                paciente.setIdPaciente(rlst.getInt("paciente"));
                PacienteDAO.getInstancia().pesquisar(paciente);
                hr.setPaciente(paciente);
                
                MedicoRaiz medico = new MedicoRaiz();
                medico.setIdMedico(rlst.getInt("medico"));
                MedicoDAO.getInstancia().pesquisar(medico);
                hr.setMedico(medico);
                
                SalaRaiz sala = new SalaRaiz();
                sala.setIdSala(rlst.getInt("sala"));
                SalaDAO.getInstancia().pesquisar(sala);
                hr.setSala(sala);
                
                
                historicos.add(hr);
            
            }
            rlst.close();
            pstmt.close();
            connection.close();
            
           
            
            return historicos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
 
    
    
    
}
