package gui;

import Data.Clases.Articulo;
import Data.Clases.Categoria;
import Data.Data;
import Data.Clases.Imagen;
import Data.Modelos.ModArticulos;
import Data.Renders.ListaRender;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ventana desde la que se puede crear o modificar una Categoría. Desde ella
 * podemos asignarle un nombre, una imagen y una lista de Artículos.
 *
 * @author Víctor Martín Torres - 12/06/2018
 * @see Categoria
 * @see ModArticulos
 */
public class FrmCategoria extends javax.swing.JFrame 
{
    private Categoria _categoria = null;
    private ModArticulos _modArticulos = null;
    
    /**
     * La variable _bModificar es un booleano que estará a false cuando se esté
     * creando un elemento nuevo, y estará a true cuando se esté modificando
     * un elemento existente.
     */
    private boolean _bModificar;
    
    /**
     * La variable _bCambios es un booleano que nos avisará si se ha realizado
     * algún cambio y este no ha sido guardado.
     */
    private boolean _bCambios;
    
    /**
     * Crea un nuevo formulario de Categoria.
     * @param iId_Categoria Si se va a modificar una categoría existente este 
     * parámetro es su Id en la base de datos. Si se va a crear una categoría
     * nueva, este parámetro debe ser null.
     * @param iId_Marca El Id de la marca a la que pertenece la categoría.
     * @throws java.sql.SQLException Error al buscar o crear la categoría en la 
     * base de datos.
     */
    public FrmCategoria(Integer iId_Categoria, Integer iId_Marca) 
            throws SQLException      
    {
        initComponents();
        txtNombre.requestFocus();
        _bCambios = false;
        
        /**
         * Si la categoría existe rellenar todos los campos del formulario con 
         * sus valores, en caso contrario crear una categoría nueva con valores
         * vacíos en la base de datos.
         */
        if(iId_Categoria != null)
        {
            _bModificar = true;
            _categoria = new Categoria(iId_Categoria);
            txtNombre.setText(_categoria.getNombre());
        }
        else
        {
            _bModificar = false;
            _categoria = Categoria.Create("", -1, iId_Marca);
        }
        
        //Cargar la imagen de la categoría
        cargarImagen();
        
        /**
         * LISTA DE ARTICULOS:
         * preparar la lista de artículos y rellenar con los artículos que
         * ya estén asociados a la categoría.
         */
        _modArticulos = new ModArticulos(_categoria.getId(),null);
        lArticulos.setModel(_modArticulos);
        lArticulos.setCellRenderer(new ListaRender());
        
        //cuando se hace doble click en un artículo abre el formulario de articulo
        lArticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()==2)
                {
                    comprobar_cambios();
                    modificarArticulo();
                }
           }
        });
        
        //Personalizar comportamiento del botón de cerrar
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                if(es_posible_salir())
                    FrmCategoria.this.dispose();
            }
        });
    }
    
    /**
     * Personalizar el icono de la ventana
     * @return Devuelve el icono personalizado.
     */
    @Override
    public Image getIconImage() 
    {
       return Toolkit.getDefaultToolkit()
               .getImage(ClassLoader.getSystemResource("img/boton_48.png"));
    }
    
    //MÉTODOS PRIVADOS//////////////////////////////////////////////////////////
    
    //subir imagen
    private void subir_imagen()
    {
        JFileChooser ventanaElegirImagen = new JFileChooser();
        String rutaImagenes = Data.getRutaImagenes();
        
        if(rutaImagenes != null) ventanaElegirImagen.setCurrentDirectory(
                new File(rutaImagenes));
        JLabel img = new JLabel();
        img.setPreferredSize(new Dimension(175,175));
        ventanaElegirImagen.setAccessory(img);

        FileNameExtensionFilter filtro = new FileNameExtensionFilter(
                "Formatos de Archivos JPEG(*.JPG;*.JPEG) y PNG", "jpg",
                "jpeg", "png");
        ventanaElegirImagen.addChoosableFileFilter(filtro);
        ventanaElegirImagen.setFileFilter(filtro);
        ventanaElegirImagen.setDialogTitle("Abrir Imagen");
        
        // Add property change listener
        ventanaElegirImagen.addPropertyChangeListener(
                (final PropertyChangeEvent pe) -> {
            // Create SwingWorker for smooth experience
            SwingWorker<Image,Void> worker=new SwingWorker<Image,Void>(){
                
                // The image processing method
                @Override
                protected Image doInBackground()
                {
                    // If selected file changes..
                    if(pe.getPropertyName().equals(
                            JFileChooser.SELECTED_FILE_CHANGED_PROPERTY))
                    {
                        // Get selected file
                        File f=ventanaElegirImagen.getSelectedFile();
                        
                        try
                        {
                            img.setText("");
                            // Create FileInputStream for file
                            FileInputStream fin=new FileInputStream(f);
                            
                            // Read image from fin
                            BufferedImage bim=ImageIO.read(fin);
                            
                            // Return the scaled version of image
                            return bim.getScaledInstance(-1,170,
                                    BufferedImage.SCALE_FAST);
                            
                        }catch(IOException e){
                            // If there is a problem reading image,
                            // it might not be a valid image or unable
                            // to read
                            img.setText(" Not valid image/Unable to read");
                        }
                    }
                    return null;
                }
                
                @Override
                protected void done()
                {
                    try
                    {
                        // Get the image
                        Image i=get(1L,TimeUnit.NANOSECONDS);
                       
                        // If i is null, go back!
                        if(i==null) return;
                       
                        // Set icon otherwise
                        img.setIcon(new ImageIcon(i));
                    }catch(InterruptedException | ExecutionException | 
                            TimeoutException e){
                        // Print error occured
                        img.setText(" Error occured.");
                    }
                }
            };
            
            // Start worker thread
            worker.execute();
        } // When any JFileChooser property changes, this handler
        // is executed
        );

        int ventana = ventanaElegirImagen.showOpenDialog(this);
        if(ventana == JFileChooser.APPROVE_OPTION)
        {
            File file = ventanaElegirImagen.getSelectedFile();
            Imagen imagen = null;
            try {
                imagen = Imagen.Create(file, rutaImagenes);
            } catch (IOException | SQLException ex) {
                JOptionPane.showMessageDialog(null, 
                "Error al subir imagen.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
            if(imagen != null)
            {
                try{
                    if(_categoria.getId_Imagen() != -1)
                    {
                        int id_imagen = _categoria.getId_Imagen();
                        _categoria.setId_Imagen(imagen.getId());
                        _categoria.Update();
                        new Imagen(id_imagen).Delete();
                    }
                    else
                        _categoria.setId_Imagen(imagen.getId());
                                   
                    cargarImagen();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, 
                    "Error al actualizar la imagen.\n"+ex.toString(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        _bCambios = true;
    }
    
    //Comprobar si existen cambios sin guardar
    private void comprobar_cambios()
    {
        if(_bCambios)
        {
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
    
    //guardar cambios
    private void guardar()
    {
        try {
            _categoria.setNombre(txtNombre.getText());
            _categoria.Update();
            
            _bModificar = true;
            _bCambios = false;

            JOptionPane.showMessageDialog(this, 
            "Los cambios se han guardado correctamente.", 
            "Mensaje del sistema", 
            JOptionPane.PLAIN_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al guardar.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Modificar artículo que ya existe
    private void modificarArticulo()
    {
        int iIndex = lArticulos.getSelectedIndex();
        Articulo articulo = (Articulo)_modArticulos.getElementAt(iIndex);

        java.awt.EventQueue.invokeLater(() -> {
            Frame frmArticulo = null;
            try {
                frmArticulo = new FrmArticulo(articulo.getId(),
                        _categoria.getId());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, 
                "Error al buscar artículo.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
            if(frmArticulo != null){
                frmArticulo.setTitle("Modificar artículo");
                frmArticulo.setLocationRelativeTo(FrmCategoria.this);
                frmArticulo.setVisible(true);
            }
            this.dispose();
        });
    }
    
    //Cargar imagen de la categoría
    private void cargarImagen() throws SQLException 
    {
        if(_categoria.getId_Imagen() != -1)
        {
            iconoImagen.setText("");
            Image image = new ImageIcon(new Imagen(_categoria.getId_Imagen())
                    .getRuta()).getImage();
            ImageIcon iconoEscalado = new ImageIcon (image
                    .getScaledInstance(-1,100,Image.SCALE_SMOOTH));
            iconoImagen.setIcon(iconoEscalado);
        }
        else{
            iconoImagen.setText("<html><body>Elige una <br> imagen</body></html>");
        }
    }
    
    //Comprobar si es posible cerrar la ventana
    private boolean es_posible_salir()
    {
        comprobar_cambios();
        if(!_bModificar)
        {
            try {
                if(!_bModificar)
                    _categoria.Delete();   
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                "Error al eliminar categoría.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
            return true;
        }
        else{
            try {
                if(new Categoria(_categoria.getId()).getId_Imagen() == -1)
                {
                    JOptionPane.showMessageDialog(null,
                        "¡ATENCIÓN! Se debe asignar una imagen a la categoría.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
                    return false;
                }
                else
                    return true;
            } catch (HeadlessException | SQLException ex) {
                JOptionPane.showMessageDialog(this, 
                "Error al salir.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }
    ////////////////////////////////////////////////////////////////////////////

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
        butSubir = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        butAtras = new javax.swing.JButton();
        butGuardar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lblArticulos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lArticulos = new javax.swing.JList<>();
        butAgregarArt = new javax.swing.JButton();
        butEliminarArt = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Agregar categoría");
        setIconImage(getIconImage());
        setResizable(false);

        lblNombre.setText("Nombre");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
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

        butSubir.setBackground(new java.awt.Color(0, 0, 0));
        butSubir.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butSubir.setForeground(new java.awt.Color(255, 255, 255));
        butSubir.setText("Subir imagen");
        butSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSubirActionPerformed(evt);
            }
        });

        butAtras.setBackground(java.awt.Color.red);
        butAtras.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butAtras.setForeground(new java.awt.Color(255, 255, 255));
        butAtras.setText("Atrás");
        butAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAtrasActionPerformed(evt);
            }
        });

        butGuardar.setBackground(java.awt.Color.green);
        butGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butGuardar.setText("Guardar cambios");
        butGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butGuardarActionPerformed(evt);
            }
        });

        lblArticulos.setText("Artículos");

        lArticulos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lArticulos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        lArticulos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lArticulos);

        butAgregarArt.setBackground(new java.awt.Color(0, 0, 0));
        butAgregarArt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butAgregarArt.setForeground(new java.awt.Color(255, 255, 255));
        butAgregarArt.setText("Agregar Artículo");
        butAgregarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butAgregarArtActionPerformed(evt);
            }
        });

        butEliminarArt.setBackground(java.awt.Color.red);
        butEliminarArt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butEliminarArt.setForeground(new java.awt.Color(255, 255, 255));
        butEliminarArt.setText("Eliminar Artículo");
        butEliminarArt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEliminarArtActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("CATEGORÍA");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addGap(0, 320, Short.MAX_VALUE))
                            .addComponent(txtNombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iconoImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblArticulos)
                            .addComponent(jLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(butGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(butAtras))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butAgregarArt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(butEliminarArt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                    .addComponent(butAtras)
                                    .addComponent(butGuardar)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(butAgregarArt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(butEliminarArt))))
                    .addComponent(butSubir))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void iconoImagenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iconoImagenMouseClicked
        subir_imagen();
    }//GEN-LAST:event_iconoImagenMouseClicked

    private void butSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSubirActionPerformed
        subir_imagen();
    }//GEN-LAST:event_butSubirActionPerformed

    private void butAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAtrasActionPerformed
        if(es_posible_salir())
        {
            java.awt.EventQueue.invokeLater(() -> {
                Frame frmMarca = null;
                try {
                    frmMarca = new FrmMarca(_categoria.getId_Marca());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, 
                    "Error al leer marcas.\n"+ex.toString(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                }
                if(frmMarca != null){
                    frmMarca.setTitle("Modificar marca");
                    frmMarca.setLocationRelativeTo(FrmCategoria.this);
                    frmMarca.setVisible(true);
                }
            });
            this.dispose();
        }  
    }//GEN-LAST:event_butAtrasActionPerformed

    private void butGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butGuardarActionPerformed
        guardar();
    }//GEN-LAST:event_butGuardarActionPerformed

    private void butAgregarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butAgregarArtActionPerformed
        comprobar_cambios();
        try {
            if(new Categoria(_categoria.getId()).getId_Imagen() == -1)
            {
                JOptionPane.showMessageDialog(this,
                        "¡ATENCIÓN! Se debe asignar una imagen a la categoría.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE);
            }
            else{
                java.awt.EventQueue.invokeLater(() -> {
                    Frame frmArticulo = null;
                    try {
                        frmArticulo = new FrmArticulo(null, _categoria.getId());
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, 
                        "Error al buscar artículo.\n"+ex.toString(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    }
                    if(frmArticulo != null){
                        frmArticulo.setLocationRelativeTo(FrmCategoria.this);
                        frmArticulo.setVisible(true);
                    }
                    this.dispose();
                });
            }
        } catch (HeadlessException | SQLException ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al salir.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_butAgregarArtActionPerformed

    private void butEliminarArtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEliminarArtActionPerformed
        int index = lArticulos.getSelectedIndex();
        
        if(index != -1)
        {
            Object[] options = {"Sí",
                                "No"};
            int n = JOptionPane.showOptionDialog(this,
                "¿Está seguro? Se eliminarán además todos los datos "
                        + "asociados a este artículo."
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
                    JOptionPane.showMessageDialog(this, 
                    "Error al eliminar artículo.\n"+ex.toString(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                }
                _bCambios = true;
            }
        }    
    }//GEN-LAST:event_butEliminarArtActionPerformed

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        _bCambios = true;
    }//GEN-LAST:event_txtNombreKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton butAgregarArt;
    private javax.swing.JButton butAtras;
    private javax.swing.JButton butEliminarArt;
    private javax.swing.JButton butGuardar;
    private javax.swing.JButton butSubir;
    private javax.swing.JLabel iconoImagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JList<String> lArticulos;
    private javax.swing.JLabel lblArticulos;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
