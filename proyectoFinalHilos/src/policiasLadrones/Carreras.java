package policiasLadrones;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	public Carreras()
	{
		//fondo
		@SuppressWarnings("unused")
		frameImagen imagen = new frameImagen();
		
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
		}
		
		//desde aqui fondo
		/*JPanel panelFondo = new JPanel();
		add(panelFondo);
		labels[2] = new JLabel();
		labels[2].setIcon(new ImageIcon(getClass().getResource("carretera.gif")));
		panelFondo.add(labels[2]);
		labels[2].setLocation(0,0);*/
		
		
		
		boton = new JButton("Comenzar carrera");//boton con texto
		
		boton.addActionListener(new ActionListener()//forma anonima para escuchar
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//accion de boton
				Anima policia = new Anima("policia",ancho-80,labels[0],0);
				Anima ladron = new Anima("ladron",ancho-80,labels[1],30);
				
				policia.start();
				ladron.start();
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
	
	public class frameImagen extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			Dimension tamanio = getSize();
			
			ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("carretera.gif")).getImage());
			g.drawImage(imagen.getImage(), 0, 0, tamanio.width, tamanio.height, null);
		}
		
		public void main(String[] args)
		{
			
		} 
	}
	
	/*Hacer un Juego de Patrulla Vs Auto. Si la Patrulla alcanza al Auto 
	 * se pierde Juego. Si No se llega a una META y se gana Juego. Al menos 
	 * que carrera dure dos minutos. Simular juego antes de Ganar o perder 
	 * de al menos 2 minutos.*/
}
