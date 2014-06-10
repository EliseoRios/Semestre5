package multihilo;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;

public class EnPelotas extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JLabel mensaje;
	private Vector<Pelota> Pelotas;
	private PanelFigura panel;
	private Random rnd = new Random();
	int nvoX, nvoY;

	private class Pelota extends Thread 
	{
		private Ellipse2D.Double laPelota;
		private boolean pelotaIniciada;
		private int tam; 
		private int incX, incY; 
		private Color color;

		public Pelota() 
		{
			
			pelotaIniciada = true;
			int r, g, b;
			r = rnd.nextInt(256);
			g = rnd.nextInt(256);
			b = rnd.nextInt(256);
			color = new Color( r, g, b);
			tam = 10 + rnd.nextInt(60);

			int x = rnd.nextInt(300);
			int y = rnd.nextInt(60);
			incX = -10 + rnd.nextInt(21);
			incY = -10 + rnd.nextInt(21);
			nvoX = x;
			nvoY = y;
			if ((incX == 0) && (incY == 0)) { incX = 1; }
			laPelota = new Ellipse2D.Double(x, y, tam, tam );
		}

		public void draw(Graphics2D g2D) 
		{
			if( laPelota != null ) 
			{
				g2D.setColor( color );
				g2D.fill(laPelota);
			}
		}

		public void run() 
		{
			while(pelotaIniciada) 
			{
				try {
					Thread.sleep(16);
			    } catch( Exception e) 
			    {
					System.out.println("Hilo se desperto anticipadamente");
				}
					
				    int antX = (int) laPelota.getX();
				    int antY = (int) laPelota.getY();
					int x, y;
					x = antX + incX;
					if( x > panel.getWidth() ) {
					x = rnd.nextInt( 10 );
					}
					if (x + tam > panel.getWidth() || x < 0) 
					incX = -incX;
					y = antY + incY;
					if( x > panel.getHeight() ) {
					y = rnd.nextInt(10);
					}
					if (y + tam > panel.getHeight() || y < 0) 
					incY = -incY; 
					laPelota.setFrame(x, y, tam, tam);
					panel.repaint();
			}
		}
	}

	private class PanelFigura extends JPanel implements MouseListener 
	{
		private static final long serialVersionUID = 1L;

	      public PanelFigura() 
	      {
		    addMouseListener( this );
		  }

		public void paintComponent( Graphics g ) 
		{
			super.paintComponent( g );
			Graphics2D g2d = (Graphics2D) g;

			for (int i = 0; i < Pelotas.size(); i++) 
			(Pelotas.elementAt(i)).draw(g2d);
			g2d.drawString("hohohohoh", nvoX, nvoY);
		}

		int cantidadPelotas = 0;
		public void mouseClicked(MouseEvent e)
		{
			if(cantidadPelotas < 10)
			{
				Pelota sigPelota = new Pelota();
				Pelotas.addElement(sigPelota);
				sigPelota.start();
				panel.setBackground(new Color( rnd.nextInt(256), 
				rnd.nextInt(256), rnd.nextInt(256)));
				mensaje.setForeground(new Color( rnd.nextInt(256), 
				rnd.nextInt(256), rnd.nextInt(256)));
				mensaje.setText("Numero de Pelotas: " + Pelotas.size());
				
				cantidadPelotas++;
			}
		}

		public void mouseEntered(MouseEvent e) { }
		public void mouseExited(MouseEvent e) { }
		public void mousePressed(MouseEvent e) { }
		public void mouseReleased(MouseEvent e) { }
	}

	public EnPelotas() 
	{
		super("Multithreading - con Pelotas");
		Pelotas = new Vector<Pelota>();
		panel = new PanelFigura();
		mensaje = new JLabel("Numero de pelotas: "+ Pelotas.size() );
		mensaje.setFont(new Font("courier", Font.BOLD, 20));
		panel.setBackground( new Color(200, 255, 128) ); // Red, Green, Blue

		add(panel, BorderLayout.CENTER);
		add(mensaje, BorderLayout.NORTH);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation(300, 200);
		setSize(400,400);
		setVisible(true);
	}
	
	public static void main(String[] args) 
	{
	    new EnPelotas();
	}
}