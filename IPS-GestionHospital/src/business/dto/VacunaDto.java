package business.dto;

public class VacunaDto {

	public int id;
	public int idPaciente;
	public java.util.Date fechainicio;
	public java.util.Date fechafin;
	public String vacuna;
	@Override
	public String toString() {
		return "VacunaDto [id=" + id + ", idPaciente=" + idPaciente + ", fechainicio=" + fechainicio + ", fechafin="
				+ fechafin + ", vacuna=" + vacuna + "]";
	}
	

}
