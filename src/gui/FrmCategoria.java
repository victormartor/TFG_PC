/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Data.Clases.Articulo;
import Data.Clases.Categoria;
import Data.Data;
import Data.Clases.Imagen;
import Data.Clases.Marca;
import Data.Modelos.ModArticulos;
import Data.Modelos.ModCategorias;
import Data.Renders.ListaRender;
import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author victor
 */
public class FrmCategoria extends javax.swing.JFrame {

    private Categoria _categoria = null;
    private ModCategorias _modCategorias = null;
    private ModArticulos _modArticulos = null;
    private IfrImagenes _ifrImagenes = null;
    private boolean _bModificar = false;
    
    /**
     * Creates new form FrmCategoria
     */
    public FrmCategoria(Categoria categoria, ModCategorias modCategorias, Marca marca) throws Exception {
        initComponents();
        
        if(categoria != null){
            _bModificar = true;
            _categoria = categoria;
            txtNombre.setText(_categoria.getNombre());
        }
        else{
            _categoria = Categoria.Create("", -1, marca.getId());
        }
        
        _modCategorias = modCategorias;
        cargarImagen();
        
        //LISTA DE ARTÍCULOS
        _modArticulos = new ModArticulos(_categoria.getId(),null);
        lArticulos.setModel(_modArticulos);
        lArticulos.setCellRenderer(new ListaRender());
        
        if(_bModificar)
            this.setTitle("Modificar categoría");
        
        lArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()==2){
                   modificarArticulo();
                }
           }
        });
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                cancelar();
            }
        });
    }
    
    private void modificarArticulo(){
        int iIndex = lArticulos.getSelectedIndex();
        Articulo articulo = _modArticulos.getArticulo(iIndex);

        java.awt.EventQueue.invokeLater(() -> {
            Frame frmArticulo = null;
            try {
                frmArticulo = new FrmArticulo(articulo, _modArticulos, _categoria);
            } catch (Exception ex) {
                System.out.println("Error al buscar el artículo en la base de datos. "+ ex.toString());
            }
            if(frmArticulo != null){
                frmArticulo.setLocationRelativeTo(FrmCategoria.this);
                frmArticulo.setVisible(true);
            }
        });
    }
    
    private void cargarImagen() throws Exception{
        if(_categoria.getId_Imagen() != -1){
            Image image = new ImageIcon(new Imagen(_categoria.getId_Imagen()).getRutaCompleta()).getImage();
            ImageIcon iconoEscalado = new ImageIcon (image.getScaledInstance(100,100,Image.SCALE_SMOOTH));
            iconoImagen.setIcon(iconoEscalado);
        }
        else{
            iconoImagen.setText("<html><body>Elige una <br> imagen</body></html>");
        }
    }
    
    private void cancelar(){
        try {
            if(!_bModificar){
                _categoria.Delete();
            }    
            else{
                Categoria categoria = new Categoria(_categoria.getId());
                //_categoria.setNombre(categoria.getNombre());
                _categoria.setId_Imagen(categoria.getId_Imagen());
            }
        } catch (Exception ex) {
            System.out.println("Error en la eliminación de la categoria. "+ ex.toString());
        }
        if(_ifrImagenes != null) _ifrImagenes.dispose();
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        iconoImagen = new javax.swing.JLabel();
        butElegir = new javax.swing.JButton();
        butSubir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        butCancelar = new javax.swing.JButton();
        butAceptar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lblArticulos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lArticulos = new javax.swing.JList<>();
        butAgregarArt = new javax.swing.JButton();
        butEliminarArt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Agregar categoría");
        setResizable(false);

        lblNombre.setText("Nombre");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        iconoImagen.setBackground(new java.awt.Color(255, 255, 255));
        iconoImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoImagen.setToolTipText("Imagen de la marca");
        iconoImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        iconoImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconoImagenMouseClicked(evt);
            }
        });

        butElegir.setText("Elegir imagen");
        butElegir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butElegirActionPerformed(evt);
            }
        });

        butSubir.setText("Subir imagen");
        butSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSubirActionPerformed(evt);
            }
        });

        butCancelar.setText("Cancelar");
        butCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butCancelarActionPerformed(evt);
            }
        });

        butAceptar.setText("Aceptar");
        butAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAceptarActionPerformed(evt);
            }
        });

        lblArticulos.setText("Artículos");

        lArticulos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lArticulos);

        butAgregarArt.setText("Agregar Artículo");
        butAgregarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarArtActionPerformed(evt);
            }
        });

        butEliminarArt.setText("Eliminar Artículo");
        butEliminarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarArtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addGap(0, 206, Short.MAX_VALUE))
                            .addComponent(txtNombre))
                        .addGap(18, 18, 18)
                        .addComponent(iconoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butElegir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butSubir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jSeparator1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(butAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butCancelar))
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblArticulos)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butAgregarArt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butEliminarArt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butElegir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butSubir))
                    .addComponent(iconoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblArticulos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(butCancelar)
                            .addComponent(butAceptar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butAgregarArt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butEliminarArt)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void iconoImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconoImagenMouseClicked

    }//GEN-LAST:event_iconoImagenMouseClicked

    private void butElegirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butElegirActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            if(_ifrImagenes == null || !_ifrImagenes.bAbierto){
                try {
                    _ifrImagenes = new IfrImagenes(iconoImagen, null, _categoria);
                } catch (Exception ex) {
                    System.out.println("Error al leer la lista de imágenes. "+ ex.toString());
                }
            }
            
            _ifrImagenes.setLocationRelativeTo(FrmCategoria.this);
            _ifrImagenes.setBounds(this.getX()+this.getWidth()-10, 
                    this.getY()+30, _ifrImagenes.getWidth(), _ifrImagenes.getHeight());
            _ifrImagenes.setVisible(true);
        });
    }//GEN-LAST:event_butElegirActionPerformed

    private void butSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSubirActionPerformed
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
                _categoria.setId_Imagen(imagen.getId());
                try {
                    //_marca.Update();
                    cargarImagen();
                } catch (Exception ex) {
                    System.out.println("Error en la actualización de la imagen de la marca. "+ ex.toString());
                }
            }
        }
    }//GEN-LAST:event_butSubirActionPerformed

    private void butCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelarActionPerformed
        cancelar();
    }//GEN-LAST:event_butCancelarActionPerformed

    private void butAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAceptarActionPerformed
        try {
            _categoria.setNombre(txtNombre.getText());
            _categoria.Update();
            if(!_bModificar) _modCategorias.addCategoria(_categoria);
        } catch (Exception ex) {
            System.out.println("Error en la creación o modificación de la marca. "+ ex.toString());
        }
        if(_ifrImagenes != null) _ifrImagenes.dispose();
        this.dispose();
    }//GEN-LAST:event_butAceptarActionPerformed

    private void butAgregarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarArtActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame frmArticulo = null;
            try {
                frmArticulo = new FrmArticulo(null, _modArticulos, _categoria);
            } catch (Exception ex) {
                System.out.println("Error al buscar el artículo en la base de datos. "+ ex.toString());
            }
            if(frmArticulo != null){
                frmArticulo.setLocationRelativeTo(FrmCategoria.this);
                frmArticulo.setVisible(true);
            }
        });
    }//GEN-LAST:event_butAgregarArtActionPerformed

    private void butEliminarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarArtActionPerformed
        int index = lArticulos.getSelectedIndex();
        
        if(index != -1)
        {
            Object[] options = {"Sí",
                                "No"};
            int n = JOptionPane.showOptionDialog(this,
                "¿Está seguro? Se eliminarán además todos los datos asociados a este artículo."
                        + "\n Esta acción no se puede deshacer.",
                "Eliminar artículo",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title

            if(n == 0)
            {
                try {
                    _modArticulos.removeArticulo(index);
                } catch (Exception ex) {
                    System.out.println("Error en la eliminación del artículo. "+ ex.toString());
                }
            }
        }
    }//GEN-LAST:event_butEliminarArtActionPerformed

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
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new FrmCategoria(null, null, null).setVisible(true);
            } catch (Exception ex) {
                Logger.getLogger(FrmCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAceptar;
    private javax.swing.JButton butAgregarArt;
    private javax.swing.JButton butCancelar;
    private javax.swing.JButton butElegir;
    private javax.swing.JButton butEliminarArt;
    private javax.swing.JButton butSubir;
    private javax.swing.JLabel iconoImagen;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JList<String> lArticulos;
    private javax.swing.JLabel lblArticulos;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
