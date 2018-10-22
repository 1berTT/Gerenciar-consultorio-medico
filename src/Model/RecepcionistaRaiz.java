package Model;

import DAO.RecepcionistaDAO;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class RecepcionistaRaiz {

    private int idRecepcionista;
    private String nomeRecepcionista;
    private String telefone;
    private String email;
    private String senha;

    public RecepcionistaRaiz() {

    }

    public int getIdRecepcionista() {
        return idRecepcionista;
    }

    public void setIdRecepcionista(int idRecepcionista) {
        this.idRecepcionista = idRecepcionista;
    }

    public String getNomeRecepcionista() {
        return nomeRecepcionista;
    }

    public void setNomeRecepcionista(String nomeRecepcionista) {
        this.nomeRecepcionista = nomeRecepcionista;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
