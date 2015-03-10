
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
        
        //nº de frame que le toca pintar
     private int contador = 0;
        
        //dirección en la que se mueve
     int dir = 0;
        
        //indica si está parado o se está moviendo
     boolean parado = true;
        
        //coordenadas de Link
     int x = 0, y = 0;
     
     public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
     
     public Link(){
         try {
            link = ImageIO.read((getClass().getResource("/Imagenes/link.png")));
        } catch (IOException ex) {
            Logger.getLogger(VentanaAnimacion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void dibuja (Graphics2D g2){
         int fila = 0;
         //si está parado hace lo que hacía amtes
         if (!parado){
            switch (dir){
                case 1: fila = 5; mueve (-6,0); break; //izquierda
                case 2: fila = 7; mueve (6, 0); break; //derecha
                case 3: fila = 6; mueve (0,-6); break; //arriba
                case 4: fila = 4; mueve (0, 6); break; //abajo
            }
            contador++;
         }
         else{ //en este caso es que está parado, y tengo que dejar
             //el contador a 0, y mostrar la fila correspondiente
            switch (dir){
                case 1: fila = 1; break; //izquierda
                case 2: fila = 3; break; //derecha
                case 3: fila = 2; break; //arriba
                case 4: fila = 0; break; //abajo
            }
            contador = 0;
         }
         g2.drawImage(link,
                x,   //posición x dentro del buffer
                y,   //posición y dentro del buffer
                x+ 120/3,   //tamaño en el eje x del frame que quiero pintar
                y+ 130/3,   //tamaño en el eje y del frame que quiero pintar
                contador*120, //posición inicial x dentro del SPRITESHEET
                fila*130, //posición inicial y dentro del SPRITESHEET, fila indica
                //la dirección de link
                contador*120 + 120, //tamaño del tile (ancho)
                fila*130 + 130, //tamaño del tile (alto),fila indica la dirección de
                //link
                null
                );
         
         //else { contador = 0;} //si está parado, reseteo el contador
         if (contador == 9) contador = 0;
     }
    
    private void mueve (int dx, int dy){//dx=despaza en la x, dy=desplaza en la y
        x = x + dx;
        //con este if controlo si ha tocado la pared izquierda
        if (x < 0){x = 0;}
     
        y = y + dy;
        
        //con este if controlo si ha tocado el techo
        if(y < 0){y = 0;}
    }
}
