/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SortVisualizer;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
/**
 *
 * @author david
 */

    public class DibujarNumeros{
     
    
    
        private static String obtenerDigitos(int numero) {
        return Integer.toString(numero);
    }
    
       public int digits2(int numero) {
        // Obtener el número ingresado por el usuario

        // Obtener los dígitos del número y guardarlos en variables distintas
        String digitos = obtenerDigitos(numero);
        int cantidadDigitos = digitos.length();
        int[] digitosSeparados = new int[cantidadDigitos];
        for (int i = 0; i < cantidadDigitos; i++) {
            digitosSeparados[i] = Integer.parseInt(Character.toString(digitos.charAt(i)));
        }
        
        return digitosSeparados[1];
        
    }
        public int digits(int numero) {
        // Obtener el número ingresado por el usuario

        // Obtener los dígitos del número y guardarlos en variables distintas
        String digitos = obtenerDigitos(numero);
        int cantidadDigitos = digitos.length();
        int[] digitosSeparados = new int[cantidadDigitos];
        for (int i = 0; i < cantidadDigitos; i++) {
            digitosSeparados[i] = Integer.parseInt(Character.toString(digitos.charAt(i)));
        }
        
        return digitosSeparados[0];
        
    }
    
    
    public void dibujarNumeros(GraphicsContext gc, int cons) {
        
      if(cons > 9){ // Este fragmento de código tiene dos switch´s dentro de un switch mayor para dibujar primer y segundo digito
        int digito1 = cons / 10; // primer digito
        int digito2 = cons % 10; // segundo digito

        // 
        int coordenadaX = 50;
        int coordenadaY = 100;
        
            for(int DobleD = 1; DobleD < 3; DobleD++){
                switch(DobleD){
                    case 1:
                        switch(digito1){
                        //Primer digito
                             case 0: 
                                dibujarCero(gc, coordenadaX, coordenadaY);
                              break;
                             case 1:
                                dibujarUno(gc, coordenadaX, coordenadaY);
                              break;
                             case 2:
                                dibujarDos(gc, coordenadaX, coordenadaY);
                              break;
                             case 3:
                                dibujarTres(gc, coordenadaX, coordenadaY);
                              break;
                             case 4:
                                dibujarCuatro(gc, coordenadaX, coordenadaY);
                              break;
                             case 5:
                                dibujarCinco(gc, coordenadaX, coordenadaY);
                              break;
                             case 6:
                                dibujarSeis(gc, coordenadaX, coordenadaY);
                              break; 
                             case 7:
                                dibujarSiete(gc, coordenadaX, coordenadaY); 
                              break;
                             case 8:
                                dibujarOcho(gc, coordenadaX, coordenadaY); 
                              break;
                             case 9:
                                dibujarNueve(gc, coordenadaX, coordenadaY);
                              break;
                             default:
                                 System.out.println("fail");
                              break;    
                    }
                     break;
                     
                   case 2:
                        switch(digito2){
                            // Segundo digito
                            case 0: 
                               dibujarCero(gc, coordenadaX+50, coordenadaY);
                             break;
                            case 1:
                               dibujarUno(gc, coordenadaX+50, coordenadaY);
                             break;
                            case 2:
                               dibujarDos(gc, coordenadaX+50, coordenadaY);
                             break;
                            case 3:
                               dibujarTres(gc, coordenadaX+50, coordenadaY);
                             break;
                            case 4:
                               dibujarCuatro(gc, coordenadaX+50, coordenadaY);
                             break;
                            case 5:
                               dibujarCinco(gc, coordenadaX+50, coordenadaY);
                             break;
                            case 6:
                               dibujarSeis(gc,coordenadaX+50, coordenadaY);
                             break; 
                            case 7:
                               dibujarSiete(gc, coordenadaX+50, coordenadaY); 
                             break;
                            case 8:
                               dibujarOcho(gc,coordenadaX+50, coordenadaY); 
                             break;
                            case 9:
                               dibujarNueve(gc, coordenadaX+50, coordenadaY);
                             break;
                            default:
                                System.out.println("fail");
                             break;
                    }   
                     break;
                     
                    default:
                        System.out.println("que pasa por dios mio santo");
                     break;
                } 
            }
       }else{
            // En caso de que sea un número de un solo dígito solo se busca el que corresponda al dígito 
           switch(cons){
               case 0: 
                  dibujarCero(gc, 50, 100);
                break;
               case 1:
                  dibujarUno(gc, 125, 100);
                break;
               case 2:
                  dibujarDos(gc, 200, 100);
                break;
               case 3:
                  dibujarTres(gc, 275, 100);
                break;
               case 4:
                  dibujarCuatro(gc, 350, 100);
                break;
               case 5:
                  dibujarCinco(gc, 425, 100);
                break;
               case 6:
                  dibujarSeis(gc, 500, 100);
                break; 
               case 7:
                  dibujarSiete(gc, 575, 100); 
                break;
               case 8:
                  dibujarOcho(gc, 650, 100); 
                break;
               case 9:
                  dibujarNueve(gc, 725, 100);
                break;
               default:
                   System.out.println("fail");
                break;
       }
       }
    }

    public void dibujarCero(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y, 50, 50);
    }

    public void dibujarUno(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x + 25, y, x + 25, y + 50);
        gc.strokeLine(x + 25,y, x + 10, y + 17 );
        gc.strokeLine(x + 10,y + 50, x + 40, y+50);
    }

    public void dibujarDos(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 50, y);
        gc.strokeLine(x + 50, y, x + 50, y + 25);
        gc.strokeLine(x + 50, y + 25, x, y + 25);
        gc.strokeLine(x, y + 25, x, y + 50);
        gc.strokeLine(x, y + 50, x + 50, y + 50);
    }

    public void dibujarTres(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 50, y);
        gc.strokeLine(x + 50, y, x + 50, y + 25);
        gc.strokeLine(x + 50, y + 25, x, y + 25);
        gc.strokeLine(x + 50, y + 25, x + 50, y + 50);
        gc.strokeLine(x, y + 50, x + 50, y + 50);
    }

    public void dibujarCuatro(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x, y + 25);
        gc.strokeLine(x, y + 25, x + 30, y + 25);
        gc.strokeLine(x + 25, y, x + 25, y + 50);
        
    }
    public void dibujarCinco(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 50, y);
        gc.strokeLine(x , y, x , y + 25);
        gc.strokeLine(x + 50, y + 25, x, y + 25);
        gc.strokeLine(x + 50, y + 25, x + 50, y + 50);
        gc.strokeLine(x, y + 50, x + 50, y + 50);
    }

    public void dibujarSeis(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y + 18, 35, 35);
        gc.strokeLine(x + 16  , y , x+1, y + 30);
    }

    public void dibujarSiete(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x, y, x + 50, y);
        gc.strokeLine(x + 50, y, x, y + 50);
    }

    public void dibujarOcho(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y + 20, 35, 35);
        gc.strokeOval(x + 6., y - 0.5, 20, 20); // circulo más pequeño
    }

    public void dibujarNueve(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x, y, 35, 35);
        gc.strokeLine(x + 35, y + 55, x + 35, y + 20);
    }
    }