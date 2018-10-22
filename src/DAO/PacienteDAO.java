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

public class PacienteDAO {

    private Connection connection;

    private static PacienteDAO pacienteDAO;

    public static PacienteDAO getInstancia() throws SQLException {
        if (pacienteDAO == null) {
            pacienteDAO = new PacienteDAO();
        }

        pacienteDAO.connection = ConnectionFactory.getConnection();

        return pacienteDAO;
    }

    private PacienteDAO() throws SQLException {

        connection = ConnectionFactory.getConnection();
    }

    public void pesquisar(PacienteRaiz paciente) throws SQLException {

        connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM PACIENTE WHERE id_Paciente = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, paciente.getIdPaciente());
            result = pstmt.executeQuery();

            while (result.next()) {

                paciente.setIdPaciente(result.getInt("id_Paciente"));
                paciente.setNomePaciente(result.getString("nome_Paciente"));
                paciente.setTelefone(result.getString("telefone"));
                paciente.setCpf(result.getString("cpf"));

                MedicoRaiz medico = new MedicoRaiz();
                medico.setIdMedico(result.getInt("medico"));
                MedicoDAO.getInstancia().pesquisar(medico);
                paciente.setMedico(medico);

            }

            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

    public ArrayList<PacienteRaiz> buscaTodosPeloNome(PacienteRaiz paciente) throws SQLException {

        connection = ConnectionFactory.getConnection();

        String sql = "SELECT * FROM PACIENTE WHERE nome_paciente like ?";

        ResultSet result = null;

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "%" + paciente.getNomePaciente() + "%");
            result = pstmt.executeQuery();
            ArrayList<PacienteRaiz> pacientes = new ArrayList<>();
            while (result.next()) {
                PacienteRaiz p = new PacienteRaiz();
                p.setIdPaciente(result.getInt("id_Paciente"));
                p.setNomePaciente(result.getString("nome_Paciente"));
                p.setTelefone(result.getString("telefone"));
                p.setCpf(result.getString("cpf"));

                MedicoRaiz medico = new MedicoRaiz();
                medico.setIdMedico(result.getInt("medico"));
                MedicoDAO.getInstancia().pesquisar(medico);
                p.setMedico(medico);

                pacientes.add(p);

            }

            pstmt.close();
            connection.close();
            
            return pacientes;
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }
    }

    public void pesquisarPeloNome(PacienteRaiz paciente) throws SQLException {

        connection = ConnectionFactory.getConnection();
        String sql = "SELECT * FROM PACIENTE WHERE nome_Paciente = ? ";

        ResultSet result = null;
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, paciente.getNomePaciente());
            result = pstmt.executeQuery();

            while (result.next()) {

                paciente.setIdPaciente(result.getInt("id_Paciente"));
                paciente.setNomePaciente(result.getString("nome_Paciente"));
                paciente.setTelefone(result.getString("telefone"));
                paciente.setCpf(result.getString("cpf"));

                MedicoRaiz medico = new MedicoRaiz();
                medico.setIdMedico(result.getInt("medico"));
                MedicoDAO.getInstancia().pesquisar(medico);
                paciente.setMedico(medico);

            }

            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

    public void adicionar(PacienteRaiz paciente) throws SQLException {

        String sql = "INSERT INTO PACIENTE(nome_Paciente, telefone, cpf, medico) VALUES(?,?,?,?)";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, paciente.getNomePaciente());
            pstmt.setString(2, paciente.getTelefone());
            pstmt.setString(3, paciente.getCpf());
            pstmt.setInt(4, paciente.getMedico().getIdMedico());

            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

    public void alterar(PacienteRaiz paciente) throws SQLException {

        String sql = "UPDATE PACIENTE SET nome_Paciente=?, telefone=?, cpf=?, medico=? WHERE id_Paciente=?";

        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, paciente.getNomePaciente());
            pstmt.setString(2, paciente.getTelefone());
            pstmt.setString(3, paciente.getCpf());
            pstmt.setInt(4, paciente.getMedico().getIdMedico());
            pstmt.setInt(5, paciente.getIdPaciente());
            pstmt.execute();
            pstmt.close();
            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            throw new SQLException(e.getMessage());
        }

    }

    public void deletar(Integer id) throws SQLException {

        String sql = "DELETE FROM PACIENTE WHERE id_Paciente=? ";

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

    public ArrayList<PacienteRaiz> listar() {

        String sql = "SELECT * FROM PACIENTE";

        try {

            ArrayList<PacienteRaiz> pacientes = new ArrayList<PacienteRaiz>();
            PreparedStatement pstmt = this.connection.prepareStatement(sql);
            ResultSet rlst = pstmt.executeQuery();
            while (rlst.next()) {

                PacienteRaiz pr = new PacienteRaiz();

                pr.setIdPaciente(rlst.getInt("id_Paciente"));
                pr.setNomePaciente(rlst.getString("nome_Paciente"));
                pr.setTelefone(rlst.getString("telefone"));
                pr.setCpf(rlst.getString("cpf"));

                MedicoRaiz medico = new MedicoRaiz();
                medico.setIdMedico(rlst.getInt("medico"));
                MedicoDAO.getInstancia().pesquisar(medico);
                pr.setMedico(medico);

                pacientes.add(pr);

            }
            rlst.close();
            pstmt.close();
            connection.close();

            return pacientes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
