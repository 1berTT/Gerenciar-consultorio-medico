/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Model.FachadaModels;
import View.JIFCadastrarMedico;
import View.JPrincipal;

/**
 *
 * @author humbe
 */
public class PrincipalControl {

    private FachadaModels model;
    private JPrincipal view;

    public PrincipalControl(JPrincipal view, FachadaModels model) {
        if (view != null && model != null) {
            this.view = view;
            this.model = model;
        }
    }

}
