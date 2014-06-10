package policiasLadrones;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Anima extends Thread
{
	String nombre;//variale para guardar animal
	int limite;//hasta donde termina de correr
	JLabel label;//crea mi hilo del label(imagenes)
	int segundos = 0;
	int empezarDesde;
	
	
	 class Contador extends TimerTask {
	        public void run() 
	        {
	            segundos++;
	        }
	    }
	 
	public Anima(String nombre, int limite, JLabel label, int empezarDesde)//constructor que pide nombre
	{
		this.nombre = nombre;
		this.limite = limite;
		this.label = label;
		this.empezarDesde = empezarDesde;
	}
	
public void run()//para runnable aqui suceden las acciones(conienza)
   {
	   int x=empezarDesde;
	   Random ran = new Random();
	   
	   Timer tiempo = new Timer();
	   tiempo.schedule(new Contador(), 0, 1000);//cuenta cada segundo
	   
	   while (x<limite)
	   {
			   if(x>limite)
				   x=limite;
			   
			   System.out.println(nombre+"\t\tavanza en segundo "+segundos);
			   //label.setLocation(x,0);//horizotal, vertical
			   int temporal = x;
			   x+=ran.nextInt(20);//aleatorio 0 20
			   
               for(int corre=temporal; corre<x; corre++)
			   {
            	   label.setLocation(corre,0);//horizotal, vertical
			   }
			
			   try
			   {
				  //pausa hilo
				  Thread.sleep(1000);
			   } catch (InterruptedException e){
				e.printStackTrace();
			   }
	   }
		   System.out.println(nombre+" ha llegado a la meta");
		   JOptionPane.showMessageDialog(null, nombre+" llego a la meta");
	   

	   //JOptionPane.showMessageDialog(null, "GO TO PRISION");
	   //alterna cada hilo con otro ejecutado
	   yield();//maneja prioridad pero ahora dejaremos la misma a todos
   }
}









/*if(segundos == 120 && x<limite && nombre=="El ladron ")
{
	    x=0;
	    JOptionPane.showMessageDialog(null, "SE TERMINO EL TIEMPO");
      System.out.println("Has sido capturado");
	    break;
}
else if(segundos == 120 && x<limite && nombre=="El policia ")
{
	    x=0;
	    this.stop();
	    break;
}*/
	  