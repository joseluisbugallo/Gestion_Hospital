package business.dto;

import java.util.ArrayList;

import business.PacientesController;


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
	public ArrayList<DiagnosticoDto> diagnostico= new ArrayList<DiagnosticoDto>();

	
	public String mostrarCitaMedico() {
		PacientesController pc = new PacientesController();
		return "Hora inicio:"+ fechainicio.getHours() + ":"+ fechainicio.getMinutes() + " Hora fin:" + fechafin.getHours()+
				":"+fechafin.getMinutes()+ "  Paciente: "+ pc.findPacientesById(idPaciente).nombre + " En la sala: "+ sala ;
	}


	@Override
	public String toString() {
		return mostrarCitaMedico();
	}
	
	
}
