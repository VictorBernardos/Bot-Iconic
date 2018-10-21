import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public  class leerficheros {
	private File fichero;
	public leerficheros(String nombre){
		fichero = new File(nombre);
	}
	public String devolver(String token) {
		//File fichero = new File(nombre);
		Scanner s= null;
		String linea = null;
		try {
			s = new Scanner(fichero);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (s.hasNextLine()) {
			linea = s.nextLine(); 	// Guardamos la linea en un String
			//a mi amorsito victor: hasNextLine, lee linea por linea, asi que puedes guardar distintos parametros en un unico fichero
			//tkm, victor
			if(linea.startsWith(token)) {
				//System.out.println("Hemos encontrado lo que querias: "+ linea);
				//Encontramos lo que nos interesa, borramos la parte innecesaria
				linea=linea.substring((token.length()+1)); //El +1 es para borrar el = que pongo despues de cada parametro
				//salida=linea.substring(beginIndex)
				return linea;
			}
		}
		return null;
	}
}
