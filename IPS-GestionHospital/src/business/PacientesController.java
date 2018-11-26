package business;

import java.util.List;

import business.dto.PacienteDto;
import persistence.DataPaciente;

public class PacientesController {

	public List<PacienteDto> findPacientesByDni(String dni) {
		List<PacienteDto> pacientes = null;
		DataPaciente dp = new DataPaciente();
		pacientes = dp.getPacientePorDni(dni);
		for (PacienteDto paciente : pacientes)
			if (paciente.dni == null) {
				return null;
			}
		return pacientes;
	}
	
	public PacienteDto findPacientesById(int id) {
		PacienteDto pacientes = null;
		DataPaciente dp = new DataPaciente();
		pacientes = dp.getPacientePorId(id);
		return pacientes;
	}

	public List<PacienteDto> findPacientesByNombre(String nombre) {
		List<PacienteDto> pacientes = null;
		DataPaciente dp = new DataPaciente();
		pacientes = dp.getPacientePorNombre(nombre);
		for (PacienteDto paciente : pacientes) {
			if (paciente.nombre == null) {
				return null;
			}
		}
		return pacientes;
	}

	public List<PacienteDto> listadoPacientes() {
		DataPaciente dp = new DataPaciente();
		List<PacienteDto> pacientes = dp.list();
		return pacientes;
	}

	public void updateInfoContacto(PacienteDto seleccion) {
		DataPaciente dp = new DataPaciente();
		dp.update(seleccion);
		
	}

	public void desactivarPaciente(PacienteDto paciente) {
		DataPaciente dp = new DataPaciente();
		dp.update(paciente);
		
	}
}
