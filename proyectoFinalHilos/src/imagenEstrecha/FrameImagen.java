package imagenEstrecha;

import javax.swing.JFrame;


@SuppressWarnings("serial")
public class FrameImagen extends JFrame
{
	public FrameImagen()
	{
		add(new PanelImegen());
		setSize(800,400);
		setVisible(true);
	    setTitle("Carreras Threads");
	}
	
	public static void main(String[] args)
	{
		@SuppressWarnings("unused")
		FrameImagen f = new FrameImagen();
	}
}
