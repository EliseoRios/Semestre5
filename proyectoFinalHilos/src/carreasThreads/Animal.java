package carreasThreads;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Animal extends Thread
{
	String nombre;//variale para guardar animal
	int limite;//hasta donde termina de correr
	JLabel label;//crea mi hilo del label(imagenes)
	
	public Animal(String nombre, int limite, JLabel label)//constructor que pide nombre
	{
		this.nombre = nombre;
		this.limite = limite;
		this.label = label;
	}
	
   public void run()//para rinnable aqui suceden las acciones(conienza)
   {
	   for (int x=0; x<limite; x++)
	   {
		   System.out.println(nombre+" avanza");
		   label.setLocation(x,0);//horizotal, vertical
		   
		   try 
		   {
			  //pausa hilo
			  Thread.sleep(10);
		   } catch (InterruptedException e) {
			e.printStackTrace();
		   }
		   
	   }
	   System.out.println(nombre+" ha llegado a la meta");
	   JOptionPane.showMessageDialog(null, nombre+" ha llegado a la meta");
	   
	   
	   
	   //alterna cada hilo con otro ejecutado
	   yield();//maneja prioridad pero ahora dejaremos la misma a todos
   }
}
