package business.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.PacientesController;
import business.PrincipalController;
import business.enums.TiposAntecedentes;


public class CitaDto {

	public int id;
	public boolean urgente;
	public java.util.Date fechainicio;
	public java.util.Date fechafin;
	public int idPaciente;
	public int idEmpleado;
	public String sala;
	public String sintomas="";
	public String procedimientos="";
	public String antecedentes="";
	public String prescripcion="";
	public ArrayList<DiagnosticoDto> diagnostico= new ArrayList<DiagnosticoDto>();
	
	//DATOS AUXILIARES PARA TRATAR MI VENTANA(VIC)
	public List<String> listadoPrescripciones= new ArrayList<>();
	public Map<TiposAntecedentes, List<String>> mapaAntecedentes = new HashMap<>();

	
	public String mostrarCitaMedico() {
		PacientesController pc = new PacientesController();
		return "Hora inicio:"+ fechainicio.getHours() + ":"+ fechainicio.getMinutes() + " Hora fin:" + fechafin.getHours()+
				":"+fechafin.getMinutes()+ "  Paciente: "+ pc.findPacientesById(idPaciente).nombre + " En la sala: "+ sala ;
	}
	
	public String mostrarPaciente() {
		PacientesController pc = new PacientesController();
		return pc.findPacientesById(idPaciente).nombre;
	}
	
	public String mostrarMedico() {
		PrincipalController pc = new PrincipalController();
		EmpleadoDto empleado = pc.findEmpleadoId(idEmpleado);
		return empleado.nombre + "; ID: " + empleado.id;
	}

	@Override
	public String toString() {
		return mostrarCitaMedico();
	}
	
	
}
