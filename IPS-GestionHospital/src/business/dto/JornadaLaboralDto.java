package business.dto;

public class JornadaLaboralDto {

	public int id;
	public java.util.Date fechainicio;
	public java.util.Date fechafin;
	public int idempleado;
	
	@Override
	public String toString() {
		return "Jornada laboral del empleado " + idempleado + " desde " + fechainicio + " hasta " + fechafin;
	}
}
