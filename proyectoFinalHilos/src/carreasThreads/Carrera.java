package carreasThreads;

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
public class Carrera extends JFrame
{
	JPanel[] paneles;
	JLabel[] labels;
	String[] nombre = {"conejo","tortuga","zorro"};//nombre de label
	JButton boton;
	int ancho = 700;
	
	public Carrera()
	{
		//visualizar en framework como tabla (filas,columnas)
		setLayout(new GridLayout(0,1));
		
		paneles = new JPanel[3];
		labels = new JLabel[3];
		
		for(int  n=0; n<3; n++)
		{
			paneles[n] = new JPanel();
			add(paneles[n]);//adiere panel a frame
			labels[n] = new JLabel(nombre[n]);//nombre a label con arreglo paralelo
		
			//agrega imagen
			labels[n].setIcon(new ImageIcon(getClass().getResource(nombre[n]+".gif")));
			
			paneles[n].add(labels[n]);//aderir label a panel
			labels[n].setLocation(0,0);
		}
		boton = new JButton("Comenzar carrera");//boton con texto
		
		boton.addActionListener(new ActionListener()//forma anonima para escuchar
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//accion de boton
				Animal conejo = new Animal("El conejo ",ancho-250,labels[0]);
				Animal tortuga = new Animal("La tortuga",ancho-250,labels[1]);
				Animal zorro = new Animal("El zorro",ancho-250,labels[2]);
				
				conejo.start();
				tortuga.start();
				zorro.start();
		    }
		});
		
		//aderir boton a frame
		add(boton);
		  
		//Cierra frame con boton cerrar para matar hilo en ejecucion
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLocation(200,250);//posicion
		setSize(ancho,250);//tamaño
		setVisible(true);//visible
	}
	
	public static void main (String[] args)
	{
		@SuppressWarnings("unused")
		Carrera carreara = new Carrera();
	}
}
