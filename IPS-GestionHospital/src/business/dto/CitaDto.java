package business.dto;

import java.util.ArrayList;
import java.util.List;

import business.PacientesController;
<<<<<<< master
=======
import business.PrincipalController;
import business.enums.TiposAntecedentes;

>>>>>>> Ventana Modificar Citas

public class CitaDto {

	public int id;
	public boolean urgente;
	public java.util.Date fechainicio;
	public java.util.Date fechafin;
	public int idPaciente;
	public int idEmpleado;
	public String sala;
	public String sintomas = "";
	public String procedimientos = "";
	public String antecedentes = "";
	public String prescripcion = "";
	public ArrayList<DiagnosticoDto> diagnostico = new ArrayList<DiagnosticoDto>();

	// DATOS AUXILIARES PARA TRATAR MI VENTANA(VIC)
	public List<String> listadoPrescripciones = new ArrayList<>();
	public List<String> listadoAntecedentes = new ArrayList<>();

	public String mostrarCitaMedico() {
		PacientesController pc = new PacientesController();
		return "Hora inicio:" + fechainicio.getHours() + ":" + fechainicio.getMinutes() + " Hora fin:"
				+ fechafin.getHours() + ":" + fechafin.getMinutes() + "  Paciente: "
				+ pc.findPacientesById(idPaciente).nombre + " En la sala: " + sala;
	}
<<<<<<< master
=======
	
	public String mostrarPaciente() {
		PacientesController pc = new PacientesController();
		return pc.findPacientesById(idPaciente).nombre;
	}
	
	public String mostrarMedico() {
		PrincipalController pc = new PrincipalController();
		return pc.findEmpleadoId(idEmpleado).nombre;
	}
>>>>>>> Ventana Modificar Citas

	@Override
	public String toString() {
		return mostrarCitaMedico();
	}

}
