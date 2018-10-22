package Control;

import DAO.MedicoDAO;
import DAO.PacienteDAO;
import Model.*;
import View.*;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class VerificarMedicoControl implements Observer {

    private FachadaModels model;
    private JIFVerificarMedico view;

    public VerificarMedicoControl(JIFVerificarMedico view, FachadaModels model) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
        }
    }

    @Override
    public void update() {
        try {
            if (this.model.getMedicos().size() > 0) {
                for (MedicoRaiz medico : this.model.getMedicos()) {
                    if (medico != null) {
                        String[] linha = {
                            String.valueOf(medico.getIdMedico()),
                            medico.getNomeMedico(),
                            medico.getEspecialidade(),
                            medico.getCrm(),
                            medico.getHorario(),
                            medico.getEmail(),
                            medico.getTelefone(),
                            String.valueOf(medico.getSala().getNumeroSala())
                        };

                        ((DefaultTableModel) this.view.getTableMedicos().getModel()).addRow(linha);
                    }
                }

                this.model.setMedicos(new ArrayList<>());
            }
            
            if (this.model.getSalas().size() > 0) {
                this.view.getComboSala().removeAll();
                for (SalaRaiz sala : this.model.getSalas()) {
                    this.view.getComboSala().addItem(sala.getNumeroSala());
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
        this.model.buscarSalas();
        if (evento != null) {
           
            if(evento.equals("ABRE TELA")){
                this.evento("LISTAR");
                this.view.getComboSala().setSelectedIndex(-1);
                this.view.limpaCampos();
            }
            
            if (evento.equals("LISTAR")) {
                try {
                    DefaultTableModel tabela = (DefaultTableModel) this.view.getTableMedicos().getModel();
                    tabela.setNumRows(0);
                    if (((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")) {
                        if (!this.view.getTextPesquisa().getText().trim().equals("")) {
                            this.model.buscarMedico(Integer.parseInt(this.view.getTextPesquisa().getText().trim()));
                        } else {
                            this.model.buscarMedicos();
                        }
                    } else {
                        if (!this.view.getTextPesquisa().getText().trim().equals("")) {
                            this.model.buscarMedico(this.view.getTextPesquisa().getText().trim());
                        } else {
                            this.model.buscarMedicos();
                        }
                    }
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível listar os médicos.");
                }

            }
            
            if(evento.equals("ALTERAR")){
                
                if(this.view.validaCampos()){    
                        
                        if (this.view.getNome().equals("") 
                        && this.view.getCrm().equals("") 
                        && this.view.getTelefone().equals("")
                        && this.view.getCodigo().equals("") 
                        && this.view.getEspecialidade().equals("")
                        && this.view.getHorarios().equals("")
                        && this.view.getEmail().equals("")) {
                    
                            this.view.mostraMensagem("Voce precisa selecionar um medico para exclui-lo.");
                        
                        } else {
                            try {
                                this.model.alterarMedico(this.view.getCodigo(),
                                        this.view.getNome(),
                                        this.view.getEspecialidade(),
                                        this.view.getCrm(),
                                        this.view.getHorarios(),
                                        this.view.getEmail(),
                                        this.view.getTelefone(), 
                                        (int) this.view.getComboSala().getItemAt(
                                                this.view.getComboSala().getSelectedIndex()
                                        ));
                                    if(((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")){
                                        this.model.buscarMedico(this.view.getCodigo());
                                        this.view.mostraMensagem("Medico alterado com sucesso.");
                                        this.view.limpaCampos();
                                        this.evento("LISTAR");
                                    }else{
                                        this.model.buscarMedicos();
                                        this.view.mostraMensagem("Medico alterado com sucesso.");
                                        this.view.limpaCampos();
                                        this.evento("LISTAR");
                                    } 
                                
                            } catch (SQLException ex) {
                                this.view.mostraMensagem("Nao foi possivel alterar o medico.");
                            }
                        }
                }
            }
            
            if(evento.equals("EXCLUIR")){
                if (this.view.getNome().equals("") 
                        && this.view.getCrm().equals("") 
                        && this.view.getTelefone().equals("")
                        && this.view.getCodigo().equals("") 
                        && this.view.getEspecialidade().equals("")
                        && this.view.getHorarios().equals("")
                        && this.view.getEmail().equals("")) {
                    
                            this.view.mostraMensagem("Voce precisa selecionar um medico para exclui-lo.");
                } else {
                    try {
                        this.model.excluirMedico(this.view.getCodigo());
                        this.model.buscarMedicos();
                        this.view.mostraMensagem("Medico excluido com sucesso.");
                        this.view.limpaCampos();
                        this.evento("LISTAR");
                    } catch (SQLException ex) {
                        this.view.mostraMensagem("Não foi possivel excluir o medico.");
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
                this.view.setCodigo((String) this.view.getTableMedicos().getModel().getValueAt(this.view.getTableMedicos().getSelectedRow(), 0));
                MedicoRaiz mr = new MedicoRaiz();
                mr.setIdMedico(Integer.parseInt(this.view.getCodigo()));
                MedicoDAO.getInstancia().pesquisar(mr);
                this.view.setaCampos(mr);
            
            }
            
           
        }
        this.model.avisaObservers();
        this.model.removerObserver(this);

    }

}
