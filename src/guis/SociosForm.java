/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import Entidades.Socio;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import persistencia.ISociosDAO;

/**
 *
 * @author LV1823
 */
public class SociosForm extends javax.swing.JFrame {

    /**
     * Creates new form SociosForm
     */
    private ISociosDAO sociosDAO;

    public SociosForm(ISociosDAO sociosDAO) {
        initComponents();
        this.sociosDAO = sociosDAO;
        this.llenarTabla();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private void guardar() {
        // TODO: COMPROBAR SI SE AGREGA O SE ACTUALIZA
        if (this.txtSocio.getText().isEmpty()) {
            this.agregar();
        }else{
            this.actualizar();
        }
    }

    private void agregar() {
        String nombre = this.txtNombre.getText();
        String curp = this.txtCurp.getText();
        // TODO: VALIDACIONES DE DATOS
        Socio socioNuevo = new Socio(nombre, curp);
        boolean seAgregoSocio = this.sociosDAO.agregar(socioNuevo);
        if (seAgregoSocio) {
            JOptionPane.showMessageDialog(this, "Se agrego el socio", "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            this.limpiarFormulario();
            this.llenarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se agrego el socio", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarTabla() {
        List<Socio> listaSocios = this.sociosDAO.consultarTodos();
        DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();
        modelo.setRowCount(0);
        listaSocios.forEach(socio -> {
            Object[] fila = new Object[3];
            fila[0] = socio.getId_socio();
            fila[1] = socio.getNombre();
            fila[2] = socio.getCurp();
            modelo.addRow(fila);
        });
    }

    private void actualizar() {
        Long idSocio = Long.parseLong(this.txtNombre.getText());
        String nombre = this.txtNombre.getText();
        String curp = this.txtCurp.getText();
        // TODO: VALIDACIONES DE DATOS
        Socio socioActualizado = new Socio(idSocio, nombre, curp);
        boolean seActualizoSocio = this.sociosDAO.agregar(socioActualizado);
        if (seActualizoSocio) {
            JOptionPane.showMessageDialog(this, "Se actualizo el socio", "Información",
                    JOptionPane.INFORMATION_MESSAGE);
            this.limpiarFormulario();
            this.llenarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "No se actualizo el socio", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        //Obtener el id socio seleccionado
        Long idSocioSeleccionado = this.getIdSocioSeleccionado();
        if (idSocioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un socio", "Información",
                     JOptionPane.ERROR_MESSAGE);
            return;
        }
        int opcionSeleccionado = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas eliminar al socio?", "Confirmación",
                 JOptionPane.YES_NO_OPTION);
        if (opcionSeleccionado == JOptionPane.NO_OPTION) {
            return;
        }
        boolean seEliminoSocio = this.sociosDAO.eliminar(idSocioSeleccionado);
        if (seEliminoSocio) {
            JOptionPane.showMessageDialog(this, "Se elimino el socio", "Información",
                     JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar el socio", "Información",
                     JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editar() {
        //Obtener el id socio seleccionado
        Long idSocioSeleccionado = this.getIdSocioSeleccionado();
        if (idSocioSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un socio", "Información",
                     JOptionPane.ERROR_MESSAGE);
            return;
        }
        Socio socio = this.sociosDAO.consultar(idSocioSeleccionado);
        if (socio != null) {
            llenarFormulario(socio);
        } else {
            JOptionPane.showMessageDialog(this, "El socio ya no existe", "Información",
                     JOptionPane.ERROR_MESSAGE);
        }
    }

    private void llenarFormulario(Socio socio) {
        this.txtSocio.setText(socio.getId_socio().toString());
        this.txtNombre.setText(socio.getNombre());
        this.txtCurp.setText(socio.getCurp());
    }

    private Long getIdSocioSeleccionado() {
        int indiceFilaSeleccionada = this.jTable1.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.jTable1.getModel();
            int indiceColumnaId = 0;
            Long idSocioSeleccionado = (Long) modelo.getValueAt(indiceFilaSeleccionada, indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return null;
        }
    }

    private void limpiarFormulario() {
        this.txtSocio.setText(null);
        this.txtNombre.setText("");
        this.txtCurp.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        lblsocio = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblcurp = new javax.swing.JLabel();
        txtSocio = new javax.swing.JTextField();
        txtCurp = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlTablaSocios = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblsocio.setText("id_socio");

        lblNombre.setText("Nombre");

        lblcurp.setText("Curp");

        txtSocio.setEditable(false);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Socio", "Nombre", "Curp"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        pnlTablaSocios.setViewportView(jTable1);

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblsocio, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblcurp)
                    .addComponent(lblNombre))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addComponent(txtCurp)
                    .addComponent(txtNombre)
                    .addComponent(txtSocio))
                .addGap(28, 28, 28)
                .addComponent(pnlTablaSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblsocio)
                            .addComponent(txtSocio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcurp)
                            .addComponent(txtCurp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnGuardar)
                            .addComponent(btnCancelar)))
                    .addComponent(pnlTablaSocios, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        this.guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.limpiarFormulario();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        this.eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        this.editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblcurp;
    private javax.swing.JLabel lblsocio;
    private javax.swing.JScrollPane pnlTablaSocios;
    private javax.swing.JTextField txtCurp;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtSocio;
    // End of variables declaration//GEN-END:variables

}