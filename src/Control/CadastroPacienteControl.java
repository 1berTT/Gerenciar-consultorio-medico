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
        try {
            this.model.buscarMedicos();
        } catch (SQLException ex) {
            this.view.mostraMensagem("Não foi possivel buscar os medicos.");
        }
        if (evento != null) {
            if (evento.equals("CADASTRAR")) {
                try {
                    if (this.view.validaCampos()) {
                        this.model.cadastrarPaciente(
                                this.view.getNomePaciente(),
                                this.view.getTelefone(),
                                this.view.getCpf(),
                                (String) this.view.getComboMedicos().getItemAt(
                                        this.view.getComboMedicos().getSelectedIndex())
                                
                        );
                        this.view.mostraMensagem("Paciente cadastrado com sucesso.");
                        this.view.fecha();
                    }
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível cadastrar o paciente.");
                }
            }
        }
        this.model.avisaObservers();
        this.model.removerObserver(this);
    }

    @Override
    public void update() {
        if (this.model.getMedicos().size() > 0) {
            this.view.getComboMedicos().removeAll();

            for (MedicoRaiz medico : this.model.getMedicos()) {
                this.view.getComboMedicos().addItem(medico.getNomeMedico());
            }

            this.view.getComboMedicos().setSelectedIndex(-1);
        }
    }

}
