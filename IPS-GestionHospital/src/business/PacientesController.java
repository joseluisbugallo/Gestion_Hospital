package business;

import java.util.List;

import business.dto.PacienteDto;
import persistence.DataPaciente;

public class PacientesController {

	public PacienteDto findPacienteByDni(String dni) {
		PacienteDto paciente = null;
		DataPaciente pacientes = new DataPaciente();
		paciente=pacientes.getPacientePorDni(dni);
		if(paciente.nombre==null) {
			return null;
		}
		return paciente;
	}
	
	public List<PacienteDto> listadoPacientes(){
		DataPaciente dp = new DataPaciente();
		List<PacienteDto> pacientes = dp.list();
		return pacientes;
	}
}
