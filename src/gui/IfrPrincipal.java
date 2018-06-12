/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Data.Clases.Configuracion;
import Data.Clases.Imagen;
import Data.Clases.PedidoPendiente;
import Data.Modelos.ModPedidos;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import util.Servidor;

/**
 *
 * @author victor
 */
public class IfrPrincipal extends javax.swing.JFrame {

    private final Servidor _servidor;
    private String _sIP = null;
    private Thread _hilo;
    private ModPedidos _modPedidos;
    private int _numPedidos;
    
    /**
     * Creates new form IfrPrincipal
     */
    public IfrPrincipal() {
        initComponents();
        getContentPane().setBackground(Color.white);
        
        this.setLocationRelativeTo(null);
        obtenerIP();
        try {
            lblNombreTienda.setText(Configuracion.Select("Nombre_tienda", null).get(0).getValor());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, 
                "Error al obtener el nombre de la tienda.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            //System.out.println("Error al obtener el nombre de la tienda. "+ex.toString());
        }
        
        _numPedidos = 1;
        _modPedidos = new ModPedidos();
        listPedidosPendientes.setModel(_modPedidos);
        
        _servidor = new Servidor();
        butEstadoServidor.doClick();
        
        
        listPedidosPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                if(e.getClickCount()==2){
                   ver_pedido();
                 }
           }
        });
        
        /*
        JOptionPane.showMessageDialog(null, 
                ClassLoader.getSystemResource("img/ES.PNG")+"\n"+ClassLoader.getSystemResource("db.properties"), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        */
        
        Image image = new ImageIcon(ClassLoader.getSystemResource("img/ES.PNG")).getImage();
        ImageIcon iconoEscalado = new ImageIcon (image.getScaledInstance(100,-1,Image.SCALE_SMOOTH));
        icono_logo.setIcon(iconoEscalado);
        
        String sPedido = "1:1:3\n"
                + "3:3:3\n"
                + "FinTicket";
        try {
            _modPedidos.addPedido(new PedidoPendiente(sPedido, _numPedidos));
            _numPedidos++;
        } catch (Exception ex) {
            System.out.println("Error al crear el pedido. "+ex.toString());
        }  
    }
    
    @Override
     public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("img/boton_48.png"));
        return retValue;
    }
    
    private void ver_pedido()
    {
        int index = listPedidosPendientes.getSelectedIndex();
        
        if(index != -1)
        {
            PedidoPendiente pedido = _modPedidos.getPedido(index);
        
            if(!pedido.getAbierto())
            {
                java.awt.EventQueue.invokeLater(() -> {
                    try {
                        Frame ifrPedido = new IfrPedido(pedido, null);
                        ifrPedido.setLocationRelativeTo(this);
                        ifrPedido.setVisible(true);
                        pedido.setFrame(ifrPedido);
                        pedido.setAbierto(true);
                    } catch (Exception ex) {
                        Logger.getLogger(IfrPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });  
            }
            else
            {
                pedido.getFrame().setState(NORMAL);
                pedido.getFrame().toFront();
            }
        }
    }
    
    private void obtenerIP()
    {
        try{
            _sIP = InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException ex){
            JOptionPane.showMessageDialog(null, 
                "Error al obtener la ip.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
        
        if(!_sIP.equals("127.0.0.1"))
            lblIP.setText("Tu dirección IP es: "+_sIP);
        else 
            lblIP.setText("No estás conectado.");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombreTienda = new javax.swing.JLabel();
        lblIP = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listPedidosPendientes = new javax.swing.JList<>();
        butEstadoServidor = new javax.swing.JToggleButton();
        lblEstadoServidor = new javax.swing.JLabel();
        butVerPedido = new javax.swing.JButton();
        butEliminar = new javax.swing.JButton();
        icono_logo = new javax.swing.JLabel();
        lblException = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuGestionar = new javax.swing.JMenu();
        MenuItemBaseDatos = new javax.swing.JMenuItem();
        MenuItemExistencias = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MenuItemColores = new javax.swing.JMenuItem();
        MenuItemTallas = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        MenuItemConfig = new javax.swing.JMenuItem();
        MenuVer = new javax.swing.JMenu();
        MenuItemPedidos = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EasyShop");
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setMinimumSize(new java.awt.Dimension(433, 490));

        lblNombreTienda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNombreTienda.setText("Nombre tienda");

        lblIP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIP.setText("Dirección IP");

        listPedidosPendientes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        listPedidosPendientes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listPedidosPendientes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listPedidosPendientes.setToolTipText("Lista de pedidos");
        listPedidosPendientes.setSelectionBackground(new java.awt.Color(0, 0, 0));
        jScrollPane1.setViewportView(listPedidosPendientes);

        butEstadoServidor.setBackground(new java.awt.Color(153, 153, 153));
        butEstadoServidor.setForeground(new java.awt.Color(255, 255, 255));
        butEstadoServidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/power_90.png"))); // NOI18N
        butEstadoServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEstadoServidorActionPerformed(evt);
            }
        });

        lblEstadoServidor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEstadoServidor.setText("Apagado");

        butVerPedido.setBackground(new java.awt.Color(0, 0, 0));
        butVerPedido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butVerPedido.setForeground(new java.awt.Color(255, 255, 255));
        butVerPedido.setText("Ver pedido");
        butVerPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butVerPedidoActionPerformed(evt);
            }
        });

        butEliminar.setBackground(java.awt.Color.red);
        butEliminar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        butEliminar.setForeground(new java.awt.Color(255, 255, 255));
        butEliminar.setText("Eliminar");

        icono_logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        icono_logo.setMaximumSize(new java.awt.Dimension(100, 100));

        lblException.setText(" ");

        MenuGestionar.setText("Gestionar");
        MenuGestionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuGestionarActionPerformed(evt);
            }
        });

        MenuItemBaseDatos.setText("Base de datos");
        MenuItemBaseDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemBaseDatosActionPerformed(evt);
            }
        });
        MenuGestionar.add(MenuItemBaseDatos);

        MenuItemExistencias.setText("Existencias");
        MenuItemExistencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemExistenciasActionPerformed(evt);
            }
        });
        MenuGestionar.add(MenuItemExistencias);
        MenuGestionar.add(jSeparator1);

        MenuItemColores.setText("Colores");
        MenuItemColores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemColoresActionPerformed(evt);
            }
        });
        MenuGestionar.add(MenuItemColores);

        MenuItemTallas.setText("Tallas");
        MenuItemTallas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemTallasActionPerformed(evt);
            }
        });
        MenuGestionar.add(MenuItemTallas);
        MenuGestionar.add(jSeparator2);

        MenuItemConfig.setText("Configuración");
        MenuItemConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemConfigActionPerformed(evt);
            }
        });
        MenuGestionar.add(MenuItemConfig);

        jMenuBar1.add(MenuGestionar);

        MenuVer.setText("Ver");

        MenuItemPedidos.setText("Pedidos realizados");
        MenuItemPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemPedidosActionPerformed(evt);
            }
        });
        MenuVer.add(MenuItemPedidos);

        jMenuBar1.add(MenuVer);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(lblNombreTienda)
                    .addComponent(lblIP))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(butVerPedido, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(butEliminar, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblEstadoServidor)
                                .addGap(29, 29, 29))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(butEstadoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(icono_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addComponent(jSeparator3)
            .addComponent(lblException, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(butEstadoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblEstadoServidor)
                        .addGap(68, 68, 68)
                        .addComponent(butVerPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(butEliminar)
                        .addGap(41, 41, 41)
                        .addComponent(icono_logo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombreTienda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(lblException))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuGestionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuGestionarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuGestionarActionPerformed

    private void MenuItemBaseDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemBaseDatosActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame ifrMarca = null;
            try {
                ifrMarca = new IfrMarca();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                "Error al leer la base de datos.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
            if(ifrMarca != null){
                ifrMarca.setLocationRelativeTo(this);
                ifrMarca.setVisible(true);
            }
            //this.dispose();
        });
    }//GEN-LAST:event_MenuItemBaseDatosActionPerformed

    private void MenuItemConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemConfigActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame frmConfig = null;
            try {
                frmConfig = new FrmConfig(lblNombreTienda);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                "Error al leer la base de datos.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
            if(frmConfig != null){
                frmConfig.setLocationRelativeTo(this);
                frmConfig.setVisible(true);
            }
        });
    }//GEN-LAST:event_MenuItemConfigActionPerformed

    private void butEstadoServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butEstadoServidorActionPerformed
        obtenerIP();
        
        if(butEstadoServidor.isSelected())
        {
            lblException.setText(" ");
            _servidor.encenderServidor();
            lblEstadoServidor.setText("Encendido");

            _hilo = new Thread(){
            @Override
            public void run()
                {
                    while(_servidor.encendido())
                    {
                        try {
                            _servidor.conectar();
                            String sMensaje = _servidor.obtenerMensaje();

                            if(!isInterrupted()) {
                                //System.out.println(sMensaje);
                                int iId_Marca;
                                int iId_Categoria;
                                int iId_Articulo;

                                switch(sMensaje){
                                    case "conectar": 
                                        _servidor.enviarMensaje("conectado");
                                        break;
                                    case "marcas":
                                        _servidor.enviarMarcas();
                                        break;
                                    case "marca":
                                        iId_Marca = Integer.parseInt(_servidor.obtenerMensaje());
                                        _servidor.enviarMarca(iId_Marca);    
                                        break;
                                    case "categorias":
                                        iId_Marca = Integer.parseInt(_servidor.obtenerMensaje());
                                        _servidor.enviarCategorias(iId_Marca);
                                        break;
                                    case "articulos":
                                        iId_Categoria = Integer.parseInt(_servidor.obtenerMensaje());
                                        _servidor.enviarArticulos(iId_Categoria);
                                        break;
                                    case "articulo":
                                        iId_Articulo = Integer.parseInt(_servidor.obtenerMensaje());
                                        _servidor.enviarUnArticulo(iId_Articulo);
                                        break;
                                    case "colores":
                                        iId_Articulo = Integer.parseInt(_servidor.obtenerMensaje());
                                        _servidor.enviarColoresArticulo(iId_Articulo);
                                        break;
                                    case "tallas":
                                        iId_Articulo = Integer.parseInt(_servidor.obtenerMensaje());
                                        _servidor.enviarTallasArticulo(iId_Articulo);
                                        break;
                                    case "combinaciones":
                                        iId_Articulo = Integer.parseInt(_servidor.obtenerMensaje());
                                        _servidor.enviarCombinacionesArticulo(iId_Articulo);
                                        break;
                                    case "pedido":
                                        sMensaje = _servidor.obtenerPedido();
                                        _modPedidos.addPedido(new PedidoPendiente(sMensaje, _numPedidos));
                                        _servidor.enviarMensaje(String.format("%d",_numPedidos));
                                        _numPedidos++;
                                        break;
                                }
                            } 
                        }catch(Exception ex){ 
                            lblException.setText(" "+ex.toString());
                        }
                    }
                }
            };

            _hilo.start();
        }
        else
        {
            try{
                _servidor.apagarServidor();
                _hilo.interrupt();
                lblEstadoServidor.setText("Apagado");
            }catch(Exception ex){ 
                lblException.setText(" "+ex.toString());
            }
        }
    }//GEN-LAST:event_butEstadoServidorActionPerformed

    private void MenuItemColoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemColoresActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame frmColores = new FrmColores();
            frmColores.setLocationRelativeTo(this);
            frmColores.setVisible(true);

        });
    }//GEN-LAST:event_MenuItemColoresActionPerformed

    private void MenuItemTallasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemTallasActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame frmTallas = new FrmTallas();
            frmTallas.setLocationRelativeTo(this);
            frmTallas.setVisible(true);
        });
    }//GEN-LAST:event_MenuItemTallasActionPerformed

    private void butVerPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butVerPedidoActionPerformed
        ver_pedido();
    }//GEN-LAST:event_butVerPedidoActionPerformed

    private void MenuItemExistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemExistenciasActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame frmExistencias = null;
            try {
                frmExistencias = new FrmExistencias(null);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                "Error al leer la base de datos.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
            if(frmExistencias != null){
                frmExistencias.setLocationRelativeTo(this);
                frmExistencias.setVisible(true);
            }
            //this.dispose();
        });
    }//GEN-LAST:event_MenuItemExistenciasActionPerformed

    private void MenuItemPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemPedidosActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame ifrPedidos = null;
            try {
                ifrPedidos = new IfrPedidosRealizados();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, 
                "Error al leer la base de datos.\n"+ex.toString(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            }
            if(ifrPedidos != null){
                ifrPedidos.setLocationRelativeTo(this);
                ifrPedidos.setVisible(true);
            }
            //this.dispose();
        });
    }//GEN-LAST:event_MenuItemPedidosActionPerformed

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
            java.util.logging.Logger.getLogger(IfrPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IfrPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IfrPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IfrPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IfrPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuGestionar;
    private javax.swing.JMenuItem MenuItemBaseDatos;
    private javax.swing.JMenuItem MenuItemColores;
    private javax.swing.JMenuItem MenuItemConfig;
    private javax.swing.JMenuItem MenuItemExistencias;
    private javax.swing.JMenuItem MenuItemPedidos;
    private javax.swing.JMenuItem MenuItemTallas;
    private javax.swing.JMenu MenuVer;
    private javax.swing.JButton butEliminar;
    private javax.swing.JToggleButton butEstadoServidor;
    private javax.swing.JButton butVerPedido;
    private javax.swing.JLabel icono_logo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lblEstadoServidor;
    private javax.swing.JLabel lblException;
    private javax.swing.JLabel lblIP;
    private javax.swing.JLabel lblNombreTienda;
    private javax.swing.JList<String> listPedidosPendientes;
    // End of variables declaration//GEN-END:variables
}
