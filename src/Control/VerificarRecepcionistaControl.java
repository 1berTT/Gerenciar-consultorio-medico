
package Control;

import DAO.RecepcionistaDAO;
import Model.FachadaModels;
import Model.Observer;
import Model.RecepcionistaRaiz;
import Model.SalaRaiz;
import View.JIFVerificarRecepcionista;
import View.JIFVerificarSala;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class VerificarRecepcionistaControl implements Observer{
    
    private FachadaModels model;
    private JIFVerificarRecepcionista view;

    public VerificarRecepcionistaControl(JIFVerificarRecepcionista view, FachadaModels model){
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
        }
        
    }
    
    
    @Override
    public void update() {
        try {
            if (this.model.getRecepcionistas().size() > 0) {
                for (RecepcionistaRaiz recepcionista : this.model.getRecepcionistas()) {
                    if (recepcionista != null) {
                        String[] linha = {
                            String.valueOf(recepcionista.getIdRecepcionista()),
                            String.valueOf(recepcionista.getNomeRecepcionista()),
                            String.valueOf(recepcionista.getTelefone()),
                            String.valueOf(recepcionista.getEmail()),
                            String.valueOf(recepcionista.getSenha())
                        };

                        ((DefaultTableModel) this.view.getTableRecepcionistas().getModel()).addRow(linha);
                    }
                }

                this.model.setRecepcionistas(new ArrayList<>());
            }

            
        } catch (Exception ex) {
            this.view.mostraMensagem("Não foi possível atualizar os dados.");
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
                    DefaultTableModel tabela = (DefaultTableModel) this.view.getTableRecepcionistas().getModel();
                    tabela.setNumRows(0);
                    
                            this.model.buscarRecepcionista();
                            this.view.limpaCampos();
                          
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível listar o recepcionista.");
                }
            }

            
        
            if (evento.equals("SELECAO")) {
                this.view.setCodigo((String) this.view.getTableRecepcionistas().getModel().getValueAt(this.view.getTableRecepcionistas().getSelectedRow(), 0));
                RecepcionistaRaiz rr = new RecepcionistaRaiz();
                rr.setIdRecepcionista(Integer.parseInt(this.view.getCodigo()));
                RecepcionistaDAO.getInstancia().pesquisar(rr);
                this.view.setaCampos(rr);
            }
    
            if (evento.equals(("ALTERAR"))) {
                if(this.view.validaCampos()){    
                    if (this.view.getNome().equals("") && this.view.getEmail().equals("") && this.view.getCodigo().equals("") 
                     && this.view.getTelefone().equals("") && this.view.getSenha().equals("")){
                        
                        this.view.mostraMensagem("Voce precisa selecionar uma sala para altera-la.");
                    
                    } else {
                        try {
                            this.model.alterarRecepcionista(this.view.getCodigo(),
                                    this.view.getNome(),
                                    this.view.getTelefone(),
                                    this.view.getEmail(),
                                    this.view.getSenha()
                            );
                            
                                this.model.buscarRecepcionista(this.view.getCodigo());
                                this.view.mostraMensagem("recepcionista alterado com sucesso.");
                                this.model.avisaObservers();
                                this.view.limpaCampos();
                                this.evento("LISTAR");
                            
                        } catch (SQLException ex) {
                            this.view.mostraMensagem("Nao foi possivel alterar o recepcionista.");
                        }
                    }
                }
            }
    
    
            if (evento.equals("EXCLUIR")) {
                if (this.view.getNome().equals("") && this.view.getEmail().equals("") && this.view.getCodigo().equals("") 
                     && this.view.getTelefone().equals("") && this.view.getSenha().equals("")){
                    this.view.mostraMensagem("Voce precisa selecionar uma recepcionista para exclui-lo.");
                } else {
                    try {
                        this.model.excluirRecepcionista(this.view.getCodigo());
                        this.view.mostraMensagem("Recepcionista excluido com sucesso. Fechando sistema.");
                        System.exit(0);
                    } catch (SQLException ex) {
                        this.view.mostraMensagem("Não foi possivel excluir o recepcionista.");
                    }
                }
            }
            
        } 
        
        this.model.avisaObservers();
        this.model.removerObserver(this);
        
    }
    
    
}
