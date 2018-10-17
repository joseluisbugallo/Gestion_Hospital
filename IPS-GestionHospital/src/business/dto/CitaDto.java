package business.dto;

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
		return "Hora inicio:"+ fechainicio.getHours() + ":"+ fechainicio.getMinutes() + " Hora fin:" + fechafin.getHours()+
				":"+fechafin.getMinutes()+ "  Paciente: "+ idPaciente + " En la sala: "+ sala ;
	}
}
