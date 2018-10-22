package Control;

import DAO.RecepcionistaDAO;
import Model.FachadaModels;
import Model.RecepcionistaRaiz;
import View.JLogin;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RecepcionistaControl {

    private JLogin view;
    private FachadaModels model;

    public RecepcionistaControl(JLogin view, FachadaModels model) {
        if (view != null && model != null) {
            this.view = view;
            this.model = model;
        }
    }

    public void evento(String evento) {
        if (evento != null) {
            if (evento.equals("LOGIN")) {
                try {
                    if(this.view.validaCampos()){    
                        this.model.logar(this.view.getUsuario(), this.view.getSenha());
                        if (this.model.getUsuario().getSenha().equals(this.view.getSenha())) {
                            this.view.mostraMensagem("Logado com sucesso.");
                            this.view.fecha();
                            this.model.abreTela("PRINCIPAL");
                        } else {
                            this.view.mostraMensagem("Usuário ou Senha incorretos.");
                            this.model.deslogar();
                        }
                    }
                } catch (Exception ex) {
                    this.view.mostraMensagem("Nao foi possível realizar o Login.");
                }
            }
        }
    }

}
