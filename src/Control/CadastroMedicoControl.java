package Control;

import Model.FachadaModels;
import Model.MedicoRaiz;

import View.JIFCadastrarMedico;
import View.JPrincipal;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class CadastroMedicoControl{
    
    private FachadaModels model;
    private JIFCadastrarMedico view;
    
    public CadastroMedicoControl(JIFCadastrarMedico view, FachadaModels model) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
        }
    }
    
    public void evento(String evento) {
        if (evento != null) {
            if (evento.equals("CADASTRAR")) {
                try {
                    if(this.view.validaCampos()){    
                        this.model.cadastrarMedico(
                                this.view.getNome(),
                                this.view.getCrm(),
                                this.view.getEspecialidade(),
                                this.view.getEmail(),
                                this.view.getTelefone()
                        );
                    
                        this.view.mostraMensagem("Medico cadastrado com sucesso.");
                        this.view.fecha();
                    }
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível cadastrar o médico.");
                }
                
            }
            
            if(evento.equals("LIMPAR")){
                try{
                    this.view.limparCampos();
                }catch(Exception e){
                    this.view.mostraMensagem("Não foi possível limpar os campos.");
                }
            }
           
        }
    
    }
    
    
    
}
