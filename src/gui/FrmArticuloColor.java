/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Data.Clases.Articulo;
import Data.Clases.Color;
import Data.Clases.Imagen;
import Data.Data;
import Data.Modelos.ColorListModel;
import Data.Modelos.ModArticulo_Color;
import Data.Modelos.ModArticulo_Color_Imagen;
import Data.Modelos.ModImagenes;
import Data.Renders.ListaImagenesRender;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author victor
 */
public class FrmArticuloColor extends javax.swing.JFrame {

    private Articulo _articulo = null;
    private Color _color = null;
    private ModArticulo_Color_Imagen _modArticulo_Color_Imagen = null;
    private boolean _bModificar = false;
    private boolean _bCambios = false;
    private IfrImagenes_Articulo_Color _ifrImagenes = null;
    
    /**
     * Creates new form FrmArticuloColor
     */
    public FrmArticuloColor(Integer iId_Articulo, Integer iId_Color) {
        initComponents();
        
        try {
            cmbColor.setModel(new ColorListModel(Color.Select(null)));
        } catch (Exception ex) {
            System.out.println("Error al acceder a la tabla colores\n" +ex.toString());
        }
        
        try {
            _articulo = new Articulo(iId_Articulo);
        } catch (Exception ex) {
            System.out.println("Error al buscar el artículo. "+ex.toString());
        }
        
        if(iId_Color != null){
            _bModificar = true;
            try {
                _color = new Color(iId_Color);
            } catch (Exception ex) {
                System.out.println("Error al buscar el color. "+ex.toString());
            }
            cmbColor.setSelectedIndex(((ColorListModel)cmbColor.getModel()).getIndexColor(iId_Color));
            cmbColor.setEnabled(false);
            butAgregarColor.setVisible(false);
            this.setTitle("Modificar color "+_color);
             
            try {
                _modArticulo_Color_Imagen = new ModArticulo_Color_Imagen(_articulo, _color);
                lImagenes.setModel(_modArticulo_Color_Imagen);
                lImagenes.setCellRenderer(new ListaImagenesRender());
            } catch (Exception ex) {
                System.out.println("Error al buscar las imágenes. "+ex.toString());
            }
        }
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                salir();
                System.exit(0);
            }
        });
    }
    
    private void guardar(){
        if(!_bModificar){
            try {
                _articulo.Add_Color(_color);
                _bModificar = true;
                _bCambios = false;

                JOptionPane.showMessageDialog(null, 
                "Los cambios se han guardado correctamente.", 
                "Mensaje del sistema", 
                JOptionPane.PLAIN_MESSAGE);
            } catch (Exception ex) {
                System.out.println("Error al añadir el color. "+ex.toString());
            }
        }
        if(_ifrImagenes != null) _ifrImagenes.dispose();
    }
    
    private void comprobar_cambios(){
        if(_bCambios){
            Object[] options = {"Sí",
                                "No"};
            int n = JOptionPane.showOptionDialog(this,
                "Hay cambios sin guardar, ¿desea guardarlos antes de continuar?.",
                "Mensaje del sistema",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title

            if(n == 0)
            {
                guardar();
            }
        }
    }
    
    private void salir(){
        comprobar_cambios();
        if(!_bModificar && _color != null){
            try {
                _articulo.Delete_Color(_color.getId());
            } catch (Exception ex) {
                System.out.println("Error al eliminar el color. "+ex.toString());
            }
        }
        
        if(_ifrImagenes != null) _ifrImagenes.dispose();
    }
    
    private boolean color_esta_asociado(){
        
        ArrayList<Integer> lColores = _articulo.getColores();
        boolean bEsta_asociado = false;
        for(Integer i : lColores)
            if(i == _color.getId()) bEsta_asociado = true;

        if(bEsta_asociado){
            JOptionPane.showMessageDialog(null, 
                "Error, ese color ya está asociado a este artículo.", 
                "Color ya asociado", 
                JOptionPane.WARNING_MESSAGE);

            return true;
        }
        else{
            return false;
        }
    }
    
    private boolean comprobar_color(){
        if(_color == null){
            JOptionPane.showMessageDialog(null, 
                "Error, debe elegir primero un color.", 
                "Elija un color", 
                JOptionPane.WARNING_MESSAGE);
            
            return false;
        }
        else
            return true;  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblColor = new javax.swing.JLabel();
        cmbColor = new javax.swing.JComboBox<>();
        butAtras = new javax.swing.JButton();
        butGuardar = new javax.swing.JButton();
        butAgregarColor = new javax.swing.JButton();
        lblImagenes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lImagenes = new javax.swing.JList<>();
        butElegir = new javax.swing.JButton();
        butSubir = new javax.swing.JButton();
        butEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Asignar color al artículo");

        lblColor.setText("Color");

        cmbColor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbColorActionPerformed(evt);
            }
        });

        butAtras.setText("Atrás");
        butAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAtrasActionPerformed(evt);
            }
        });

        butGuardar.setText("Guardar cambios");
        butGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGuardarActionPerformed(evt);
            }
        });

        butAgregarColor.setText("+");
        butAgregarColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarColorActionPerformed(evt);
            }
        });

        lblImagenes.setText("Imágenes");

        jScrollPane1.setViewportView(lImagenes);

        butElegir.setText("Elegir Imagen");
        butElegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butElegirActionPerformed(evt);
            }
        });

        butSubir.setText("Subir Imagen");
        butSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSubirActionPerformed(evt);
            }
        });

        butEliminar.setText("Eliminar Imagen");
        butEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarActionPerformed(evt);
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
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(butGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butAtras))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblColor)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(butAgregarColor))
                            .addComponent(lblImagenes))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butElegir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butSubir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblColor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butAgregarColor))
                .addGap(18, 18, 18)
                .addComponent(lblImagenes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(butAtras)
                            .addComponent(butGuardar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butElegir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butSubir)
                        .addGap(18, 18, 18)
                        .addComponent(butEliminar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void butAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAtrasActionPerformed
        salir();
        java.awt.EventQueue.invokeLater(() -> {
            Frame frmArticulo = null;
            try {
                frmArticulo = new FrmArticulo(_articulo.getId(),null);
            } catch (Exception ex) {
                System.out.println("Error al leer el artículo. "+ ex.toString());
            }
            if(frmArticulo != null){
                frmArticulo.setLocationRelativeTo(this);
                frmArticulo.setVisible(true);
            }
        });
        this.dispose();
    }//GEN-LAST:event_butAtrasActionPerformed

    private void butGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_butGuardarActionPerformed

    private void butAgregarColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarColorActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame frmColor = frmColor = new FrmColor(cmbColor);
            frmColor.setLocationRelativeTo(FrmArticuloColor.this);
            frmColor.setVisible(true);
        });
    }//GEN-LAST:event_butAgregarColorActionPerformed

    private void butElegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butElegirActionPerformed
        if(comprobar_color()){
            java.awt.EventQueue.invokeLater(() -> {
                if(_ifrImagenes == null || !_ifrImagenes.bAbierto){
                    try {
                        _ifrImagenes = new IfrImagenes_Articulo_Color(_modArticulo_Color_Imagen);
                    } catch (Exception ex) {
                        System.out.println("Error al leer la lista de imágenes. "+ ex.toString());
                    }
                }

                _ifrImagenes.setLocationRelativeTo(FrmArticuloColor.this);
                _ifrImagenes.setBounds(this.getX()+this.getWidth()-10, 
                        this.getY()+30, _ifrImagenes.getWidth(), _ifrImagenes.getHeight());
                _ifrImagenes.setVisible(true);
            });
            _bCambios = true;
        }
    }//GEN-LAST:event_butElegirActionPerformed

    private void butSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSubirActionPerformed
        if(comprobar_color()){
            JFileChooser archivo = new JFileChooser();

            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG)", "jpg","jpeg");
            archivo.addChoosableFileFilter(filtro);
            archivo.setFileFilter(filtro);
            archivo.setDialogTitle("Abrir Imagen");

            File ruta = null;
            try {
                ruta = new File(Data.RutaImagenes());
            } catch (IOException ex) {
                System.out.println("Error en la lectura de la ruta. "+ ex.toString());
            }
            archivo.setCurrentDirectory(ruta);

            int ventana = archivo.showOpenDialog(null);
            if(ventana == JFileChooser.APPROVE_OPTION)
            {
                File file = archivo.getSelectedFile();
                String sRuta = null;
                Imagen imagen = null;
                try {
                    sRuta = file.getPath().replace(Data.RutaImagenes(), "");
                    sRuta = sRuta.replace(file.getName(), "");
                    if(Imagen.Select(file.getName(), null).size() > 0)
                        imagen = Imagen.Select(file.getName(), null).get(0);
                    else
                        imagen = Imagen.Create(file.getName(), sRuta);
                } catch (Exception ex) {
                    System.out.println("Error en la creación de la imagen. "+ ex.toString());
                }
                if(imagen != null){
                    try {
                        _modArticulo_Color_Imagen.addImagen(imagen);
                    } catch (Exception ex) {
                        System.out.println("Error al añadir la imagen. "+ ex.toString());
                    }
                }
            }
            _bCambios = true;
        }
    }//GEN-LAST:event_butSubirActionPerformed

    private void butEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarActionPerformed
        int index = lImagenes.getSelectedIndex();
        
        if(index != -1)
        {
            Object[] options = {"Sí",
                                "No"};
            int n = JOptionPane.showOptionDialog(this,
                "¿Está seguro? La imagen dejará de estar asociada a este color."
                        + "\n Esta acción no se puede deshacer.",
                "Eliminar imagen",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title

            if(n == 0)
            {
                try {
                    _modArticulo_Color_Imagen.removeImagen(index);
                } catch (Exception ex) {
                    System.out.println("Error en la eliminación de la imagen. "+ ex.toString());
                }
                _bCambios = true;
            }
        }
    }//GEN-LAST:event_butEliminarActionPerformed

    private void cmbColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbColorActionPerformed
        if(!_bModificar){
            _color = (Color)cmbColor.getSelectedItem();
            if(!color_esta_asociado()){
                cmbColor.setEnabled(false);
                butAgregarColor.setVisible(false);

                try {
                    _modArticulo_Color_Imagen = new ModArticulo_Color_Imagen(_articulo, _color);
                    lImagenes.setModel(_modArticulo_Color_Imagen);
                    lImagenes.setCellRenderer(new ListaImagenesRender());
                } catch (Exception ex) {
                    System.out.println("Error al buscar las imágenes. "+ex.toString());
                }
                _bCambios = true;
            } 
            else{
                cmbColor.setSelectedItem(null);
            }
        }
    }//GEN-LAST:event_cmbColorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmArticuloColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmArticuloColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmArticuloColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmArticuloColor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmArticuloColor(null, null, null).setVisible(true);
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAgregarColor;
    private javax.swing.JButton butAtras;
    private javax.swing.JButton butElegir;
    private javax.swing.JButton butEliminar;
    private javax.swing.JButton butGuardar;
    private javax.swing.JButton butSubir;
    private javax.swing.JComboBox<String> cmbColor;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lImagenes;
    private javax.swing.JLabel lblColor;
    private javax.swing.JLabel lblImagenes;
    // End of variables declaration//GEN-END:variables
}
