package Control;

import DAO.ConsultaDAO;
import Model.ConsultaRaiz;
import Model.FachadaModels;
import Model.MedicoRaiz;
import Model.Observer;
import Model.PacienteRaiz;
import Model.SalaRaiz;
import View.JIFVerificarConsulta;
import View.JIFVerificarMedico;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class VerificarConsultaControl implements Observer{
    
    private FachadaModels model;
    private JIFVerificarConsulta view;

    public VerificarConsultaControl(JIFVerificarConsulta view, FachadaModels model) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
        }
    }
    
    
    @Override
    public void update() {
        try {
            if (this.model.getMedicos().size() > 0) {
                for (ConsultaRaiz consulta : this.model.getConsultas()) {
                    if (consulta != null) {
                        String[] linha = {
                            String.valueOf(consulta.getIdConsulta()),
                            consulta.getDataConsulta(),
                            consulta.getHoraConsulta(),
                            consulta.getPaciente().getNomePaciente(),
                            consulta.getMedico().getNomeMedico(),
                            String.valueOf(consulta.getSala().getNumeroSala())
                        };

                        ((DefaultTableModel) this.view.getTableConsultas().getModel()).addRow(linha);
                    }
                }

                this.model.setConsultas(new ArrayList<>());
            }
            
            
            if (this.model.getPacientes().size() > 0) {
                this.view.getComboPacientes().removeAllItems();

                for (PacienteRaiz paciente : this.model.getPacientes()) {
                    this.view.getComboPacientes().addItem(paciente.getNomePaciente());
                }

                this.view.getComboPacientes().setSelectedIndex(-1);
            }

            if (this.model.getMedicos().size() > 0) {
                this.view.getComboMedicos().removeAllItems();

                for (MedicoRaiz medico : this.model.getMedicos()) {
                    this.view.getComboMedicos().addItem(medico.getNomeMedico());
                }

                this.view.getComboMedicos().setSelectedIndex(-1);
            }

            if (this.model.getSalas().size() > 0) {
                this.view.getComboSalas().removeAllItems();

                for (SalaRaiz sala : this.model.getSalas()) {
                    this.view.getComboSalas().addItem(sala.getNumeroSala());
                }

                this.view.getComboSalas().setSelectedIndex(-1);
            }

            
            
            
            
        } catch (Exception ex) {
            this.view.mostraMensagem("Não foi possível atualizar os dados.");
        }
    }
    
    
    public void evento(KeyEvent evento) {
        if (((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")) {
            String caracteres = "0987654321";
            if (!caracteres.contains(evento.getKeyChar() + "")) {
                evento.consume();
            }
        }
    }
    
    
    public void evento(String evento) throws SQLException {
        this.model.incluiObserver(this);
        try {
            this.model.buscarMedicos();
            this.model.buscarPacientes();
            this.model.buscarSalas();
        } catch (SQLException ex) {
            this.view.mostraMensagem("Não foi possivel buscar os medicos, pacientes ou salas do sistema.");
        }

        if (evento != null) {
           
            if(evento.equals("ABRE TELA")){
                this.evento("LISTAR");
                this.view.limpaCampos();
            }
       
            if (evento.equals("LISTAR")) {
                try {
                    DefaultTableModel tabela = (DefaultTableModel) this.view.getTableConsultas().getModel();
                    tabela.setNumRows(0);
                    if (((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")) {
                        if (!this.view.getTextPesquisa().getText().trim().equals("")) {
                            this.model.buscarConsulta(Integer.parseInt(this.view.getTextPesquisa().getText().trim()));
                        } else {
                            this.model.buscarConsultas();
                        }
                    } else {
                        if (!this.view.getTextPesquisa().getText().trim().equals("")) {
                            this.model.buscarConsulta(this.view.getTextPesquisa().getText().trim());
                        } else {
                            this.model.buscarConsultas();
                        }
                    }
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível listar as consultas.");
                }

            }
        
            
            if(evento.equals("ALTERAR")){
                
                if(this.view.validaCampos()){    
                        
                        if (this.view.getData().equals("") 
                        && this.view.getHora().equals("") 
                        && this.view.getCodigo().equals("")
                        && this.view.getComboMedicos().getSelectedIndex() == -1 
                        && this.view.getComboPacientes().getSelectedIndex() == -1
                        && this.view.getComboSalas().getSelectedIndex() == -1) {
                    
                            this.view.mostraMensagem("Voce precisa selecionar uma consulta para exclui-la.");
                        
                        } else {
                            try {
                                this.model.alterarConsulta(this.view.getCodigo(),
                                        this.view.getData(),
                                        this.view.getHora(),
                                        (String) this.view.getComboPacientes().getSelectedItem(),
                                        (String) this.view.getComboMedicos().getSelectedItem(),
                                        String.valueOf((Integer) this.view.getComboSalas().getSelectedItem())
                                        );
                                    if(((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")){
                                        this.model.buscarConsulta(this.view.getCodigo());
                                        this.view.mostraMensagem("Consulta alterada com sucesso.");
                                        this.view.limpaCampos();
                                        this.evento("LISTAR");
                                    }else{
                                        this.model.buscarConsultas();
                                        this.view.mostraMensagem("Consulta alterada com sucesso.");
                                        this.view.limpaCampos();
                                        this.evento("LISTAR");
                                    } 
                                
                            } catch (SQLException ex) {
                                this.view.mostraMensagem("Nao foi possivel alterar a consulta.");
                            }
                        }
                
                }
           
            }
            
            if(evento.equals("EXCLUIR")){
                if (this.view.getCodigo().equals("") 
                        && this.view.getData().equals("") 
                        && this.view.getHora().equals("")
                        && this.view.getComboPacientes().getSelectedIndex() == -1
                        && this.view.getComboMedicos().getSelectedIndex() == -1
                        && this.view.getComboSalas().getSelectedIndex() == -1) {
                    
                            this.view.mostraMensagem("Voce precisa selecionar uma consulta para exclui-la.");
                } else {
                    try {
                        this.model.excluirConsulta(this.view.getCodigo());
                        this.model.buscarConsultas();
                        this.view.mostraMensagem("Consulta excluida com sucesso.");
                        this.view.limpaCampos();
                        this.evento("LISTAR");
                    } catch (SQLException ex) {
                        this.view.mostraMensagem("Não foi possivel excluir a consulta.");
                    }
                }
            }
            
            
            if (evento.equals("MUDAR TIPO PESQUISA")) {
                if (((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")) {
                    this.view.addTipoLabelCombo("Código");
                } else {
                    this.view.addTipoLabelCombo("Médico");
                }

                this.view.getTextPesquisa().setText("");
            }
            
            if(evento.equals("SELECAO")){
                this.view.setCodigo((String) this.view.getTableConsultas().getModel().getValueAt(this.view.getTableConsultas().getSelectedRow(), 0));
                ConsultaRaiz cr = new ConsultaRaiz();
                cr.setIdConsulta(Integer.parseInt(this.view.getCodigo()));
                ConsultaDAO.getInstancia().pesquisar(cr);
                this.view.setaCampos(cr);
            
            }
        
        }
        
        this.model.avisaObservers();
        this.model.removerObserver(this);
        
    }
    
    
}
