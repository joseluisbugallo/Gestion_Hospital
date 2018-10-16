package business;

import business.dto.EmpleadoDto;
import business.dto.PacienteDto;
import persistence.DataEmpleado;
import persistence.DataPaciente;

public class PrincipalController {
	
	
	public EmpleadoDto findEmpleado(String dni) {
		EmpleadoDto medico = null;		
		DataEmpleado empleado = new DataEmpleado();
		medico =empleado.getEmpleadoPorDni(dni);
		if(medico.nombre==null) {
			return null;
		}
		return medico;
	}
}