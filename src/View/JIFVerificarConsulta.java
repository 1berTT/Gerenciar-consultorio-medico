/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.VerificarConsultaControl;
import Model.ConsultaRaiz;
import Model.FachadaModels;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author humbe
 */
public class JIFVerificarConsulta extends javax.swing.JInternalFrame {

    private static FachadaModels model;

    public JIFVerificarConsulta(FachadaModels model) {
        if (model != null) {
            this.model = model;
        } else {
            this.model = new FachadaModels();
        }

        initComponents();
    }
    
    
    public JComboBox getComboPesquisa() {
        return this.jComboBox1;
    }

    public void addTipoLabelCombo(String label) {
        if (label != null) {
            this.jLabel2.setText(label);
        }
    }
    
    public JTextField getTextPesquisa() {
        return this.jTextFieldPesquisar;
    }

    public void mostraMensagem(String mensagem) {
        if (mensagem != null) {
            JOptionPane.showMessageDialog(rootPane, mensagem);
        }
    }

    public JTable getTableConsultas() {
        return this.jTableConsultas;
    }
    
    
    public String getCodigo(){
        return this.jTextFieldCodigo.getText();
    }
    public String getData(){
        return this.jTextFieldData.getText();
    }
    public String getHora(){
        return this.jTextFieldHora.getText();
    }
    public JComboBox getComboPacientes() {
        return this.jComboBoxPacientes;
    }
    public JComboBox getComboMedicos() {
        return this.jComboBoxMedicos;
    }
    public JComboBox getComboSalas() {
        return this.jComboBoxSalas;
    }
    
    
    public void setCodigo(String codigo){
        this.jTextFieldCodigo.setText(codigo);
    }
    public void setData(String data){
        this.jTextFieldData.setText(data);
    }
    public void setHora(String hora){
        this.jTextFieldHora.setText(hora);
    }
    public void setComboPacientes(String paciente){
        this.jComboBoxPacientes.setSelectedItem(paciente);
    }
    public void setComboMedicos(String medico){
        this.jComboBoxMedicos.setSelectedItem(medico);
    }
    public void setComboSalas(int sala){
        this.jComboBoxPacientes.setSelectedItem(sala);
    }
    
    
    public boolean validaCampos() {
        
        if (this.jTextFieldData.getText().trim().equals("")) {
            this.mostraMensagem("Informe a data da consulta.");
            this.jTextFieldData.requestFocus();
            return false;
        }
        
        if (this.jTextFieldHora.getText().trim().equals("")) {
            this.mostraMensagem("Informe a hora da consulta.");
            this.jTextFieldHora.requestFocus();
            return false;
        }
        
        if (this.jComboBoxPacientes.getSelectedIndex() == -1) {
            this.mostraMensagem("Informe o paciente da consulta.");
            this.jComboBoxPacientes.requestFocus();
            return false;
        }
        
        if (this.jComboBoxMedicos.getSelectedIndex() == -1) {
            this.mostraMensagem("Informe o médico da consulta.");
            this.jComboBoxMedicos.requestFocus();
            return false;
        }
        
        if (this.jComboBoxSalas.getSelectedIndex() == -1) {
            this.mostraMensagem("Informe a sala da consulta.");
            this.jComboBoxSalas.requestFocus();
            return false;
        }
    
        return true;
        
    }
    
    
    public void setaCampos(ConsultaRaiz consulta) {
       
        this.setCodigo(String.valueOf(consulta.getIdConsulta()));
        this.setData(consulta.getDataConsulta());
        this.setHora(consulta.getHoraConsulta());
        this.setComboPacientes(consulta.getPaciente().getNomePaciente());
        this.setComboMedicos(consulta.getMedico().getNomeMedico());
        this.setComboSalas(consulta.getSala().getNumeroSala());
        
        this.enabledCampos(true);
        
    }
    
    
    public void limpaCampos() {
        this.setCodigo("");
        this.setData("");
        this.setHora("");
        this.getComboPacientes().setSelectedIndex(-1);
        this.getComboMedicos().setSelectedIndex(-1);
        this.getComboSalas().setSelectedIndex(-1);
  
        this.enabledCampos(false);
    }
    
    public void enabledCampos(boolean valor){
        this.jTextFieldCodigo.setEnabled(false);
        this.jTextFieldData.setEnabled(valor);
        this.jTextFieldHora.setEnabled(valor);
        this.jComboBoxPacientes.setEnabled(valor);
        this.jComboBoxMedicos.setEnabled(valor);
        this.jComboBoxSalas.setEnabled(valor);
    }
    
    public void fecha() {
        this.setVisible(false);
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableConsultas = new javax.swing.JTable();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldData = new javax.swing.JTextField();
        jTextFieldHora = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxPacientes = new javax.swing.JComboBox<>();
        jComboBoxMedicos = new javax.swing.JComboBox<>();
        jComboBoxSalas = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButtonDeletar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jLabel3.setText("Pesquisar Por:");

        jComboBox1.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nome" }));
        jComboBox1.setLightWeightPopupEnabled(false);
        jComboBox1.setMinimumSize(new java.awt.Dimension(6, 23));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jTableConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Hora", "Paciente", "Médico", "Sala"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableConsultasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableConsultas);

        jTextFieldPesquisar.setFont(new java.awt.Font("Arial Narrow", 0, 14)); // NOI18N
        jTextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarKeyTyped(evt);
            }
        });

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon("C:\\Users\\humbe\\Desktop\\research.png")); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jLabel2.setText("Código");

        jLabel1.setText("Código");

        jLabel4.setText("Data");

        jTextFieldData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDataActionPerformed(evt);
            }
        });

        jLabel5.setText("Hora");

        jLabel6.setText("Paciente");

        jLabel7.setText("Médico");

        jLabel8.setText("Sala");

        jButtonDeletar.setIcon(new javax.swing.ImageIcon("C:\\Users\\humbe\\Desktop\\x-button.png")); // NOI18N
        jButtonDeletar.setText("Deletar");
        jButtonDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon("C:\\Users\\humbe\\Desktop\\checked.png")); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldCodigo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jComboBoxPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxMedicos, javax.swing.GroupLayout.Alignment.TRAILING, 0, 110, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(jButtonDeletar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBoxSalas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonPesquisar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel8)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxMedicos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxSalas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonDeletar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        VerificarConsultaControl control = new VerificarConsultaControl(this, this.model);
        try {
            control.evento("MUDAR TIPO PESQUISA");
        } catch (SQLException ex) {
            Logger.getLogger(JIFVerificarConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTableConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultasMouseClicked
        VerificarConsultaControl control = new VerificarConsultaControl(this, this.model);
        try {
            control.evento("SELECAO");
        } catch (SQLException ex) {
            Logger.getLogger(JIFVerificarConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableConsultasMouseClicked

    private void jTextFieldPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyTyped
        VerificarConsultaControl control = new VerificarConsultaControl(this, this.model);
        control.evento(evt);
    }//GEN-LAST:event_jTextFieldPesquisarKeyTyped

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        VerificarConsultaControl control = new VerificarConsultaControl(this, this.model);
        try {
            control.evento("LISTAR");
        } catch (SQLException ex) {
            Logger.getLogger(JIFVerificarConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jTextFieldDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDataActionPerformed

    private void jButtonDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarActionPerformed
        VerificarConsultaControl control = new VerificarConsultaControl(this, this.model);
        try{
            control.evento("EXCLUIR");
        }catch(SQLException ex){
            Logger.getLogger(JIFVerificarConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDeletarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        VerificarConsultaControl control = new VerificarConsultaControl(this, this.model);
        try{
            control.evento("ALTERAR");
        }catch(SQLException ex){
            Logger.getLogger(JIFVerificarConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        VerificarConsultaControl control = new VerificarConsultaControl(this, this.model);
        try {
            control.evento("ABRE TELA");
        } catch (SQLException ex) {
            Logger.getLogger(JIFVerificarMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonDeletar;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxMedicos;
    private javax.swing.JComboBox<String> jComboBoxPacientes;
    private javax.swing.JComboBox<String> jComboBoxSalas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableConsultas;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldData;
    private javax.swing.JTextField jTextFieldHora;
    private javax.swing.JTextField jTextFieldPesquisar;
    // End of variables declaration//GEN-END:variables
}
