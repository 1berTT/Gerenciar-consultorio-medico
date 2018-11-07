package Control;

import Model.FachadaModels;
import Model.MedicoRaiz;
import Model.Observer;
import View.JIFCadastrarPaciente;
import View.JIFCadastrarRecepcionista;
import java.sql.SQLException;

public class CadastroRecepcionistaControl implements Observer{

    private FachadaModels model;
    private JIFCadastrarRecepcionista view;

    public CadastroRecepcionistaControl(JIFCadastrarRecepcionista view, FachadaModels model) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
        }
    }

    public void evento(String evento) {
        this.model.incluiObserver(this);
        
        if (evento != null) {
            if (evento.equals("CADASTRAR")) {
                try {
                    if (this.view.validaCampos()) {
                        this.model.cadastrarRecepcionista(
                                this.view.getNomeRecepcionista(),
                                this.view.getTelefone(),
                                this.view.getEmail(),
                                this.view.getSenha()
                        );
                        this.view.mostraMensagem("Recepcionista cadastrado com sucesso.");
                        this.view.fecha();
                    }
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível cadastrar o recepcionista.");
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
        this.model.avisaObservers();
        this.model.removerObserver(this);
    }

    @Override
    public void update() {
        
    }

    
}
