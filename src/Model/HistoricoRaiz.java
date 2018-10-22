
package Model;


public class HistoricoRaiz {
    
    private int idHistorico;
    private String dataConsulta;
    private PacienteRaiz paciente;
    private MedicoRaiz medico;
    private SalaRaiz sala;

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public PacienteRaiz getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteRaiz paciente) {
        this.paciente = paciente;
    }

    public MedicoRaiz getMedico() {
        return medico;
    }

    public void setMedico(MedicoRaiz medico) {
        this.medico = medico;
    }

    public SalaRaiz getSala() {
        return sala;
    }

    public void setSala(SalaRaiz sala) {
        this.sala = sala;
    }
    
    
    
    
    
}
