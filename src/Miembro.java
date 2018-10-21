
//La clase miembro guarda el ID de telegram, junto con su nombre, la membresia, y otros datos de quizas importancia
public class Miembro {
	private long ID;
	private String Nombre;
	private String Apellidos;
	//Constructores
	public Miembro(long ID, String Nombre, String Apellidos) {
		this.ID=ID;
		this.Nombre=Nombre;
		this.Apellidos=Apellidos;
	}
	//Funciones:
	public long getID() {
		return ID;
	}
	public String getNombre() {
		return Nombre;
	}
}
