package policiasUltimo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;


//IMPLEMENTACION GRAFICA
@SuppressWarnings("serial")
public class Carreras extends JFrame
{
	JPanel[] paneles;
	JLabel[] labels;
	String[] nombre = {"policia","ladron"};//nombre de label
	JButton boton;
	int ancho = 1200;
	
	int segundos =0;
	int distanciaPolicia=0;
	int distanciaLadron=1;
	
	policia poli;
	ladron ratero;
	int numeroArrestos=0;
	boolean libre=true;
	boolean preso=true;
	
	public Carreras()
	{
		//fondo
		@SuppressWarnings("unused")
		panelImagen imagen = new panelImagen();
		
		//visualizar en framework como tabla (filas,columnas)
		setLayout(new GridLayout(0,1));
		
		paneles = new JPanel[2];
		labels = new JLabel[3];
		
		for(int n=0; n<2; n++)
		{
			paneles[n] = new JPanel();
			add(paneles[n]);//adiere panel a frame
			labels[n] = new JLabel(nombre[n]);//nombre a label con arreglo paralelo
		
			//agrega imagen
			labels[n].setIcon(new ImageIcon(getClass().getResource(nombre[n]+".gif")));
			paneles[n].add(labels[n]);//aderir label a panel
			labels[n].setLocation(0,0);
			
			this.setResizable(false);
			this.setBackground(Color.darkGray);
		}
		boton = new JButton("Comenzar carrera");//boton con texto
		
		boton.addActionListener(new ActionListener()//forma anonima para escuchar
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				numeroArrestos =0; //inicializa
				libre = true;
				distanciaLadron =0;
				distanciaPolicia =1;
				segundos =0;
				
				poli = new policia(labels[0],ancho-85,nombre[0],0);
				ratero = new ladron(labels[1],ancho-85,nombre[1],50);
				
				ratero.start();
				poli.start();
		    }
		});
		
		//aderir boton a frame
		add(boton);
		  
		//Cierra frame con boton cerrar para matar hilo en ejecucion
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLocation(100,250);//posicion
		setSize(ancho,250);//tamaño
		setVisible(true);//visible
	}
	
	public static void main (String[] args)
	{
		@SuppressWarnings("unused")
		Carreras carreara = new Carreras();
	}
	
	public class panelImagen extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Dimension tamanio = getSize();
			
			ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("carretera.gif")).getImage());
			g.drawImage(imagen.getImage(), 0, 0, tamanio.width, tamanio.height, null);
		}
	}
	
	public class policia extends Thread
	{
		int empezardesde =0;
		int limite =0;
		JLabel label;
		String nombre;
		
		public policia(JLabel label1, int miLimite, String nombre, int empezarDesde)
		{
			this.empezardesde = empezarDesde;
			this.limite = miLimite;
			this.label = label1;
			this.nombre = nombre;
		}
		
		@SuppressWarnings("deprecation")
		public void run()
		{
			int x=empezardesde;
			  Random ran = new Random();
			   
			   Timer tiempo = new Timer();
			   tiempo.schedule(new Contador(), 0, 1000);//cuenta cada segundo
			   
			   while (x<limite)
			   {
					   if(x>limite)
						   x=limite;
					   
					   System.out.println(nombre+"\t\tavanza en segundo "+segundos);
					   int temporal = x;
					   x+=ran.nextInt(20);//aleatorio 0 20
					   distanciaPolicia = x;
					   
		               for(int corre=temporal; corre<x; corre++)
					   {
		            	   label.setLocation(corre,0);//horizotal, vertical
					   }
					
		               if(distanciaPolicia>distanciaLadron+10 && numeroArrestos==0  && distanciaLadron<limite)
						{
		            	    numeroArrestos = 1;
		            	    System.out.println("HAS SIDO ARRESTADO");
							//JOptionPane.showMessageDialog(null, "HAS SIDO ARRESTADO");
							ratero.stop();
							poli.stop();
							break;
						}else if(distanciaLadron>=limite && libre==true)
						{
							libre = false;
							System.out.println(nombre+"FELICIDADES ERES LIBRE");
							//JOptionPane.showMessageDialog(null, "FELICIDADES SERAS LIBRE");
							poli.stop();
							ratero.stop();
						}
		               
					   try
					   {
						  //pausa hilo
						  Thread.sleep(50);
					   } catch (InterruptedException e){
						e.printStackTrace();
					   }
			   }
			   this.setPriority(2);
			   yield();
		}
	}
	
	public class ladron extends Thread
	{   
		int empezardesde =0;
		int limite =0;
		JLabel label;
		String nombre;
		
		public ladron(JLabel label2, int miLimite, String nombre, int empezarDesde)
		{
			this.empezardesde = empezarDesde;
			this.limite = miLimite;
			this.label = label2;
			this.nombre = nombre;
		}
		
		@SuppressWarnings("deprecation")
		public void run()
		{
			 int x=empezardesde;
			  Random ran = new Random();
			   
			   Timer tiempo = new Timer();
			   tiempo.schedule(new Contador(), 0, 1000);//cuenta cada segundo
			   
			   while (x<limite)
			   {
					   if(x>limite)
						   x=limite;
					   
					   System.out.println(nombre+"\t\tavanza en segundo "+segundos);
					   int temporal = x;
					   x+=ran.nextInt(20);//aleatorio 0 20
					   distanciaLadron = x;
					   
		               for(int corre=temporal; corre<x; corre++)
					   {
		            	   label.setLocation(corre,0);//horizotal, vertical
					   }
		               
		               if(distanciaPolicia>distanciaLadron+10 && numeroArrestos==0 && distanciaLadron<limite)
						{
		            	    numeroArrestos = 1;
		            	    System.out.println("HAS SIDO ARRESTADO");
							//JOptionPane.showMessageDialog(null, "HAS SIDO ARRESTADO");
							poli.stop();
							ratero.stop();
							break;
						}else if(distanciaLadron>=limite && libre==true)
						{
							libre = false;
							System.out.println("FELICIDADES ERES LIBRE");
							//JOptionPane.showMessageDialog(null, "FELICIDADES ERES LIBRE");
							poli.stop();
							ratero.stop();
						}
					
					   try
					   {
						  //pausa hilo
						  Thread.sleep(50);
					   } catch (InterruptedException e){
						e.printStackTrace();
					   }
			   }
			   this.setPriority(2);
			   yield();
		}
	}
	
	class Contador extends TimerTask 
	{
        public void run() 
        {
            segundos++;
        }
    }
	
}
	/*Hacer un Juego de Patrulla Vs Auto. Si la Patrulla alcanza al Auto 
	 * se pierde Juego. Si No se llega a una META y se gana Juego. Al menos 
	 * que carrera dure dos minutos. Simular juego antes de Ganar o perder 
	 * de al menos 2 minutos.*/
