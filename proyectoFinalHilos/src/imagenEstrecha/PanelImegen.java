package imagenEstrecha;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelImegen extends JPanel
{
	public void paintComponent(Graphics g)
	{
		Dimension tamanio = getSize();
		
		ImageIcon imagen = new ImageIcon(new ImageIcon(getClass().getResource("carretera.gif")).getImage());
		g.drawImage(imagen.getImage(), 0, 0, tamanio.width, tamanio.height, null);
	}
	
	public static void main(String[] args)
	{
		
	}
}
