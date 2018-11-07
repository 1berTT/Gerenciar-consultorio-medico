

package DAO;

import Control.ConnectionFactory;
import Model.ConsultaRaiz;
import Model.MedicoRaiz;
import Model.PacienteRaiz;
import Model.SalaRaiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConsultaDAO {
    
    
    private Connection connection;

    private static ConsultaDAO consultaDAO;

    public static ConsultaDAO getInstancia() throws SQLException {
        if (consultaDAO == null) {
            consultaDAO = new ConsultaDAO();
        }

        consultaDAO.connection = ConnectionFactory.getConnection();
        
        return consultaDAO;
    }

    public ConsultaDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    
    
    public void pesquisar(ConsultaRaiz consulta) throws SQLException {

        connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM CONSULTA WHERE id_Consulta = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, consulta.getIdConsulta());
            result = pstmt.executeQuery();

            while (result.next()) {

                consulta.setIdConsulta(result.getInt("id_Consulta"));
                consulta.setDataConsulta(result.getString("data_Consulta"));
                consulta.setHoraConsulta(result.getString("hora_Consulta"));
                
                PacienteRaiz paciente = new PacienteRaiz();
                paciente.setIdPaciente(result.getInt("paciente"));
                PacienteDAO.getInstancia().pesquisar(paciente);
                consulta.setPaciente(paciente);
                
                MedicoRaiz medico = new MedicoRaiz();
                medico.setIdMedico(result.getInt("medico"));
                MedicoDAO.getInstancia().pesquisar(medico);
                consulta.setMedico(medico);
                
                SalaRaiz sala = new SalaRaiz();
                sala.setIdSala(result.getInt("sala"));
                SalaDAO.getInstancia().pesquisar(sala);
                consulta.setSala(sala);
                
            }
            
            
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
    
    public void adicionar(ConsultaRaiz consulta) throws SQLException {

        String sql = "INSERT INTO CONSULTA(data_Consulta, hora_Consulta, paciente, medico, sala) VALUES(?,?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, consulta.getDataConsulta());
            pstmt.setString(2, consulta.getHoraConsulta());
            pstmt.setInt(3, consulta.getPaciente().getIdPaciente());
            pstmt.setInt(4, consulta.getMedico().getIdMedico());
            pstmt.setInt(5, consulta.getSala().getIdSala());
            
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
    
    public void alterar(ConsultaRaiz consulta) throws SQLException {

        String sql = "UPDATE CONSULTA SET data_Consulta=?, hora_Consulta=?, paciente=?, medico=?, sala=? WHERE id_Consulta=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, consulta.getDataConsulta());
            pstmt.setString(2, consulta.getHoraConsulta());
            pstmt.setInt(3, consulta.getPaciente().getIdPaciente());
            pstmt.setInt(4, consulta.getMedico().getIdMedico());
            pstmt.setInt(5, consulta.getSala().getIdSala());
            pstmt.setInt(6, consulta.getIdConsulta());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }
    
    
    
    public void deletar(Integer id) throws SQLException {

        String sql = "DELETE FROM CONSULTA WHERE id_Consulta=? ";

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

    
    
    public ArrayList<ConsultaRaiz> buscaTodosPelaData(ConsultaRaiz consulta) throws SQLException {

        connection = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM CONSULTA WHERE data_Consulta like ?";

        ResultSet result = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "%" + consulta.getDataConsulta()+ "%");
            result = pstmt.executeQuery();
            ArrayList<ConsultaRaiz> consultas = new ArrayList<>();
            while (result.next()) {
                ConsultaRaiz cr = new ConsultaRaiz();
                
                cr.setIdConsulta(result.getInt("id_Consulta"));
                cr.setDataConsulta(result.getString("data_Consulta"));
                cr.setHoraConsulta(result.getString("hora_Consulta"));
                
                
                PacienteRaiz paciente = new PacienteRaiz();
                paciente.setIdPaciente(result.getInt("paciente"));
                PacienteDAO.getInstancia().pesquisar(paciente);
                cr.setPaciente(paciente);
                
                MedicoRaiz medico = new MedicoRaiz();
                medico.setIdMedico(result.getInt("medico"));
                MedicoDAO.getInstancia().pesquisar(medico);
                cr.setMedico(medico);
                
                SalaRaiz sala = new SalaRaiz();
                sala.setIdSala(result.getInt("sala"));
                SalaDAO.getInstancia().pesquisar(sala);
                cr.setSala(sala);
                

                consultas.add(cr);

            }

            pstmt.close();
            connection.close();
            
            return consultas;
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }
        
    }
    
    
    
    
    public ArrayList<ConsultaRaiz> listar() {

        String sql = "SELECT * FROM CONSULTA";

        try {
            
           
            
            ArrayList<ConsultaRaiz> consultas = new ArrayList<ConsultaRaiz>();
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            ResultSet rlst = pstmt.executeQuery();
            while (rlst.next()) {
                ConsultaRaiz cr = new ConsultaRaiz();
                cr.setIdConsulta(rlst.getInt("id_Historico"));
                cr.setDataConsulta(rlst.getString("data_Consulta"));
                cr.setHoraConsulta(rlst.getString("hora_Consulta"));
                
                PacienteRaiz paciente = new PacienteRaiz();
                paciente.setIdPaciente(rlst.getInt("paciente"));
                PacienteDAO.getInstancia().pesquisar(paciente);
                cr.setPaciente(paciente);
                
                MedicoRaiz medico = new MedicoRaiz();
                medico.setIdMedico(rlst.getInt("medico"));
                MedicoDAO.getInstancia().pesquisar(medico);
                cr.setMedico(medico);
                
                SalaRaiz sala = new SalaRaiz();
                sala.setIdSala(rlst.getInt("sala"));
                SalaDAO.getInstancia().pesquisar(sala);
                cr.setSala(sala);
                
                
                consultas.add(cr);
            
            }
            rlst.close();
            pstmt.close();
            connection.close();
            
           
            
            return consultas;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
 
    
    
    
}
