package Control;

import Model.FachadaModels;
import Model.MedicoRaiz;
import Model.Observer;
import Model.PacienteRaiz;
import Model.SalaRaiz;
import View.JIFCadastrarConsulta;
import java.sql.SQLException;

public class CadastroConsultaControl implements Observer {

    private FachadaModels model;
    private JIFCadastrarConsulta view;

    public CadastroConsultaControl(JIFCadastrarConsulta view, FachadaModels model) {
        if (model != null && view != null) {
            this.model = model;
            this.view = view;
        }
    }

    public void evento(String evento) {
        this.model.incluiObserver(this);
        try {
            this.model.buscarMedicos();
            this.model.buscarPacientes();
            this.model.buscarSalas();
        } catch (SQLException ex) {
            this.view.mostraMensagem("Não foi possivel buscar os medicos, pacientes ou salas do sistema.");
        }
        if (evento != null) {
            if (evento.equals("CADASTRAR")) {
                try {
                    if (this.view.validaCampos()) {
                        this.model.cadastrarConsulta(
                                this.view.getDataConsulta(),
                                this.view.getHoraConsulta(),
                                (String) this.view.getComboPacientes().getSelectedItem(),
                                (String) this.view.getComboMedicos().getSelectedItem(),
                                String.valueOf((Integer) this.view.getComboSalas().getSelectedItem())
                        );
                        this.view.mostraMensagem("Consulta cadastrada com sucesso.");
                        this.view.fecha();
                    }
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível cadastrar a consulta. " + e.getMessage());
                }
            }

            if (evento.equals("LIMPAR")) {
                try {
                    this.view.limparCampos();
                } catch (Exception e) {
                    this.view.mostraMensagem("Não foi possível limpar os campos");
                }
            }

        }
        this.model.avisaObservers();
        this.model.removerObserver(this);
    }

    @Override
    public void update() {
        if (this.model.getPacientes().size() > 0) {
            this.view.getComboPacientes().removeAllItems();

            for (PacienteRaiz paciente : this.model.getPacientes()) {
                this.view.getComboPacientes().addItem(paciente.getNomePaciente());
            }

            this.view.getComboPacientes().setSelectedIndex(-1);
        }

        if (this.model.getMedicos().size() > 0) {
            this.view.getComboMedicos().removeAllItems();

            for (MedicoRaiz medico : this.model.getMedicos()) {
                this.view.getComboMedicos().addItem(medico.getNomeMedico());
            }

            this.view.getComboMedicos().setSelectedIndex(-1);
        }

        if (this.model.getSalas().size() > 0) {
            this.view.getComboSalas().removeAllItems();

            for (SalaRaiz sala : this.model.getSalas()) {
                this.view.getComboSalas().addItem(sala.getNumeroSala());
            }

            this.view.getComboSalas().setSelectedIndex(-1);
        }
    }

}
