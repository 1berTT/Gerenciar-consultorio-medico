
package Model;


public class ConsultaRaiz {
    
    private int idConsulta;
    private String dataConsulta;
    private String horaConsulta;
    private PacienteRaiz paciente;
    private MedicoRaiz medico;
    private SalaRaiz sala;

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta = horaConsulta;
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
