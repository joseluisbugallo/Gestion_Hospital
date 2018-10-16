package business;

import java.util.ArrayList;
import java.util.List;
import business.dto.EmpleadoDto;
import persistence.DataEmpleado;

public class JornadaController {
	
	public List<EmpleadoDto> getMedicos(){
		DataEmpleado dE = new DataEmpleado();
		List<EmpleadoDto> em = dE.list();
		List<EmpleadoDto> medicos = new ArrayList<EmpleadoDto>();
		for (EmpleadoDto e : em) {
			if(e.cargo=="Medico") {
				medicos.add(e);
			}
		}
		return medicos;
	}
	
	public List<EmpleadoDto> getEnfermeros(){
		DataEmpleado dE = new DataEmpleado();
		List<EmpleadoDto> em = dE.list();
		List<EmpleadoDto> enfermeros = new ArrayList<EmpleadoDto>();
		for (EmpleadoDto e : em) {
			if(e.cargo=="Enfermero") {
				enfermeros.add(e);
			}
		}
		return enfermeros;
	}
	
}
