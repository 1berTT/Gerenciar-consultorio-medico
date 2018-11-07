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

    private ArrayList<ConsultaRaiz> consultas = new ArrayList<>();
    private ArrayList<MedicoRaiz> medicos = new ArrayList<>();
    private ArrayList<PacienteRaiz> pacientes = new ArrayList<>();
    private ArrayList<SalaRaiz> salas = new ArrayList<>();
    private ArrayList<RecepcionistaRaiz> recepcionistas = new ArrayList<>();
    
    //GETTERS E SETERS DOS ARRAY LIST DE USADOS NO OBSERVER
    public void setConsultas(ArrayList<ConsultaRaiz> consultas) {
        this.consultas = consultas;
    }

    public ArrayList<ConsultaRaiz> getConsultas() {
        return this.consultas;
    }
    
    public void setRecepcionistas(ArrayList<RecepcionistaRaiz> recepcionistas) {
        this.recepcionistas = recepcionistas;
    }

    public ArrayList<RecepcionistaRaiz> getRecepcionistas() {
        return this.recepcionistas;
    }
    
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

    public void logar(String email, String senha) throws Exception {
        if (email != null && senha != null) {
            this.usuario = new RecepcionistaRaiz();
            this.usuario.setEmail(email);
            RecepcionistaDAO.getInstancia().pesquisarPeloEmail(this.usuario);
            
            if(this.usuario.getIdRecepcionista() < 1){
                this.recepcionistas.add(usuario);
            }
        }
    }

    
    public void buscarRecepcionista() throws SQLException {
        RecepcionistaDAO.getInstancia().pesquisar(this.usuario);
        this.recepcionistas.add(usuario);
    }
    
    
    public void abreTela(String tela) {
        if (tela != null) {
            if (tela.equals("PRINCIPAL")) {
                JPrincipal principal = new JPrincipal(this);
                principal.setVisible(true);
            }  
        }
    }

    
    public void alterarRecepcionista(String codigo, String nome, String telefone, String email, String senha) throws SQLException {

        RecepcionistaRaiz rr = new RecepcionistaRaiz();
        
        rr.setIdRecepcionista(Integer.parseInt(codigo));
        rr.setNomeRecepcionista(nome);
        rr.setTelefone(telefone);
        rr.setEmail(email);
        rr.setSenha(senha);
        
        RecepcionistaDAO.getInstancia().alterar(rr);

        }
    
    public void excluirRecepcionista(String codigo) throws SQLException {
        RecepcionistaDAO.getInstancia().deletar(Integer.parseInt(codigo));
    }   
    
    
    public void buscarRecepcionista(String codigo) throws SQLException {
        RecepcionistaRaiz recepcionista = new RecepcionistaRaiz();
        recepcionista.setIdRecepcionista(Integer.parseInt(codigo));
        RecepcionistaDAO.getInstancia().pesquisar(recepcionista);

        if(recepcionista.getIdRecepcionista()!= 0){
            this.recepcionistas.add(recepcionista);
        }
    }
    
    //MÉTODOS DA TELA CADASTRAR MEDICO
    public void cadastrarMedico(String nome, String crm, String especialidade, String email,
            String telefone) throws SQLException {

        MedicoRaiz medico = new MedicoRaiz();

        medico.setNomeMedico(nome);
        medico.setCrm(crm);
        medico.setEspecialidade(especialidade);
        medico.setEmail(email);
        medico.setTelefone(telefone);

        MedicoDAO.getInstancia().adicionar(medico);
    }

    
    // MÉTODOS DA TELA CADASTRAR RECEPCIONISTA
    
        public void cadastrarRecepcionista(String nome, String telefone, String email, String senha) throws SQLException {

        RecepcionistaRaiz recepcionista = new RecepcionistaRaiz();

        recepcionista.setNomeRecepcionista(nome);
        recepcionista.setTelefone(telefone);
        recepcionista.setEmail(email);
        recepcionista.setSenha(senha);
        
        RecepcionistaDAO.getInstancia().adicionar(recepcionista);
    }
    
    
    // MÉTODOS DA TELA CADASTRAR SALA
    public void cadastrarSala(String bloco, String numeroSala) throws SQLException {

        SalaRaiz sala = new SalaRaiz();

        sala.setBloco(Integer.parseInt(bloco));
        sala.setNumeroSala(Integer.parseInt(numeroSala));

        SalaDAO.getInstancia().adicionar(sala);

    }

    //MÉTODOS DA TELA CADASTRAR CONSULTA
    
    public void cadastrarConsulta(String data, String hora, String paciente, String medico, String sala) throws SQLException {

        
        ConsultaRaiz consulta = new ConsultaRaiz();

        consulta.setDataConsulta(data);
        consulta.setHoraConsulta(hora);

        PacienteRaiz pr = new PacienteRaiz();
        pr.setNomePaciente(paciente);
        PacienteDAO.getInstancia().pesquisarPeloNome(pr);
        
        consulta.setPaciente(pr);
        
        MedicoRaiz mr = new MedicoRaiz();
        mr.setNomeMedico(medico);
        MedicoDAO.getInstancia().pesquisarPeloNome(mr);
        
        consulta.setMedico(mr);

        SalaRaiz sr = new SalaRaiz();
        sr.setNumeroSala(Integer.parseInt(sala));
        SalaDAO.getInstancia().pesquisarPeloNumero(sr);
        
        consulta.setSala(sr);
        
        ConsultaDAO.getInstancia().adicionar(consulta);
    }
    
    // MÉTODOS DA TELA CADASTRAR PACIENTE
    public void cadastrarPaciente(String nome, String telefone, String cpf) throws SQLException {

        PacienteRaiz paciente = new PacienteRaiz();

        paciente.setNomePaciente(nome);
        paciente.setTelefone(telefone);
        paciente.setCpf(cpf);

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

    
    public void buscarConsultas() throws SQLException {
        this.consultas = ConsultaDAO.getInstancia().listar();
    }
    
    public void buscarPacientes() throws SQLException {
        this.pacientes = PacienteDAO.getInstancia().listar();
    }

    // buscar pelo codigo, onde entra o observer
    public void buscarMedico(int codigo) throws SQLException {
        MedicoRaiz medico = new MedicoRaiz();
        medico.setIdMedico(codigo);
        MedicoDAO.getInstancia().pesquisar(medico);

        if (medico.getEspecialidade()!= null) {
            this.medicos.add(medico);
        }
    }

    
    public void buscarConsulta(int codigo) throws SQLException {
        ConsultaRaiz consulta = new ConsultaRaiz();
        consulta.setIdConsulta(codigo);
        ConsultaDAO.getInstancia().pesquisar(consulta);

        if (consulta.getDataConsulta()!= null) {
            this.consultas.add(consulta);
        }
    }
    
    
    
    
    public void buscarSalaPeloNumero(String numero) throws SQLException {
        SalaRaiz sala = new SalaRaiz();
        sala.setNumeroSala(Integer.parseInt(numero));

        this.salas = SalaDAO.getInstancia().buscaTodosPeloNumero(sala);

    }
    
    
    public void buscarSala(String codigo) throws SQLException {
        SalaRaiz sala = new SalaRaiz();
        sala.setIdSala(Integer.parseInt(codigo));
        SalaDAO.getInstancia().pesquisar(sala);

        if(sala.getIdSala() != 0){
            this.salas.add(sala);
        }
    }
    
    public void buscarMedico(String nome) throws SQLException {
        MedicoRaiz medico = new MedicoRaiz();
        medico.setNomeMedico(nome);

        this.medicos = MedicoDAO.getInstancia().buscaTodosPeloNome(medico);
    }

    
    public void buscarConsulta(String data) throws SQLException {
        ConsultaRaiz consulta = new ConsultaRaiz();
        consulta.setDataConsulta(data);

        this.consultas = ConsultaDAO.getInstancia().buscaTodosPelaData(consulta);
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

        if (paciente.getCpf()!= null) {
            this.pacientes.add(paciente);
        }
    }

    public MedicoRaiz buscaMedico(String medico) throws SQLException {
        MedicoRaiz m = new MedicoRaiz();
        m.setIdMedico(Integer.parseInt(medico));
        MedicoDAO.getInstancia().pesquisar(m);
        return m;
    }

    
    //JIF VERIFICAR CONSULTA
    
        public void alterarConsulta(String codigo, String data, String hora,
            String paciente, String medico, String sala) throws SQLException{
        
        ConsultaRaiz cr = new ConsultaRaiz();
        
        cr.setIdConsulta(Integer.parseInt(codigo));
        cr.setDataConsulta(data);
        cr.setHoraConsulta(hora);
        
        PacienteRaiz pr = new PacienteRaiz();
        pr.setNomePaciente(paciente);
        PacienteDAO.getInstancia().pesquisarPeloNome(pr);
        cr.setPaciente(pr);
        
        MedicoRaiz mr = new MedicoRaiz();
        mr.setNomeMedico(medico);
        MedicoDAO.getInstancia().pesquisarPeloNome(mr);
        cr.setMedico(mr);
        
        SalaRaiz sr = new SalaRaiz();
        sr.setNumeroSala(Integer.parseInt(sala));
        SalaDAO.getInstancia().pesquisarPeloNumero(sr);
        cr.setSala(sr);
        
        ConsultaDAO.getInstancia().alterar(cr);
        
    }
    
    public void excluirConsulta(String codigo) throws SQLException {
        ConsultaDAO.getInstancia().deletar(Integer.parseInt(codigo));
    }
    
    
    // JIF VERIFICAR MEDICO
    
    public void excluirMedico(String codigo) throws SQLException {
        MedicoDAO.getInstancia().deletar(Integer.parseInt(codigo));
    }
    
    public void alterarMedico(String codigo, String nome, String especialidade,
            String crm, String email, String telefone) throws SQLException{
        
        MedicoRaiz mr = new MedicoRaiz();
        
        mr.setIdMedico(Integer.parseInt(codigo));
        mr.setNomeMedico(nome);
        mr.setEspecialidade(especialidade);
        mr.setCrm(crm);
        mr.setEmail(email);
        mr.setTelefone(telefone);
        
        MedicoDAO.getInstancia().alterar(mr);
        
    }
     
     
    //MÉTODOS DA JIF VERIFICAR SALA
    
        public void alterarSala(String codigo, String bloco, String numero) throws SQLException {

        SalaRaiz sr = new SalaRaiz();
        
        sr.setIdSala(Integer.parseInt(codigo));
        sr.setBloco(Integer.parseInt(bloco));
        sr.setNumeroSala(Integer.parseInt(numero));
        
        SalaDAO.getInstancia().alterar(sr);

        }
    
        public void excluirSala(String codigo) throws SQLException {
            SalaDAO.getInstancia().deletar(Integer.parseInt(codigo));
        }   
        
    
    // METODOS DA JIF VERIFICAR PACIENTE    
    public void excluirPaciente(String codigo) throws SQLException {
        PacienteDAO.getInstancia().deletar(Integer.parseInt(codigo));
    }

    public void alterarPaciente(String codigo, String nome,
            String cpf, String telefone) throws SQLException {

        PacienteRaiz pr = new PacienteRaiz();
        pr.setIdPaciente(Integer.parseInt(codigo));
        pr.setCpf(cpf);
        pr.setNomePaciente(nome);
        pr.setTelefone(telefone);

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
