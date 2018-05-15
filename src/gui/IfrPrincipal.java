/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Data.Clases.Configuracion;
import java.awt.Frame;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Servidor;

/**
 *
 * @author victor
 */
public class IfrPrincipal extends javax.swing.JFrame {

    private final Servidor _servidor;
    private String _sIP = null;
    private Thread _hilo;
    
    /**
     * Creates new form IfrPrincipal
     */
    public IfrPrincipal() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        obtenerIP();
        try {
            lblNombreTienda.setText(Configuracion.Select("Nombre_tienda", null).get(0).getValor());
        } catch (Exception ex) {
            System.out.println("Error al obtener el nombre de la tienda. "+ex.toString());
        }
        
        _servidor = new Servidor();
        _servidor.encenderServidor();
        _hilo = new Thread(){
        @Override
        public void run()
            {
                while(_servidor.encendido())
                {
                    String smensaje = _servidor.obtenerMensaje();

                    //if(!isInterrupted()) lista.addPedido(new Pedido(mensaje));
                }
            }
        };

        _hilo.start();
    }
    
    private void obtenerIP()
    {
        try{
            _sIP = InetAddress.getLocalHost().getHostAddress();
        }catch(UnknownHostException ex){
            System.out.println("Error al obtener la ip. "+ex.toString());
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
        jList1 = new javax.swing.JList<>();
        butEstadoServidor = new javax.swing.JToggleButton();
        lblEstadoServidor = new javax.swing.JLabel();
        butVerPedido = new javax.swing.JButton();
        butEliminar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuGestionar = new javax.swing.JMenu();
        MenuItemBaseDatos = new javax.swing.JMenuItem();
        MenuItemColores = new javax.swing.JMenuItem();
        MenuItemTallas = new javax.swing.JMenuItem();
        MenuItemConfig = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("EasyShop");

        lblNombreTienda.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblNombreTienda.setText("Nombre tienda");

        lblIP.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIP.setText("Dirección IP");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        butEstadoServidor.setBackground(new java.awt.Color(153, 153, 153));
        butEstadoServidor.setForeground(new java.awt.Color(255, 255, 255));
        butEstadoServidor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Data/img/power_90.png"))); // NOI18N
        butEstadoServidor.setSelected(true);
        butEstadoServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butEstadoServidorActionPerformed(evt);
            }
        });

        lblEstadoServidor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEstadoServidor.setText("Encendido");

        butVerPedido.setText("Ver pedido");

        butEliminar.setText("Eliminar");

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

        MenuItemConfig.setText("Configuración");
        MenuItemConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuItemConfigActionPerformed(evt);
            }
        });
        MenuGestionar.add(MenuItemConfig);

        jMenuBar1.add(MenuGestionar);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreTienda)
                    .addComponent(lblIP)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(butEstadoServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblEstadoServidor)
                                .addGap(22, 22, 22))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(butVerPedido, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(butEliminar, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addContainerGap())))
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
                        .addGap(0, 125, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombreTienda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblIP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)))
                .addContainerGap())
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
                System.out.println("Error al leer la base de datos. "+ ex.toString());
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
                System.out.println("Error al leer la base de datos. "+ ex.toString());
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
            _servidor.encenderServidor();
            lblEstadoServidor.setText("Encendido");

            _hilo = new Thread(){
            @Override
            public void run()
                {
                    while(_servidor.encendido())
                    {
                        String smensaje = _servidor.obtenerMensaje();
    
                        //if(!isInterrupted()) lista.addPedido(new Pedido(mensaje));
                    }
                }
            };

            _hilo.start();
        }
        else
        {
            _servidor.apagarServidor();
            _hilo.interrupt();
            lblEstadoServidor.setText("Apagado");
        }
    }//GEN-LAST:event_butEstadoServidorActionPerformed

    private void MenuItemColoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemColoresActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame frmColores = new FrmColores();
            
            if(frmColores != null){
                frmColores.setLocationRelativeTo(this);
                frmColores.setVisible(true);
            }
            //this.dispose();
        });
    }//GEN-LAST:event_MenuItemColoresActionPerformed

    private void MenuItemTallasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuItemTallasActionPerformed
        java.awt.EventQueue.invokeLater(() -> {
            Frame frmTallas = new FrmTallas();
            
            if(frmTallas != null){
                frmTallas.setLocationRelativeTo(this);
                frmTallas.setVisible(true);
            }
            //this.dispose();
        });
    }//GEN-LAST:event_MenuItemTallasActionPerformed

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
    private javax.swing.JMenuItem MenuItemTallas;
    private javax.swing.JButton butEliminar;
    private javax.swing.JToggleButton butEstadoServidor;
    private javax.swing.JButton butVerPedido;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstadoServidor;
    private javax.swing.JLabel lblIP;
    private javax.swing.JLabel lblNombreTienda;
    // End of variables declaration//GEN-END:variables
}
