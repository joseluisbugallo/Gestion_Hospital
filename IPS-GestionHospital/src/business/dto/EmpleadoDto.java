package business.dto;

public class EmpleadoDto {
	
	public int id;
	public String nombre;
	public String dni;
	public String cargo;
	public String correo;
	public String estado;
	
	
	@Override
	public String toString() {
		return nombre + ",\t ID : " + id;
	}
}
