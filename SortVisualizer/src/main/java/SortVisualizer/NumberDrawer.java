package SortVisualizer;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;


    public class NumberDrawer{
         int CT = 4; // CT = Constante de Tamaño(números)
        Canvas rectangul;
        GraphicsContext gc;
        int coordenadaX = 10;
        int coordenadaY = 20;
        
        
     public NumberDrawer(Canvas reciboRetangul){
         
         rectangul = reciboRetangul;
         gc = reciboRetangul.getGraphicsContext2D();
   
    }
    public void drawNumber(int cons) {
        
        

        System.out.println(cons);
      if(cons > 9){ // Este fragmento de código tiene dos switch´s dentro de un switch mayor para dibujar primer y segundo digito
        int digito1 = cons / 10; // primer digito
        int digito2 = cons % 10; // segundo digito

        // 

        
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
                               dibujarCero(gc, coordenadaX + 50, coordenadaY);
                             break;
                            case 1:
                               dibujarUno(gc, coordenadaX + 50, coordenadaY);
                             break;
                            case 2:
                               dibujarDos(gc, coordenadaX + 50, coordenadaY);
                             break;
                            case 3:
                               dibujarTres(gc, coordenadaX + 50, coordenadaY);
                             break;
                            case 4:
                               dibujarCuatro(gc, coordenadaX + 50, coordenadaY);
                             break;
                            case 5:
                               dibujarCinco(gc, coordenadaX + 50, coordenadaY);
                             break;
                            case 6:
                               dibujarSeis(gc,coordenadaX + 50, coordenadaY);
                             break; 
                            case 7:
                               dibujarSiete(gc, coordenadaX + 50, coordenadaY); 
                             break;
                            case 8:
                               dibujarOcho(gc,coordenadaX + 50, coordenadaY); 
                             break;
                            case 9:
                               dibujarNueve(gc, coordenadaX + 50, coordenadaY);
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
                  dibujarCero(gc, coordenadaX, coordenadaY);
                break;
               case 1:
                  dibujarUno(gc, coordenadaX   , coordenadaY);
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
       }
    }

      public void dibujarNumeros(GraphicsContext gc, int cons) {
        dibujarDos(gc,300,300);
        
    }

    public void dibujarCero(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x/CT, y/CT, 50/CT , 50/CT);
    }

    public void dibujarUno(GraphicsContext gc, double x, double y) {

        gc.strokeLine((x + 25)/CT, y/CT, (x + 25)/CT, (y + 50)/CT);
        gc.strokeLine((x + 25)/CT,y/CT, (x + 10)/CT, (y + 17)/CT );
        gc.strokeLine((x + 10)/CT,(y + 50)/CT, (x + 40)/CT, (y+50)/CT);
    }

    public void dibujarDos(GraphicsContext gc, double x, double y) {
        
        gc.strokeLine(x/CT, y/CT, (x + 35)/CT, y/CT); // Horizontal
        gc.strokeLine((x + 35)/CT, y/CT, (x + 35)/CT, (y + 25)/CT);
        gc.strokeLine((x + 35)/CT, (y + 25)/CT, x/CT, (y + 25)/CT); // Horizontal 2 
        gc.strokeLine(x/CT, (y + 25)/CT, x/CT, (y + 50)/CT);
        gc.strokeLine(x/CT, (y + 50)/CT, (x + 35)/CT, (y + 50)/CT); // Horizontal 3
    }

    public void dibujarTres(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x/CT, y/CT, (x + 50)/CT, y/CT);
        gc.strokeLine((x + 50)/CT, y/CT, (x + 50)/CT, (y + 25)/CT);
        gc.strokeLine((x + 50)/CT, (y + 25)/CT, x/CT, (y + 25)/CT);
        gc.strokeLine((x + 50)/CT, (y + 25)/CT, (x + 50)/CT, (y + 50)/CT);
        gc.strokeLine(x/CT, (y + 50)/CT, (x + 50)/CT, (y + 50)/CT);
    }

    public void dibujarCuatro(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x/CT, y/CT, x/CT, (y + 25)/CT);
        gc.strokeLine(x/CT, (y + 25)/CT, (x + 30)/CT, (y + 25)/CT);
        gc.strokeLine((x + 25)/CT, y/CT, (x + 25)/CT, (y + 50)/CT);
        
    }
    public void dibujarCinco(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x/CT, y/CT, (x + 35)/CT, y/CT);
        gc.strokeLine(x/CT, y/CT, x/CT , (y + 25)/CT);
        gc.strokeLine((x + 35)/CT, (y + 25)/CT, x/CT, (y + 25)/CT);
        gc.strokeLine((x + 35)/CT, (y + 25)/CT, (x + 35)/CT, (y + 50)/CT);
        gc.strokeLine(x/CT, (y + 50)/CT, (x + 35)/CT, (y + 50)/CT);
    }

    public void dibujarSeis(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x/CT, (y + 18)/CT, 35/CT, 35/CT);
        gc.strokeLine((x + 16)/CT  , y/CT , (x+1)/CT, (y + 30)/CT);
    }

    public void dibujarSiete(GraphicsContext gc, double x, double y) {
        gc.strokeLine(x/CT, y/CT, (x + 50)/CT, y/CT);
        gc.strokeLine((x + 50)/CT, y/CT, x/CT, (y + 50)/CT);
    }

    public void dibujarOcho(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x/CT, (y + 20)/CT, 35/CT, 35/CT);
        gc.strokeOval((x + 6)/CT, (y - 0.5)/CT, 20/CT, 20/CT); // circulo más pequeño
    }

    public void dibujarNueve(GraphicsContext gc, double x, double y) {
        gc.strokeOval(x/CT, y/CT, 35/CT, 35/CT);
        gc.strokeLine((x + 35)/CT, (y + 55)/CT, (x + 35)/CT, (y + 20)/CT);
    }
    }