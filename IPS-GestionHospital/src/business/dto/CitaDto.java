package business.dto;

import java.sql.Date;

public class CitaDto {

	public int id;
	public boolean urgente;
	public java.util.Date fechainicio;
	public java.util.Date fechafin;
	public int idPaciente;
	public int idEmpleado;
	public String sala;

	@Override
	public String toString() {
		return "CitaID: " + id + "  " + (urgente ? "URGENTE  " : "") + "Desde: " + fechainicio + "  Hasta: " + fechafin
				+ "  PacienteID: " + idPaciente;
	}
}
