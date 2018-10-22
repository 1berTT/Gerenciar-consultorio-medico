
package Control;

import Model.FachadaModels;
import View.JIFCadastrarMedico;
import View.JIFCadastrarSala;
import java.sql.SQLException;


public class CadastroSalaControl {


    private FachadaModels model;
    private JIFCadastrarSala view;
    
    
     public CadastroSalaControl(JIFCadastrarSala view, FachadaModels model) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
        }
    }


     
    public void evento(String evento) {
        if (evento != null) {
            if (evento.equals("CADASTRAR")) {
                try {
                    this.model.cadastrarSala(
                            this.view.getBloco(),
                            this.view.getNumeroSala()
                    );
                    this.view.mostraMensagem("Sala cadastrada com sucesso.");
                    this.view.fecha();
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível cadastrar a sala.");
                }

            }
        }
    }

 
    
     
    
}
