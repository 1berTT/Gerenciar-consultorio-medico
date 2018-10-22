package DAO;

import Control.ConnectionFactory;
import Model.MedicoRaiz;
import Model.PacienteRaiz;
import Model.SalaRaiz;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO {

    private Connection connection;

    private static MedicoDAO medicoDAO;

    public static MedicoDAO getInstancia() throws SQLException {
        if (medicoDAO == null) {
            medicoDAO = new MedicoDAO();
        }
        
        medicoDAO.connection = ConnectionFactory.getConnection();

        return medicoDAO;
    }

    private MedicoDAO() throws SQLException {
        connection = ConnectionFactory.getConnection();
    }

    public void pesquisar(MedicoRaiz medico) throws SQLException {

        String sql = "";
        sql = "SELECT * FROM MEDICO WHERE id_Medico = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, medico.getIdMedico());
            result = pstmt.executeQuery();

            while (result.next()) {

                medico.setIdMedico(result.getInt("id_Medico"));
                medico.setNomeMedico(result.getString("nome_Medico"));
                medico.setCrm(result.getString("crm"));
                medico.setEspecialidade(result.getString("especialidade"));
                medico.setHorario(result.getString("horario"));
                medico.setEmail(result.getString("email"));
                medico.setTelefone(result.getString("telefone"));

                SalaRaiz sala = new SalaRaiz();
                sala.setIdSala(result.getInt("sala"));
                SalaDAO.getInstancia().pesquisar(sala);
                medico.setSala(sala);

            }

            pstmt.close();
            connection.close();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

    }

    
    
    public void pesquisarPeloNome(MedicoRaiz medico) throws SQLException {

        String sql = "";
        sql = "SELECT * FROM MEDICO WHERE nome_Medico = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, medico.getNomeMedico());
            result = pstmt.executeQuery();

            while (result.next()) {

                medico.setIdMedico(result.getInt("id_Medico"));
                medico.setNomeMedico(result.getString("nome_Medico"));
                medico.setCrm(result.getString("crm"));
                medico.setEspecialidade(result.getString("especialidade"));
                medico.setHorario(result.getString("horario"));
                medico.setEmail(result.getString("email"));
                medico.setTelefone(result.getString("telefone"));

                SalaRaiz sala = new SalaRaiz();
                sala.setIdSala(result.getInt("sala"));
                SalaDAO.getInstancia().pesquisar(sala);
                medico.setSala(sala);

            }

            pstmt.close();
            connection.close();
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

    }
    
    
    
    public ArrayList<MedicoRaiz> buscaTodosPeloNome(MedicoRaiz medico) throws SQLException {

        connection = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM MEDICO WHERE nome_Medico like ?";

        ResultSet result = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "%" + medico.getNomeMedico()+ "%");
            result = pstmt.executeQuery();
            ArrayList<MedicoRaiz> medicos = new ArrayList<>();
            while (result.next()) {
                MedicoRaiz m = new MedicoRaiz();
                m.setIdMedico(result.getInt("id_Medico"));
                m.setNomeMedico(result.getString("nome_Medico"));
                m.setEspecialidade(result.getString("especialidade"));
                m.setCrm(result.getString("crm"));
                m.setHorario(result.getString("horario"));
                m.setEmail(result.getString("email"));
                m.setTelefone(result.getString("telefone"));
                

                SalaRaiz sala = new SalaRaiz();
                sala.setIdSala(result.getInt("sala"));
                SalaDAO.getInstancia().pesquisar(sala);
                m.setSala(sala);

                medicos.add(m);

            }

            pstmt.close();
            connection.close();
            
            return medicos;
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }
        
    }
    
    
    
    
    public void adicionar(MedicoRaiz medico) throws SQLException {

        String sql = "INSERT INTO MEDICO(nome_Medico, crm, especialidade, horario, email, telefone, sala) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, medico.getNomeMedico());
            pstmt.setString(2, medico.getCrm());
            pstmt.setString(3, medico.getEspecialidade());
            pstmt.setString(4, medico.getHorario());
            pstmt.setString(5, medico.getEmail());
            pstmt.setString(6, medico.getTelefone());
            pstmt.setInt(7, medico.getSala().getIdSala());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

    public void alterar(MedicoRaiz medico) throws SQLException {

        String sql = "UPDATE MEDICO SET nome_Medico=?, crm=?, especialidade=?, horario=?, email=?, telefone=?, sala=? WHERE id_Medico=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, medico.getNomeMedico());
            pstmt.setString(2, medico.getCrm());
            pstmt.setString(3, medico.getEspecialidade());
            pstmt.setString(4, medico.getHorario());
            pstmt.setString(5, medico.getEmail());
            pstmt.setString(6, medico.getTelefone());
            pstmt.setInt(7, medico.getSala().getIdSala());
            pstmt.setInt(8, medico.getIdMedico());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

    public void deletar(Integer id) throws SQLException {

        String sql = "DELETE FROM MEDICO WHERE id_Medico=? ";

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

    public ArrayList<MedicoRaiz> listar() {

        String sql = "SELECT * FROM MEDICO";

        try {

            ArrayList<MedicoRaiz> medicos = new ArrayList<MedicoRaiz>();
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            ResultSet rlst = pstmt.executeQuery();
            while (rlst.next()) {
                MedicoRaiz mr = new MedicoRaiz();
                mr.setIdMedico(rlst.getInt("id_Medico"));
                mr.setNomeMedico(rlst.getString("nome_Medico"));
                mr.setCrm(rlst.getString("crm"));
                mr.setHorario(rlst.getString("horario"));
                mr.setEmail(rlst.getString("email"));
                mr.setTelefone(rlst.getString("telefone"));

                SalaRaiz sala = new SalaRaiz();
                sala.setIdSala(rlst.getInt("sala"));
                SalaDAO.getInstancia().pesquisar(sala);
                mr.setSala(sala);

                medicos.add(mr);

            }
            rlst.close();
            pstmt.close();
            connection.close();

            return medicos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
