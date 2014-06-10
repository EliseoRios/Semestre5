package policiasLadrones2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class capturaLadron extends JFrame
{
	JPanel[] paneles = new JPanel[3];
	JLabel[] labels = new JLabel[3];
	String[] nombre = {"policia","ladron"};
	
	public capturaLadron()
	{
		//inicializar frame
		add(new PanelImagen());
		setSize(800,400);
		this.setResizable(false);
		setVisible(true);
	    setTitle("Carreras Threads");
		setDefaultCloseOperation(EXIT_ON_CLOSE);//Cierra frame con boton cerrar para matar hilo en ejecucion
	}
	
	class PanelImagen extends JPanel
	{
		public void paintComponent(Graphics g)
		{
			//imagen fondo en metodo dibuja 
			Dimension tamanioPanel = getSize();
			
			ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("carretera.gif")).getImage());
			g.drawImage(imagen.getImage(), 0, 0, tamanioPanel.width, tamanioPanel.height, null);
		}
		
		//contructor panel
		public PanelImagen()
		{
			//listener boton
			final JButton boton = new JButton("Comenzar");
			boton.setSize(50,25);
			boton.setLocation(300, 200);
			add(boton);
			
			boton.addActionListener(new ActionListener()//forma anonima para escuchar
			{
				int vecesPresionadas = 0;
				public void actionPerformed(ActionEvent e) 
				{
					if(vecesPresionadas == 0)
					{
						boton.setText("Parar");
						vecesPresionadas = 1;
					}
					else
					{
					    boton.setText("Comenzar");
					    vecesPresionadas = 0;
					}
			    }
			});
		}
	}
	
			
	public static void main(String[] args) 
	{
	  new capturaLadron();
	}
}

/*
 * //crea autos
		paneles[0] = new JPanel();
		add(paneles[0]);
		labels[0] = new JLabel(nombre[0]);//nombre a label con arreglo paralelo
		
		//agrega imagen
		labels[0].setIcon(new ImageIcon(getClass().getResource(nombre[0]+".gif")));
		paneles[0].add(labels[0]);//aderir label a panel
		labels[0].setLocation(400,400);*/
