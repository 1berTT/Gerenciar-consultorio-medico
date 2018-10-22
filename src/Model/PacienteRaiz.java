
package Model;

public class PacienteRaiz {

    private int idPaciente;
    private String nomePaciente;
    private String telefone;
    private String cpf;
    private MedicoRaiz medico;

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public MedicoRaiz getMedico() {
        return medico;
    }

    public void setMedico(MedicoRaiz medico) {
        this.medico = medico;
    }
    
    
    

    
}
