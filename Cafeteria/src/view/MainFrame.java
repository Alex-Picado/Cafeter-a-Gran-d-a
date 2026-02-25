/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import controller.CategoriaController;
import controller.ClienteController;
import controller.PedidoActivoManager;
import controller.ProductoController;
import controller.RecuperacionController;
import java.awt.CardLayout;
import model.CategoriaDAO;
import model.DivisionManager;
import model.Factura;
import model.FacturaDAO;
import model.Pedido;
import model.PedidoActivoSnapshot;
import model.ProductoDAO;
import service.FacturaService;

/**
 *
 * @author eidan
 */
public class MainFrame extends javax.swing.JFrame {

    private CardLayout cardLayout;
    private PanelProductos panelProductos;
    private PanelAgregarProducto panelAgregarProducto;
    private ProductoController productoController;
    private ProductoDAO productoDAO = new ProductoDAO();
    private PanelPadNumerico panelPadNumerico;
    private PanelPedido panelPedido;
    private PanelMesas panelMesas;
    private PanelDividirCuenta panelDividirCuenta;
    private PanelFactura panelFactura;
    private ClienteController clienteController;
    private PedidoActivoManager pedidoActivoManager = new PedidoActivoManager();
    private FacturaService facturaService = new FacturaService();
    private FacturaDAO facturaDAO = new FacturaDAO();
    private String destinoPad;
    private PanelGestionarClientes panelGestionarClientes;
    private PanelSeleccionPago panelSeleccionPago;
    private String metodoPagoSeleccionado;
    private model.Factura facturaEnProceso;
    private PanelMostrarFacturaYaCreada panelMostrarFacturaYaCreada;
    private PanelHistorialVentas panelHistorialVentas;
    private PanelPadMontoAPagar panelPadMontoAPagar;
    private PanelVentasMesasYGlobales panelVentasMesasYGlobales;
    private DivisionManager divisionManager = new DivisionManager();

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();

        cardLayout = (CardLayout) panelContenedor.getLayout();

        panelProductos = new PanelProductos();
        panelAgregarProducto = new PanelAgregarProducto();
        panelPadNumerico = new PanelPadNumerico();
        panelPedido = new PanelPedido();

        panelMesas = new PanelMesas();
        panelDividirCuenta = new PanelDividirCuenta();
        clienteController = new ClienteController();
        panelFactura = new PanelFactura();
        panelGestionarClientes = new PanelGestionarClientes(clienteController);
        panelSeleccionPago = new PanelSeleccionPago();
        panelMostrarFacturaYaCreada = new PanelMostrarFacturaYaCreada();
        panelHistorialVentas = new PanelHistorialVentas();
        panelPadMontoAPagar = new PanelPadMontoAPagar();
        panelVentasMesasYGlobales = new PanelVentasMesasYGlobales();

        PedidoActivoSnapshot snap = new PedidoActivoSnapshot();
        RecuperacionController rec
                = new RecuperacionController(snap, productoDAO);
        pedidoActivoManager.setPedidos(rec.recuperar());

        var mesasOcupadas = pedidoActivoManager.getPedidos()
                .entrySet()
                .stream()
                .filter(e -> !e.getValue().getItems().isEmpty())
                .map(e -> e.getKey())
                .collect(java.util.stream.Collectors.toSet());

        panelMesas.marcarOcupadas(mesasOcupadas);

        CategoriaDAO categoriaDAO = new CategoriaDAO();
        CategoriaController categoriaController = new CategoriaController(categoriaDAO);

        productoController = new ProductoController(productoDAO, panelProductos);

        panelProductos.setController(productoController);
        panelAgregarProducto.setController(productoController);

        panelProductos.setCategorias(categoriaController.obtenerCategoriasActivas());
        panelAgregarProducto.setCategoriaController(categoriaController);

        panelContenedor.add(panelFondoTotal, "inicio");
        panelContenedor.add(new PanelMenu(), "menu");
        panelContenedor.add(panelMesas, "mesas");
        panelContenedor.add(panelProductos, "productos");

        panelContenedor.add(panelGestionarClientes, "clientes");

        panelContenedor.add(panelAgregarProducto, "agregarProducto");
        panelContenedor.add(new PanelReportesYEstadisticas(), "reportes");
        panelContenedor.add(panelPedido, "pedidos");
        panelContenedor.add(panelDividirCuenta, "dividir");
        panelContenedor.add(panelPadNumerico, "padNumerico");
        panelContenedor.add(panelFactura, "factura");
        panelContenedor.add(panelPadMontoAPagar, "padMonto");
        panelContenedor.add(panelSeleccionPago, "seleccionPago");
        panelContenedor.add(panelMostrarFacturaYaCreada, "mostrarFactura");
        panelContenedor.add(panelHistorialVentas, "historial");
        panelContenedor.add(panelVentasMesasYGlobales, "ventasMesas");
        productoController.cargarProductosEnVista();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedor = new javax.swing.JPanel();
        panelFondoTotal = new javax.swing.JPanel();
        labelTitulo = new javax.swing.JLabel();
        btnIniciar = new javax.swing.JButton();
        labelFondoTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelContenedor.setLayout(new java.awt.CardLayout());

        panelFondoTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTitulo.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        labelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        labelTitulo.setText("Cafetería Gran Dia");
        panelFondoTotal.add(labelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, -1, -1));

        btnIniciar.setBackground(new java.awt.Color(255, 255, 255));
        btnIniciar.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        btnIniciar.setForeground(new java.awt.Color(0, 0, 0));
        btnIniciar.setText("Iniciar");
        btnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarActionPerformed(evt);
            }
        });
        panelFondoTotal.add(btnIniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 330, 280, -1));

        labelFondoTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ImgFondoOpaco.jpeg"))); // NOI18N
        panelFondoTotal.add(labelFondoTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        panelContenedor.add(panelFondoTotal, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(panelContenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarActionPerformed
        // TODO add your handling code here:
        mostrar("menu");
    }//GEN-LAST:event_btnIniciarActionPerformed

    public void setMetodoPagoSeleccionado(String metodo) {
        this.metodoPagoSeleccionado = metodo;
    }

    public String getMetodoPagoSeleccionado() {
        return metodoPagoSeleccionado;
    }

    public FacturaDAO getFacturaDAO() {
        return facturaDAO;
    }

    public PanelFactura getPanelFactura() {
        return panelFactura;
    }

    public FacturaService getFacturaService() {
        return facturaService;
    }

    public PanelVentasMesasYGlobales getPanelVentasMesasYGlobales() {
        return panelVentasMesasYGlobales;
    }

    public void mostrar(String nombre) {

        if ("historial".equals(nombre)) {

            var facturas = facturaDAO.obtenerTodas();
            panelHistorialVentas.cargarHistorial(facturas);
        }

        cardLayout.show(panelContenedor, nombre);
    }

    public PanelPadNumerico getPanelPadNumerico() {
        return panelPadNumerico;
    }

    public PanelAgregarProducto getPanelAgregarProducto() {
        return panelAgregarProducto;
    }

    public ProductoController getProductoController() {
        return productoController;
    }

    public PanelPedido getPanelPedido() {
        return panelPedido;
    }

    public PanelMesas getPanelMesas() {
        return panelMesas;
    }

    public PedidoActivoManager getPedidoActivoManager() {
        return pedidoActivoManager;
    }

    public PanelProductos getPanelProductos() {
        return panelProductos;
    }

    public void abrirPadParaFactura() {
        destinoPad = "FACTURA";
        mostrar("padNumerico");
    }

    public String getDestinoPad() {
        return destinoPad;
    }

    public ClienteController getClienteController() {
        return clienteController;
    }

    public PanelGestionarClientes getPanelGestionarClientes() {
        return panelGestionarClientes;
    }

    public void setFacturaEnProceso(model.Factura f) {
        this.facturaEnProceso = f;
    }

    public model.Factura getFacturaEnProceso() {
        return facturaEnProceso;
    }

    public void abrirFacturaDesdeHistorial(Factura factura) {

        panelMostrarFacturaYaCreada.setFactura(factura);
        mostrar("mostrarFactura");
    }

    public void abrirHistorial() {

        var facturas = facturaDAO.obtenerTodas();

        panelHistorialVentas.cargarHistorial(facturas);

        mostrar("historial");
    }

    public PanelHistorialVentas getPanelHistorialVentas() {
        return panelHistorialVentas;
    }

    public PanelDividirCuenta getPanelDividirCuenta() {
        return panelDividirCuenta;
    }

    public DivisionManager getDivisionManager() {
        return divisionManager;

    }

    public void mostrarFacturaDesdeDivision(model.Factura factura) {

        panelFactura.setFactura(factura);

        mostrar("factura");
    }

    public void mostrarPanelFacturaDesdeDivision(model.Pedido pedido,
            String mesa,
            int persona) {

        panelFactura.cargarPedidoDesdeDivision(pedido, mesa, persona);

        mostrar("factura");
    }

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIniciar;
    private javax.swing.JLabel labelFondoTotal;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JPanel panelContenedor;
    private javax.swing.JPanel panelFondoTotal;
    // End of variables declaration//GEN-END:variables
}
