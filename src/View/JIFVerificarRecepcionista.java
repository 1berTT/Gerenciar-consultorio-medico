/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Control.VerificarRecepcionistaControl;
import Model.FachadaModels;
import Model.RecepcionistaRaiz;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author humbe
 */
public class JIFVerificarRecepcionista extends javax.swing.JInternalFrame {

    private static FachadaModels model;
    
    public JIFVerificarRecepcionista(FachadaModels model) {
        if (model != null) {
            this.model = model;
        } else {
            this.model = new FachadaModels();
        }
        
        initComponents();
    }
    
    
    public String getCodigo() {
        return this.jTextFieldCodigo.getText();
    }
    
    public String getNome() {
        return this.jTextFieldNome.getText();
    }
    
    public String getTelefone() {
        return this.jTextFieldTelefone.getText();
    }
    
    public String getEmail(){
        return this.jTextFieldEmail.getText();
    }
    
    public String getSenha(){
        return this.jTextFieldSenha.getText();
    }
            
    public JTable getTableRecepcionistas() {
        return this.jTableRecepcionistas;
    }
    
    
    public void setCodigo(String codigo) {
        this.jTextFieldCodigo.setText(codigo);
    }
    
    public void setNome(String nome) {
        this.jTextFieldNome.setText(nome);
    }
    
    public void setTelefone(String telefone) {
        this.jTextFieldTelefone.setText(telefone);
    }
    
    public void setEmail(String email) {
        this.jTextFieldEmail.setText(email);
    }
    
    public void setSenha(String senha) {
        this.jTextFieldSenha.setText(senha);
    }
    
    
    public void fecha() {
        this.setVisible(false);
    }
    
    public void mostraMensagem(String mensagem) {
        if (mensagem != null) {
            JOptionPane.showMessageDialog(rootPane, mensagem);
        }
    }
    
    
    public void setaCampos(RecepcionistaRaiz recepcionista) {
        this.setCodigo(String.valueOf(recepcionista.getIdRecepcionista()));
        this.setNome(recepcionista.getNomeRecepcionista());
        this.setTelefone(recepcionista.getTelefone());
        this.setEmail(recepcionista.getEmail());
        this.setSenha(recepcionista.getSenha());
    
        this.enabledCampos(true);
        
    }
    
    public void limpaCampos() {
        this.setCodigo("");
        this.setNome("");
        this.setTelefone("");
        this.setEmail("");
        this.setSenha("");
        this.enabledCampos(false);
    }
    
    public void enabledCampos(boolean valor){
        this.jTextFieldCodigo.setEnabled(false);
        this.jTextFieldEmail.setEnabled(valor);
        this.jTextFieldNome.setEnabled(valor);
        this.jTextFieldTelefone.setEnabled(valor);
        this.jTextFieldSenha.setEnabled(valor);
    }
    
    public boolean validaCampos() {
        if (this.jTextFieldNome.getText().trim().equals("")) {
            this.mostraMensagem("Informe o nome do recepcionista.");
            this.jTextFieldNome.requestFocus();
            return false;
        }

        if (this.jTextFieldTelefone.getText().trim().equals("")) {
            this.mostraMensagem("Informe o telefone do recepcionista.");
            this.jTextFieldTelefone.requestFocus();
            return false;
        }
        
        if (this.jTextFieldEmail.getText().trim().equals("")) {
            this.mostraMensagem("Informe o email do recepcionista.");
            this.jTextFieldEmail.requestFocus();
            return false;
        }
        
        if (this.jTextFieldSenha.getText().trim().equals("")) {
            this.mostraMensagem("Informe a senha do recepcionista.");
            this.jTextFieldSenha.requestFocus();
            return false;
        }
        
        return true;
    }
        
        
        
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRecepcionistas = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jTextFieldTelefone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButtonAlterar = new javax.swing.JButton();
        jButtonDeletar = new javax.swing.JButton();
        jTextFieldSenha = new javax.swing.JTextField();

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

        jTableRecepcionistas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome", "Telefone", "E-mail", "Senha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRecepcionistas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRecepcionistasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableRecepcionistas);

        jLabel6.setText("E-mail");

        jLabel1.setText("Código");

        jLabel4.setText("Nome");

        jLabel5.setText("Telefone");

        jLabel7.setText("Senha");

        jButtonAlterar.setIcon(new javax.swing.ImageIcon("C:\\Users\\humbe\\Desktop\\checked.png")); // NOI18N
        jButtonAlterar.setText("Alterar");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonDeletar.setIcon(new javax.swing.ImageIcon("C:\\Users\\humbe\\Desktop\\x-button.png")); // NOI18N
        jButtonDeletar.setText("Deletar");
        jButtonDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addComponent(jButtonDeletar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldSenha)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 640, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jLabel7)
                        .addComponent(jLabel5)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAlterar)
                    .addComponent(jButtonDeletar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableRecepcionistasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRecepcionistasMouseClicked
        VerificarRecepcionistaControl control = new VerificarRecepcionistaControl(this, this.model);
        try {
            control.evento("SELECAO");
        } catch (SQLException ex) {
            Logger.getLogger(JIFVerificarRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableRecepcionistasMouseClicked

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        VerificarRecepcionistaControl control = new VerificarRecepcionistaControl(this, this.model);
        try {
            control.evento("ALTERAR");
        } catch (SQLException ex) {
            Logger.getLogger(JIFVerificarRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarActionPerformed
        VerificarRecepcionistaControl control = new VerificarRecepcionistaControl(this, this.model);
        try {
            control.evento("EXCLUIR");
        } catch (SQLException ex) {
            Logger.getLogger(JIFVerificarRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDeletarActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        VerificarRecepcionistaControl control = new VerificarRecepcionistaControl(this, this.model);
        try {
            control.evento("ABRIR TELA");
        } catch (SQLException ex) {
            Logger.getLogger(JIFVerificarRecepcionista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formInternalFrameOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonDeletar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableRecepcionistas;
    private javax.swing.JTextField jTextFieldCodigo;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldSenha;
    private javax.swing.JTextField jTextFieldTelefone;
    // End of variables declaration//GEN-END:variables
}
