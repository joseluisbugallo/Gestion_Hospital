package business.dto;

public class PacienteDto {

	public int id;
	public String dni;
	public String nombre;
	public String contacto;
	
	
	@Override
	public String toString() {
		return nombre + " DNI paciente: " + dni + ".";
	}
}
