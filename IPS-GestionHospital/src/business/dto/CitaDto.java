package business.dto;

public class CitaDto {

	public int id;
	public boolean urgente;
	public java.util.Date fechainicio;
	public java.util.Date fechafin;
	public int idPaciente;
	public int idEmpleado;
	public String sala;
	public String sintomas;
	public String procedimientos;
	public String antecedentes;
	public String prescripcion;

	
	public String mostrarCitaMedico() {
		return "Hora inicio:"+ fechainicio.getHours() + ":"+ fechainicio.getMinutes() + " Hora fin:" + fechafin.getHours()+
				":"+fechafin.getMinutes()+ "  Paciente: "+ idPaciente + " En la sala: "+ sala ;
	}


	@Override
	public String toString() {
		return "Cita: id" + id + ",  ¿Es urgente?" + urgente + ", Fecha inicio:" + fechainicio + ", Fecha fin:" + fechafin
				+ ", idPaciente=" + idPaciente + ", idEmpleado=" + idEmpleado + ", sala=" + sala;
	}
	
	
}
