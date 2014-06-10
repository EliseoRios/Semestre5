package multihilo;

public class carcel {
	 public int posicionPolicia;
     public int posicionLadron;
     
	public carcel(String nombre,int posicion)
	{
		if(nombre == "ladron")
		 this.posicionLadron = posicion;
		
		if (nombre == "policia")
		 this.posicionPolicia = posicion;
	}
}
