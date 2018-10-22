package Control;

import DAO.PacienteDAO;
import Model.FachadaModels;
import Model.MedicoRaiz;
import Model.Observer;
import Model.PacienteRaiz;
import View.JIFVerificarMedico;
import View.JIFVerificarPaciente;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VerificarPacienteControl implements Observer {

    private FachadaModels model;
    private JIFVerificarPaciente view;

    public VerificarPacienteControl(JIFVerificarPaciente view, FachadaModels model){
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
        }
        
    }

    @Override
    public void update() {
        try {
            if (this.model.getPacientes().size() > 0) {
                for (PacienteRaiz paciente : this.model.getPacientes()) {
                    if (paciente != null) {
                        String[] linha = {
                            String.valueOf(paciente.getIdPaciente()),
                            paciente.getNomePaciente(),
                            paciente.getTelefone(),
                            paciente.getCpf(),
                            String.valueOf(paciente.getMedico().getNomeMedico())
                        };

                        ((DefaultTableModel) this.view.getTablePacientes().getModel()).addRow(linha);
                    }
                }

                this.model.setPacientes(new ArrayList<>());
            }

            
            
            if (this.model.getMedicos().size() > 0) {
                this.view.getComboMedicos().removeAll();
                for (MedicoRaiz medico : this.model.getMedicos()) {
                    this.view.getComboMedicos().addItem(medico.getNomeMedico());
                }
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
        this.model.buscarMedicos();
        
        if (evento != null) {

            if (evento.equals("ABRIR TELA")) {
                this.evento("LISTAR");
                this.view.getComboMedicos().setSelectedIndex(-1);
                this.view.limpaCampos();
            }

            if (evento.equals("LISTAR")) {
                try {
                    DefaultTableModel tabela = (DefaultTableModel) this.view.getTablePacientes().getModel();
                    tabela.setNumRows(0);
                    if (((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")) {
                        if (!this.view.getTextPesquisa().getText().trim().equals("")) {
                            this.model.buscarPaciente(Integer.parseInt(this.view.getTextPesquisa().getText().trim()));
                        } else {
                            this.model.buscarPacientes();
                        }
                    } else {
                        if (!this.view.getTextPesquisa().getText().trim().equals("")) {
                            this.model.buscarPaciente(this.view.getTextPesquisa().getText().trim());
                        } else {
                            this.model.buscarPacientes();
                        }
                    }
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível listar os pacientes.");
                }
            }

            if (evento.equals("MUDAR TIPO PESQUISA")) {
                if (((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")) {
                    this.view.addTipoLabelCombo("Código");
                } else {
                    this.view.addTipoLabelCombo("Paciente");
                }

                this.view.getTextPesquisa().setText("");
            }
            if (evento.equals("SELECAO")) {
                this.view.setCodigo((String) this.view.getTablePacientes().getModel().getValueAt(this.view.getTablePacientes().getSelectedRow(), 0));
                PacienteRaiz pr = new PacienteRaiz();
                pr.setIdPaciente(Integer.parseInt(this.view.getCodigo()));
                PacienteDAO.getInstancia().pesquisar(pr);
                this.view.setaCampos(pr);
            }
            if (evento.equals(("ALTERAR"))) {
                if(this.view.validaCampos()){    
                    if (this.view.getNome().equals("") && this.view.getCpf().equals("") && this.view.getTelefone().equals("") && this.view.getCodigo().equals("")) {
                        this.view.mostraMensagem("Voce precisa selecionar um paciente para altera-lo.");
                    } else {
                        try {
                            this.model.alterarPaciente(this.view.getCodigo(),
                                    this.view.getNome(),
                                    this.view.getCpf(),
                                    this.view.getTelefone(),
                                    (String) this.view.getComboMedicos().getItemAt(
                                            this.view.getComboMedicos().getSelectedIndex()
                                    )
                            );
                            if(((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")){
                                this.model.buscarPaciente(this.view.getCodigo());
                                this.view.mostraMensagem("Paciente alterado com sucesso.");
                                this.view.limpaCampos();
                                this.evento("LISTAR");
                            }else{
                                this.model.buscarPacientes();
                                this.view.mostraMensagem("Paciente alterado com sucesso.");
                                this.view.limpaCampos();
                                this.evento("LISTAR");
                            }
                            
                        } catch (SQLException ex) {
                            this.view.mostraMensagem("Nao foi possivel alterar o paciente.");
                        }
                    }
                }
            }
            if (evento.equals("EXCLUIR")) {
                if (this.view.getNome().equals("") && this.view.getCpf().equals("") && this.view.getTelefone().equals("") && this.view.getCodigo().equals("")) {
                    this.view.mostraMensagem("Voce precisa selecionar um paciente para exclui-lo.");
                } else {
                    try {
                        this.model.excluirPaciente(this.view.getCodigo());
                        this.model.buscarPacientes();
                        this.view.mostraMensagem("Paciente excluido com sucesso.");
                        this.view.limpaCampos();
                        this.evento("LISTAR");
                    } catch (SQLException ex) {
                        this.view.mostraMensagem("Não foi possivel excluir o paciente.");
                    }
                }
            }
        }

        this.model.avisaObservers();
        this.model.removerObserver(this);

    }

}
