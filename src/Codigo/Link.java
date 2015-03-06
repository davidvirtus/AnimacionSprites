
package Codigo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;


/**
 *
 * @author Virtus
 */
public class Link {
     Image link;
     private int contador = 0;
     int dir = 0;
     boolean parado = true;
     
     public Link(){
         try {
            link = ImageIO.read((getClass().getResource("/Imagenes/link.png")));
        } catch (IOException ex) {
            Logger.getLogger(VentanaAnimacion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void dibuja (Graphics2D g2){
         int fila = 0;
         switch (dir){
             case 0: fila = 0; break; //está parado
             case 1: fila = 5; break; //izquierda
             case 2: fila = 7; break; //derecha
             case 3: fila = 6; break; //arriba
             case 4: fila = 4; break; //abajo
         }
         g2.drawImage(link,
                100,   //posición x dentro del buffer
                100,   //posición y dentro del buffer
                2*120,   //tamaño en el eje x del frame que quiero pintar
                2*130,   //tamaño en el eje y del frame que quiero pintar
                contador*120, //posición inicial x dentro del SPRITESHEET
                fila*130, //posición inicial y dentro del SPRITESHEET, fila indica
                //la dirección de link
                contador*120 + 120, //tamaño del tile (ancho)
                fila*130 + 130, //tamaño del tile (alto),fila indica la dirección de
                //link
                null
                );
         
         if (!parado){ //si no está parado incremento el contador
             contador++;
         }
         //else { contador = 0;} //si está parado, reseteo el contador
         if (contador == 10) contador = 0;
     }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
}
