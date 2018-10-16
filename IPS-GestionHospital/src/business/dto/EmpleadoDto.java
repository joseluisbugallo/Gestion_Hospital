package business.dto;

public class EmpleadoDto {
	
	public int id;
	public String nombre;
	public String dni;
	public String cargo;
	public String correo;
	
	
	@Override
	public String toString() {
		return "ID del " + cargo + ": " + id + " DNI: " + dni +  " correo: " + correo + ".";
	}
}
