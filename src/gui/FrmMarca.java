/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Data.Data;
import Data.Imagen;
import Data.Marca;
import Data.Modelos.ModImagenes;
import Data.Modelos.ModMarcas;
import Data.Renders.ListaImagenesRender;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author victor
 */
public class FrmMarca extends javax.swing.JFrame {

    private Marca _marca = null;
    private ModMarcas _modMarcas = null;
    /**
     * Creates new form FrmMarca
     */
    public FrmMarca(Marca marca, ModMarcas modMarcas) throws Exception {
        initComponents();
        
        _marca = Marca.Create("", -1);
        _modMarcas = modMarcas;
        //listImagen.setModel(new ModImagenes());
        //listImagen.setCellRenderer(new ListaImagenesRender());
        txtNombre.setText(_marca.getNombre());
        cargarImagen();
    }
    
    private void cargarImagen() throws Exception{
        if(_marca.getId_Imagen() != -1){
            Image image = new ImageIcon(new Imagen(_marca.getId_Imagen()).getRutaCompleta()).getImage();
            ImageIcon iconoEscalado = new ImageIcon (image.getScaledInstance(100,100,Image.SCALE_SMOOTH));
            iconoImagen.setIcon(iconoEscalado);
        }
        else{
            iconoImagen.setText("<html><body>Elige una <br> imagen</body></html>");
        }
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
        butCancelar = new javax.swing.JButton();
        butAceptar = new javax.swing.JButton();
        iconoImagen = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblCategorias = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar o modificar marca");

        lblNombre.setText("Nombre");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
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

        iconoImagen.setBackground(new java.awt.Color(255, 255, 255));
        iconoImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconoImagen.setToolTipText("");
        iconoImagen.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        iconoImagen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iconoImagenMouseClicked(evt);
            }
        });

        lblCategorias.setText("Categorias");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(iconoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCategorias)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNombre)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(iconoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCategorias)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(butAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butCancelar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void butCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butCancelarActionPerformed
        try {
            _marca.Delete();
            if(_marca.getId_Imagen() != -1){
                new Imagen(_marca.getId_Imagen()).Delete();
            }
        } catch (Exception ex) {
            Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_butCancelarActionPerformed

    private void butAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAceptarActionPerformed
        try {
            if(_marca != null){
                _marca.setNombre(txtNombre.getText());
                _marca.Update();
            }
            else{
                _marca = Marca.Create(txtNombre.getText(), 1);
                _modMarcas.addMarca(_marca);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
    }//GEN-LAST:event_butAceptarActionPerformed

    private void iconoImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconoImagenMouseClicked
        //Creamos nuestra variable archivo en la cual podremos usar todos los metodos de la clase jFileChooser
        JFileChooser archivo = new JFileChooser();
        //Si deseamos crear filtros para la selecion de archivos
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Formatos de Archivos JPEG(*.JPG;*.JPEG)", "jpg","jpeg");
        //Si deseas que se muestre primero los filtros usa la linea q esta abajo de esta.
        //archivo.setFileFilter(filtro);
        // Agregamos el Filtro pero cuidado se mostrara despues de todos los archivos
        archivo.addChoosableFileFilter(filtro);
        archivo.setFileFilter(filtro);
        // Colocamos titulo a nuestra ventana de Seleccion
        archivo.setDialogTitle("Abrir Imagen");
        //Si deseamos que muestre una carpeta predetermina usa la siguiente linea
        File ruta = null;
        try {
            ruta = new File(Data.RutaImagenes());
        } catch (IOException ex) {
            Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Le implementamos a nuestro ventana de seleccion
         archivo.setCurrentDirectory(ruta);
         //Abrimos nuestra Ventana de Selccion
        int ventana = archivo.showOpenDialog(null);
        //hacemos comparacion en caso de aprete el boton abrir
        if(ventana == JFileChooser.APPROVE_OPTION)
        {
            //Obtenemos la ruta de nuestra imagen seleccionada
            File file = archivo.getSelectedFile();
            //Lo imprimimos en una caja de texto para ver su ruta
            //txtnomimagen.setText(String.valueOf(file));
            //de cierto modo necesitamos tener la imagen para ello debemos conocer la ruta de dicha imagen
            //Image foto= getToolkit().getImage(String.valueOf(file));
            //Le damos dimension a nuestro label que tendra la imagen
            //foto= foto.getScaledInstance(110, 110, Image.SCALE_DEFAULT);
            //Imprimimos la imagen en el label
            //iconoImagen.setIcon(new ImageIcon(foto));
            /*System.out.println(file.getAbsolutePath());
            try {
                System.out.println(file.getCanonicalPath());
            } catch (IOException ex) {
                Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(file.getName());
            System.out.println(file.getParent());
            System.out.println(file.getPath());
            
            try {
                String s = file.getPath().replace(Data.RutaImagenes(), "");
                s = s.replace(file.getName(), "");
                System.out.println(s);
            } catch (IOException ex) {
                Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image image = new ImageIcon(String.valueOf(file)).getImage();
            ImageIcon iconoEscalado = new ImageIcon (image.getScaledInstance(100,100,Image.SCALE_SMOOTH));
            iconoImagen.setIcon(iconoEscalado);*/
            String sRuta = null;
            try {
                sRuta = file.getPath().replace(Data.RutaImagenes(), "");
            } catch (IOException ex) {
                Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            sRuta = sRuta.replace(file.getName(), "");
            Imagen imagen = null;
            try {
                imagen = Imagen.Create(file.getName(), sRuta);
            } catch (Exception ex) {
                Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(imagen != null){
                _marca.setId_Imagen(imagen.getId());
                try {
                    _marca.Update();
                } catch (Exception ex) {
                    Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    cargarImagen();
                } catch (Exception ex) {
                    Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_iconoImagenMouseClicked

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
            java.util.logging.Logger.getLogger(FrmMarca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMarca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMarca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMarca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*
        java.awt.EventQueue.invokeLater(() -> {
            
            FrmMarca frmMarca = null;
            try {
                frmMarca = new FrmMarca();
            } catch (Exception ex) {
                Logger.getLogger(FrmMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(frmMarca != null){
                frmMarca.setLocationRelativeTo(null);
                frmMarca.setVisible(true);
            }
        });
        */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAceptar;
    private javax.swing.JButton butCancelar;
    private javax.swing.JLabel iconoImagen;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblCategorias;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
