
package Codigo;

import com.sun.glass.events.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Virtus
 */

public class VentanaAnimacion extends javax.swing.JFrame {
    
    //buffer para pintar sobre el JPanel
    BufferedImage buffer = null;
    
    //ancho y alto de la pantalla
    int anchoPantalla = 700;
    int altoPantalla = 700;
    
    //Temporizador para crear la animación
    Timer temporizador = new Timer (30, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            bucleJuego();
        }
    });
    
    //creo el objeto link
    Link link = new Link();
    int spawn = 0;
    
    //creo el objeto esqueleto
    ArrayList <Esqueleto>listaEsqueletos = new ArrayList();
    
    //creo el objeto disparo
    ArrayList <Disparo>listaDisparos = new ArrayList();
    
    Random aleatorio = new Random();
    
    //variable para guardar la dirección
    //si vale 0 => parado
    //si vale 1 => izquierda
    //si vale 2 => derecha
    //si vale 3 => arriba
    //si vale 4 => abajo
    int direccion = 0;
    
    /**
     * Creates new form VentanaAnimacion
     */
    public VentanaAnimacion() {
        initComponents();
        this.setSize(anchoPantalla, altoPantalla);
        
        buffer = (BufferedImage) jPanel1.createImage(anchoPantalla, altoPantalla);
        temporizador.start();
    }
    
    private void creaEsqueleto(){
        Esqueleto e = new Esqueleto();
        e.x = aleatorio.nextInt(anchoPantalla);
        e.y = aleatorio.nextInt(altoPantalla);
        e.setDir(0);
        e.parado = false;
        
        //añado el esqueleto de la posición random en la lista de esqueletos
        listaEsqueletos.add(e);
    }
    
    private void dibujaListaEsqueletos(Graphics2D g2){
        for (int i=0; i<listaEsqueletos.size(); i++){
            Esqueleto e = listaEsqueletos.get(i);
            
            //actualizo la posición x del esqueleto en función de la posición x de Link
            if (e.x > link.x){e.x--; e.dir = 1;}
            else if (e.x < link.x){ e.x++; e.dir = 2;}
            
            //actualizo la posición y del esqueleto en función de la posición y de Link
            if (e.y > link.y){e.y--; e.dir = 3;}
            else if (e.y < link.y){e.y++; e.dir = 4;}
            
            e.dibuja(g2);
            
        }
    }
    
    private void dibujaListaDisparos(Graphics2D g2){
        for (int i=0; i<listaDisparos.size(); i++){
            Disparo d = listaDisparos.get(i);
            d.dibuja(g2);
            
        }
    }
    
    private void dispara(){
        Disparo d = new Disparo(link.x+16, link.y+16, link.dir);
        listaDisparos.add(d);
        
    }
    
    private void chequeaColision(){
        //creo un marco para guardar el borde de la imágen del esqueleto
        Rectangle2D.Double rectanguloEsqueleto = new Rectangle2D.Double();
        //creo un marco para guardar el borde de la imágen del disparo
        Rectangle2D.Double rectanguloDisparo = new Rectangle2D.Double();
        
        //ahora leo la lista de disparos
        for (int j=0; j<listaDisparos.size(); j++){
            Disparo d = listaDisparos.get(j);
            //asigno al rectángulo las dimensiones del disparo y su posición
            rectanguloDisparo.setFrame(d.x, d.y, 48/3, 48/3);
            boolean disparoABorrar = false;
            //leo la lista de esqueletos y comparo uno a uno con el disparo
            for (int i=0; i< listaEsqueletos.size(); i++){
                Esqueleto e = listaEsqueletos.get(i);
                rectanguloEsqueleto.setFrame(e.x, e.y, 64/3, 64/3);
                if (rectanguloDisparo.intersects(rectanguloEsqueleto)){
                    listaEsqueletos.remove(i);
                    //no borro aqui el disparo para evitar que se cuelgue 
                    //listaDisparos.remove(j);
                     disparoABorrar = true;
                }
            }
            if (disparoABorrar){
                  listaDisparos.remove(j);
            }
        }
    }
    
    public void bucleJuego(){
        
        //primero apunto al buffer
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        
        //borro la pantalla
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, anchoPantalla, altoPantalla);
        
        ////////////////// dibujo a Link ////////////////////////
        
        //incrementa el contador para saber si hay que añadir esqueletos
        spawn++;
        if(spawn == 100){
            creaEsqueleto();
            spawn = 0;
        }
        chequeaColision();
        
        //primero selecciono la dirección de Link
        link.setDir(direccion);
        
        //dibujo a Link
        link.dibuja(g2);

        
        dibujaListaEsqueletos(g2);
        dibujaListaDisparos(g2);
        
        
      ///////////////////////////////////////////////////////////
      //apunto al JPanel y repinto con el nuevo buffer  
        g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer, 0, 0, null);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 388, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 288, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()){
            case KeyEvent.VK_LEFT  : direccion = 1; break;
            case KeyEvent.VK_RIGHT : direccion = 2; break;
            case KeyEvent.VK_UP    : direccion = 3; break;
            case KeyEvent.VK_DOWN  : direccion = 4; break;
            case KeyEvent.VK_SPACE : dispara(); break;
        }
        link.parado = false;
        
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        link.parado = true;
        //direccion = 0;
    }//GEN-LAST:event_formKeyReleased

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
            java.util.logging.Logger.getLogger(VentanaAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAnimacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

        
    }
