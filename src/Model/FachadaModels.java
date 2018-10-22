package Model;

import DAO.*;
import View.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JDesktopPane;

public class FachadaModels {

    private RecepcionistaRaiz usuario;

    private ArrayList<MedicoRaiz> medicos = new ArrayList<>();
    private ArrayList<PacienteRaiz> pacientes = new ArrayList<>();
    private ArrayList<SalaRaiz> salas = new ArrayList<>();
    
    
    //GETTERS E SETERS DOS ARRAY LIST DE USADOS NO OBSERVER
    public void setPacientes(ArrayList<PacienteRaiz> pacientes) {
        this.pacientes = pacientes;
    }

    public ArrayList<PacienteRaiz> getPacientes() {
        return this.pacientes;
    }

    public void setMedicos(ArrayList<MedicoRaiz> medicos) {
        this.medicos = medicos;
    }

    public ArrayList<MedicoRaiz> getMedicos() {
        return this.medicos;
    }

    public void setSalas(ArrayList<SalaRaiz> salas){
        this.salas = salas;
    }
    
    public ArrayList<SalaRaiz> getSalas(){
        return this.salas;
    }
    
    
    // MÉTODOS DO RECEPCIONISTA
    public RecepcionistaRaiz getUsuario() {
        return usuario;
    }

    public void logar(String usuario, String senha) throws Exception {
        if (usuario != null && senha != null) {
            this.usuario = new RecepcionistaRaiz();
            this.usuario.setIdRecepcionista(Integer.parseInt(usuario));
            RecepcionistaDAO.getInstancia().pesquisar(this.usuario);
        }
    }

    
    
    
    
    public void abreTela(String tela) {
        if (tela != null) {
            if (tela.equals("PRINCIPAL")) {
                JPrincipal principal = new JPrincipal(this);
                principal.setVisible(true);
            }
        }
    }

    //MÉTODOS DA TELA CADASTRAR MEDICO
    public void cadastrarMedico(String nome, String crm, String especialidade, String horario, String email,
            String telefone, String sala) throws SQLException {

        MedicoRaiz medico = new MedicoRaiz();

        medico.setNomeMedico(nome);
        medico.setCrm(crm);
        medico.setEspecialidade(especialidade);
        medico.setHorario(horario);
        medico.setEmail(email);
        medico.setTelefone(telefone);

        medico.setSala(this.buscaSala(sala));

        MedicoDAO.getInstancia().adicionar(medico);
    }

    // MÉTODOS DA TELA CADASTRAR SALA
    public void cadastrarSala(String bloco, String numeroSala) throws SQLException {

        SalaRaiz sala = new SalaRaiz();

        sala.setBloco(Integer.parseInt(bloco));
        sala.setNumeroSala(Integer.parseInt(numeroSala));

        SalaDAO.getInstancia().adicionar(sala);

    }

    // MÉTODOS DA TELA CADASTRAR PACIENTE
    public void cadastrarPaciente(String nome, String telefone, String cpf, String medico) throws SQLException {

        PacienteRaiz paciente = new PacienteRaiz();

        paciente.setNomePaciente(nome);
        paciente.setTelefone(telefone);
        paciente.setCpf(cpf);

        MedicoRaiz mr = new MedicoRaiz();
        mr.setNomeMedico(medico);
        MedicoDAO.getInstancia().pesquisarPeloNome(mr);

        paciente.setMedico(mr);

        PacienteDAO.getInstancia().adicionar(paciente);
    }

    // MÉTODOS BUSCAR
    public SalaRaiz buscaSala(String sala) throws SQLException {
        SalaRaiz s = new SalaRaiz();
        s.setIdSala(Integer.parseInt(sala));
        SalaDAO.getInstancia().pesquisar(s);
        return s;
    }
    
    public void buscarSalas() throws SQLException{
        this.salas = SalaDAO.getInstancia().listar();
    }
    
    public void buscarMedicos() throws SQLException {
        this.medicos = MedicoDAO.getInstancia().listar();
    }

    public void buscarPacientes() throws SQLException {
        this.pacientes = PacienteDAO.getInstancia().listar();
    }

    // buscar pelo codigo, onde entra o observer
    public void buscarMedico(int codigo) throws SQLException {
        MedicoRaiz medico = new MedicoRaiz();
        medico.setIdMedico(codigo);
        MedicoDAO.getInstancia().pesquisar(medico);

        if (medico.getSala() != null) {
            this.medicos.add(medico);
        }
    }

    public void buscarMedico(String nome) throws SQLException {
        MedicoRaiz medico = new MedicoRaiz();
        medico.setNomeMedico(nome);

        this.medicos = MedicoDAO.getInstancia().buscaTodosPeloNome(medico);
    }

    public void buscarPaciente(String nome) throws SQLException {
        PacienteRaiz paciente = new PacienteRaiz();
        paciente.setNomePaciente(nome);

        this.pacientes = PacienteDAO.getInstancia().buscaTodosPeloNome(paciente);
    }

    public void buscarPaciente(int codigo) throws SQLException {
        PacienteRaiz paciente = new PacienteRaiz();
        paciente.setIdPaciente(codigo);
        PacienteDAO.getInstancia().pesquisar(paciente);

        if (paciente.getMedico() != null) {
            this.pacientes.add(paciente);
        }
    }

    public MedicoRaiz buscaMedico(String medico) throws SQLException {
        MedicoRaiz m = new MedicoRaiz();
        m.setIdMedico(Integer.parseInt(medico));
        MedicoDAO.getInstancia().pesquisar(m);
        return m;
    }

    
    // JIF VERIFICAR MEDICO
    
    public void excluirMedico(String codigo) throws SQLException {
        MedicoDAO.getInstancia().deletar(Integer.parseInt(codigo));
    }
    
    public void alterarMedico(String codigo, String nome, String especialidade,
            String crm, String horarios, String email, String telefone, int sala) throws SQLException{
        
        MedicoRaiz mr = new MedicoRaiz();
        
        mr.setIdMedico(Integer.parseInt(codigo));
        mr.setNomeMedico(nome);
        mr.setEspecialidade(especialidade);
        mr.setCrm(crm);
        mr.setHorario(horarios);
        mr.setEmail(email);
        mr.setTelefone(telefone);
       
        
        SalaRaiz sr = new SalaRaiz();
        sr.setNumeroSala(sala);
        
        SalaDAO.getInstancia().pesquisarPeloNumero(sr);
        
        mr.setSala(sr);
        
        MedicoDAO.getInstancia().alterar(mr);
        
    }
     
     
    // METODOS DA JIF VERIFICAR PACIENTE    
    public void excluirPaciente(String codigo) throws SQLException {
        PacienteDAO.getInstancia().deletar(Integer.parseInt(codigo));
    }

    public void alterarPaciente(String codigo, String nome,
            String cpf, String telefone, String medico) throws SQLException {

        PacienteRaiz pr = new PacienteRaiz();
        pr.setIdPaciente(Integer.parseInt(codigo));
        pr.setCpf(cpf);
        pr.setNomePaciente(nome);
        pr.setTelefone(telefone);

        MedicoRaiz mr = new MedicoRaiz();
        mr.setNomeMedico(medico);

        MedicoDAO.getInstancia().pesquisarPeloNome(mr);

        pr.setMedico(mr);

        PacienteDAO.getInstancia().alterar(pr);


    }

    //VARIAVEIS GLOBAIS
    private ArrayList<Observer> observers = new ArrayList<>();

    // METODOS GERAIS (OBSERVERS)
    public void incluiObserver(Observer observer) {
        if (observer != null) {
            this.observers.add(observer);
        }
    }

    public void removerObserver(Observer observer) {
        if (observer != null) {
            this.observers.remove(observer);
        }
    }

    public void avisaObservers() {
        for (Observer o : this.observers) {
            if (o != null) {
                o.update();
            }
        }
    }

    public void deslogar() {
        this.usuario = null;
    }

}
