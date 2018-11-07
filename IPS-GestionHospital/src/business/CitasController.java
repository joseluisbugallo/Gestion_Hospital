package business;

import java.util.ArrayList;

import java.util.List;

import business.dto.CitaDto;
import business.dto.EmpleadoDto;
import business.dto.HistorialDto;
import business.dto.JornadaLaboralDto;
import persistence.DataCita;
import persistence.DataHistorial;
import persistence.DataJornada;

public class CitasController {

	public boolean comprobarDisponibilidadEmpleado(EmpleadoDto empleado, CitaDto cita) {
		List<JornadaLaboralDto> jornadas = new ArrayList<JornadaLaboralDto>();
		jornadas = obtenerJornadasEmpleado(empleado);
		if(jornadas.size()==0)
		{
			return false;
		}
		for (JornadaLaboralDto jornada : jornadas) {
//			System.out.println(jornada.fechainicio.compareTo(cita.fechainicio)>=0);
//			System.out.println(jornada.fechafin.compareTo(cita.fechafin)<=0);
			if (jornada.fechainicio.compareTo(cita.fechainicio)>=0 
					&& jornada.fechafin.compareTo(cita.fechafin)<=0) {
					return true; // La cita está disponible!!
			}
		}
		return false;
	}

	public List<CitaDto> getListadoCitas(EmpleadoDto empleado) {
		List<CitaDto> listadoCitas = obtenerCitasEmpleado(empleado.id);
		if (listadoCitas == null)
			return new ArrayList<>();
		else
			return listadoCitas;
	}
	
	public String cargarDatosHistorial(int idPaciente) {
		HistorialDto historial = new HistorialDto();
		DataHistorial dh = new DataHistorial();
		historial = dh.getHistorialByPaciente(idPaciente);
		if(historial.id==0) {
			return "No existe el historial de ese paciente";
		}
		
		return historial.datos;
	}
	
	public List<CitaDto> getListadoCompletoDecitas() {
		List<CitaDto> citas =null;
		DataCita dC = new DataCita();
		citas =dC.list();
		return citas;
	}
	

	public boolean comprobarRestoCitas(CitaDto cita, int idEmpl) {
		List<CitaDto> citas = obtenerCitasEmpleado(idEmpl);
		boolean valido=true;
		for (CitaDto c : citas) {
			if (c.fechainicio.compareTo(cita.fechafin)>=0 || c.fechafin.compareTo(cita.fechainicio)<=0)
				continue;
			else {
				valido= false;
			}
		}
		return valido;
	}

	private List<CitaDto> obtenerCitasEmpleado(int idEmpleado) {
		DataCita dC = new DataCita();
		List<CitaDto> cs = dC.listCitasByidEmpleado(idEmpleado);
		return cs;
	}

	private List<JornadaLaboralDto> obtenerJornadasEmpleado(EmpleadoDto medico) {
		DataJornada dJ = new DataJornada();
		List<JornadaLaboralDto> js = dJ.listJornadasByEmpleado(medico.id);
		return js;
	}
	
	public void crearCita(CitaDto cita, List<EmpleadoDto> empleados) {
		DataCita dc = new DataCita();
		for (EmpleadoDto em: empleados) {
			cita.idEmpleado=em.id;
			dc.add(cita);
		}
		
	}
	
	public String obtenerProcCita (CitaDto cita){
		if(cita.procedimientos != null) {
			return cita.procedimientos;
		}
		else {
			return " ";
		}
			
	}
	
	public void addProc(CitaDto cita, String proc) {
		DataCita dc = new DataCita();
		cita.procedimientos=proc;
		dc.updateProc(cita);
	}

	public void modificarSintomas(CitaDto cita, String text) {
		DataCita dc= new DataCita();
		cita.sintomas=text;
		dc.updateSintomas(cita);
		
	}
	
	public CitaDto getCitaById(int id) {
		DataCita dc = new DataCita();
		return dc.listCitaById(id).get(0);
	}
}
