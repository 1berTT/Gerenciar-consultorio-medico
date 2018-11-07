package Control;

import Model.FachadaModels;
import Model.MedicoRaiz;
import Model.Observer;
import View.JIFCadastrarMedico;
import View.JIFCadastrarPaciente;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CadastroPacienteControl implements Observer {

    private FachadaModels model;
    private JIFCadastrarPaciente view;

    public CadastroPacienteControl(JIFCadastrarPaciente view, FachadaModels model) {
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
                        this.model.cadastrarPaciente(
                                this.view.getNomePaciente(),
                                this.view.getTelefone(),
                                this.view.getCpf()
                        );
                        this.view.mostraMensagem("Paciente cadastrado com sucesso.");
                        this.view.fecha();
                    }
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível cadastrar o paciente.");
                }
            }
            
            if(evento.equals("LIMPAR")){
                try{
                    this.view.limparCampos();
                }catch(Exception e){
                    this.view.mostraMensagem("Não foi possível limpar os camppos.");
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
