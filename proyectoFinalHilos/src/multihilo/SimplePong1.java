package multihilo;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;

@SuppressWarnings("serial")
class Sonido extends Applet 
{
	public static final AudioClip PELOTA = newAudioClip(
	Sonido.class.getResource("Pelota.wav"));
	public static final AudioClip FINDEJUEGO = Applet.newAudioClip(
	Sonido.class.getResource("gameover.wav"));
}

@SuppressWarnings("serial")
public class SimplePong1 extends JFrame 
{
	JPanel panel;
	final int NUM = 2;
	Random rnd = new Random();
	Pelota pelotas[] = new Pelota[NUM];
	Raqueta raqueta = new Raqueta();
	Raqueta2 raqueta2 = new Raqueta2();
	int score = 0;
	boolean perdi=false;

	class Dibuja extends JPanel implements KeyListener 
	{
		public Dibuja() 
		{
			for(int i = 0; i < NUM; i++) 
			{
			  pelotas[i] = new Pelota();
			  pelotas[i].start();
			}
			addKeyListener( this );
		}
			public void paintComponent( Graphics g ) 
			{
				super.paintComponent( g );
				Graphics2D g2d = (Graphics2D) g;
				
				for(int i =0; i < NUM; i++) 
				{
				  g2d.setColor( pelotas[i].col );
				  pelotas[i].draw(g2d);
				}
				
				g2d.setColor(Color.GRAY);
				raqueta.dibuja(g2d);
				g2d.setColor(Color.ORANGE);
				raqueta2.dibuja(g2d);
				g2d.setColor(Color.GRAY);
				g2d.setFont(new Font("Verdana", Font.BOLD, 30));
				g2d.drawString(""+ score, 10, 30);
				
				if( perdi ) 
				{
				  Sonido.FINDEJUEGO.play();
				  g2d.setColor(Color.BLACK);
				  g2d.setFont(new Font("Verdana", Font.BOLD, 24));
				  g2d.drawString("Finalizo el juego", 30,160);
				  
				  for(int i =0; i < NUM; i++)
				   pelotas[i].interrupt();
				}
			}

			@Override
			public void keyPressed(KeyEvent e){}

			public void keyPressed2(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			public void keyTyped(KeyEvent e) {}
	}

	//Raqueta con la que se golpea la pelota
	class Raqueta implements KeyListener 
	{
		int x = 0;
		int incX = 0;
		final int Y = 340, ANCHO = 60, ALTO = 20;

		public Raqueta() 
		{
		 addKeyListener( this );
		}

		public void mover() 
		{
		 if( x + incX > 0 && x + incX < panel.getWidth() - ANCHO)
		  x = x + incX;
		}

		public void dibuja(Graphics2D g2d ) 
		{
		  g2d.fillRect(x, Y, ANCHO, ALTO );
		}

		public Rectangle getBounds() 
		{
		  return new Rectangle(x, Y, ANCHO, ALTO);
		}

		public int getTopY() 
		{
		  return Y;
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
		  if( e.getKeyCode() == KeyEvent.VK_LEFT )
		   incX = -2;
		  if( e.getKeyCode() == KeyEvent.VK_RIGHT )
		   incX = 2;
		}

		public void keyReleased(KeyEvent arg0) 
		{
		  incX = 0;
		}

		public void keyTyped(KeyEvent arg0) { }
	}

	class Raqueta2 implements KeyListener 
	{
		int y = 0;
		int incY = 0;
		final int Z = 10, ANCHO = 60, ALTO = 20;

		public Raqueta2() 
		{
		 addKeyListener( this );
		}

		public void mover()
		{
		 if( y + incY > 0 && y + incY < panel.getWidth() - ANCHO)
		 y = y + incY;
		}

		public void dibuja(Graphics2D g2d ) 
		{
		  g2d.fillRect(y, Z, ANCHO, ALTO );
		}

		public Rectangle getBounds2() 
		{
		  return new Rectangle(y, Z, ANCHO, ALTO);
		}

		public int getTopY() 
		{
		  return Z;
		}

		@Override
		public void keyPressed(KeyEvent e) 
		{
		  if( e.getKeyCode() == KeyEvent.VK_A )
		   incY = -2;
		  if( e.getKeyCode() == KeyEvent.VK_D )
		   incY = 2;
		}

		public void keyReleased(KeyEvent arg0) 
		{
		 incY = 0;
		}

		public void keyTyped(KeyEvent arg0) { }
	}

	//Crean Pelotas
	class Pelota extends Thread 
	{
		public final int DIAMETRO = 30;
		Ellipse2D.Double laPelota;
		int veloc;
		Color col;
		int x = rnd.nextInt(300), y = 0;
		int incX = 1, incY = 1;

		public Pelota( ) 
		{
		 laPelota = new Ellipse2D.Double(x, y, DIAMETRO, DIAMETRO );
		 veloc = 3 + rnd.nextInt(10);
		 col = new Color( rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256) );
		}

		public void draw(Graphics2D g2D) 
		{
	  	 if( laPelota != null )
	  	 {
		   g2D.setColor( col );
		   g2D.fill(laPelota);
		 }
		}

		boolean colision() 
		{
		 return raqueta.getBounds().intersects(getBounds());
		}

		//raqueta 2
		boolean colision2()
		{
		 return raqueta2.getBounds2().intersects(getBounds());
		}

		public Rectangle getBounds() 
		{
		  return new Rectangle(x, y, DIAMETRO, DIAMETRO);
		}

		public void run() 
		{
			while( !perdi ) 
			{
				try 
				{
				 Thread.sleep( veloc );
				}
				catch (Exception e ) 
				{
				 System.out.println("Hilo se desperto anticipadamente");
				}
				
				int antX = (int) laPelota.getX();
				int antY = (int) laPelota.getY();
				x = antX + incX;
				y = antY + incY;

				if (x + incX < 0)
				 incX = 1;
				if (panel != null && x + incX > panel.getWidth() - 30)
				 incX = -1;
				if (y + incY < 0)
				incY = 1;
				if (panel != null && y + incY > panel.getHeight() - 30)
				{
				 incY = -1;
				 perdi = true;
				}
				if( colision() ) 
				{
				 Sonido.PELOTA.play();
				 score++;
				 incY = -1;
				 y = raqueta.Y - DIAMETRO;
				}
				if( colision2() ) 
				{
				  Sonido.PELOTA.play();
				  score ++;
				  incY= 1;
				  y = raqueta2.y + DIAMETRO;
				}

				laPelota.setFrame(x, y, DIAMETRO, DIAMETRO);
				raqueta.mover();
				raqueta2.mover();
				if( panel != null)
				panel.repaint();

				}
			}
		}
	

	public SimplePong1() 
	{
	  super("Juego de PONG simple");
	  panel = new Dibuja();
	  panel.setSize(300, 400);

	  add(panel, BorderLayout.CENTER);
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  setLocation(300, 300);
	  setSize(300, 400);
	  setVisible(true);
	}
	
	public static void main(String[] args) 
	{
	  new SimplePong1();
	}
}