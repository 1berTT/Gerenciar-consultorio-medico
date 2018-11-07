
package Control;

import DAO.PacienteDAO;
import DAO.SalaDAO;
import Model.FachadaModels;
import Model.MedicoRaiz;
import Model.Observer;
import Model.PacienteRaiz;
import Model.SalaRaiz;
import View.JIFVerificarPaciente;
import View.JIFVerificarSala;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class VerificarSalaControl implements Observer {
    
    private FachadaModels model;
    private JIFVerificarSala view;

    public VerificarSalaControl(JIFVerificarSala view, FachadaModels model){
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
        }
        
    }
    
    
    
    @Override
    public void update() {
        try {
            if (this.model.getSalas().size() > 0) {
                for (SalaRaiz sala : this.model.getSalas()) {
                    if (sala != null) {
                        String[] linha = {
                            String.valueOf(sala.getIdSala()),
                            String.valueOf(sala.getBloco()),
                            String.valueOf(sala.getNumeroSala())
                        };

                        ((DefaultTableModel) this.view.getTableSalas().getModel()).addRow(linha);
                    }
                }

                this.model.setSalas(new ArrayList<>());
            }

            
        } catch (Exception ex) {
            this.view.mostraMensagem("Não foi possível atualizar os dados.");
        }
    }
    
    
    public void evento(KeyEvent evento) {
        if (((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código") ||
        ((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Número")) {
            String caracteres = "0987654321";
            if (!caracteres.contains(evento.getKeyChar() + "")) {
                evento.consume();
            }
        }
    }
    
    
    public void evento(String evento) throws SQLException {
        
        this.model.incluiObserver(this);
        
        if (evento != null) {

            if (evento.equals("ABRIR TELA")) {
                this.evento("LISTAR");
                this.view.limpaCampos();
            }

            if (evento.equals("LISTAR")) {
                try {
                    DefaultTableModel tabela = (DefaultTableModel) this.view.getTableSalas().getModel();
                    tabela.setNumRows(0);
                    if (((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")) {
                        if (!this.view.getTextPesquisa().getText().trim().equals("")) {
                            this.model.buscarSala(this.view.getTextPesquisa().getText().trim());
                            this.view.limpaCampos();
                        } else {
                            this.model.buscarSalas();
                            this.view.limpaCampos();
                        }
                    } else {
                        if (!this.view.getTextPesquisa().getText().trim().equals("")) {
                            this.model.buscarSalaPeloNumero(this.view.getTextPesquisa().getText().trim());
                            this.view.limpaCampos();
                        } else {
                            this.model.buscarSalas();
                            this.view.limpaCampos();
                        }
                    }
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível listar as salas.");
                }
            }

            if (evento.equals("MUDAR TIPO PESQUISA")) {
                if (((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")) {
                    this.view.addTipoLabelCombo("Código");
                } else {
                    this.view.addTipoLabelCombo("Número");
                }

                this.view.getTextPesquisa().setText("");
            }
            if (evento.equals("SELECAO")) {
                this.view.setCodigo((String) this.view.getTableSalas().getModel().getValueAt(this.view.getTableSalas().getSelectedRow(), 0));
                SalaRaiz sr = new SalaRaiz();
                sr.setIdSala(Integer.parseInt(this.view.getCodigo()));
                SalaDAO.getInstancia().pesquisar(sr);
                this.view.setaCampos(sr);
            }
            if (evento.equals(("ALTERAR"))) {
                if(this.view.validaCampos()){    
                    if (this.view.getNumero().equals("") && this.view.getBloco().equals("") && this.view.getCodigo().equals("")) {
                        this.view.mostraMensagem("Voce precisa selecionar uma sala para altera-la.");
                    } else {
                        try {
                            this.model.alterarSala(this.view.getCodigo(),
                                    this.view.getBloco(),
                                    this.view.getNumero()
                            );
                            if(((String) this.view.getComboPesquisa().getItemAt(this.view.getComboPesquisa().getSelectedIndex())).equals("Código")){
                                this.model.buscarSala(this.view.getCodigo());
                                this.view.mostraMensagem("Sala alterada com sucesso.");
                                this.model.avisaObservers();
                                this.view.limpaCampos();
                                this.evento("LISTAR");
                            }else{
                                this.model.buscarSalas();
                                this.view.mostraMensagem("sala alterada com sucesso.");
                                this.view.limpaCampos();
                                this.evento("LISTAR");
                            }
                            
                        } catch (SQLException ex) {
                            this.view.mostraMensagem("Nao foi possivel alterar a sala.");
                        }
                    }
                }
            }
            if (evento.equals("EXCLUIR")) {
                if (this.view.getNumero().equals("") && this.view.getBloco().equals("") && this.view.getCodigo().equals("")) {
                    this.view.mostraMensagem("Voce precisa selecionar uma sala para exclui-lo.");
                } else {
                    try {
                        this.model.excluirSala(this.view.getCodigo());
                        this.model.buscarPacientes();
                        this.view.mostraMensagem("Sala excluida com sucesso.");
                        this.view.limpaCampos();
                        this.evento("LISTAR");
                    } catch (SQLException ex) {
                        this.view.mostraMensagem("Não foi possivel excluir a sala.");
                    }
                }
            }
        }

        this.model.avisaObservers();
        this.model.removerObserver(this);
    }
    
    
}
