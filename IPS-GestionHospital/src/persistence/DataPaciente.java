package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.PacienteDto;

public class DataPaciente extends DataManager {

	private static final String SQL_SELECT_PACIENTE = "Select idpaciente, dni, nombrepaciente, contacto from paciente";
	private static final String SQL_SELECT_PACIENTE_BY_DNI = "Select * from paciente where dni=?";
	private static final String SQL_INSERT_PACIENTE = "Insert into paciente(dni, nombrepaciente, contacto) values (?, ?, ?)";
	private static final String SQL_DELETE_PACIENTE = "Delete from paciente where dni=?";
	private static final String SQL_UPDATE_PACIENTE = "Update paciente set nombrepaciente=?, contacto=? where dni=?";
	private static final String SQL_SELECT_PACIENTE_BY_NOMBRE = "Select * from paciente where nombrepaciente=?";
	private static final String SQL_SELECT_PACIENTE_BY_ID = "Select * from paciente where idpaciente=?";
	
	public List<PacienteDto> list() {
		List<PacienteDto> pacientes = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_PACIENTE);
			rs = st.executeQuery();
			pacientes = new ArrayList<>();
			while (rs.next()) {
				PacienteDto paciente = new PacienteDto();
				paciente.id = rs.getInt(1);
				paciente.dni = rs.getString(2);
				paciente.nombre = rs.getString(3);
				paciente.contacto = rs.getString(4);
				pacientes.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return pacientes;
	}

	public void add(PacienteDto paciente) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_INSERT_PACIENTE);
			st.setString(1, paciente.dni);
			st.setString(2, paciente.nombre);
			st.setString(3, paciente.contacto);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void delete(PacienteDto paciente) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_DELETE_PACIENTE);
			st.setString(1, paciente.dni);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void update(PacienteDto paciente) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_UPDATE_PACIENTE);
			st.setString(1, paciente.nombre);
			st.setString(2, paciente.contacto);
			st.setString(3, paciente.dni);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}
	
	public List<PacienteDto> getPacientePorDni(String dni) {
		List<PacienteDto> pacientes = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = Jdbc.getCurrentConnection().prepareStatement(SQL_SELECT_PACIENTE_BY_DNI);
			st.setString(1, dni);
			rs = st.executeQuery();
			pacientes = new ArrayList<PacienteDto>();
			while (rs.next()) {
				PacienteDto paciente = new PacienteDto();
				paciente.id = rs.getInt(1);
				paciente.nombre = rs.getString(3);
				paciente.dni = rs.getString(2);
				paciente.contacto = rs.getString(4);
				pacientes.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return pacientes;
	}
	
	public PacienteDto getPacientePorId(int id) {
		PacienteDto pacientes = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		PacienteDto paciente = new PacienteDto();
		try {
			st = Jdbc.getCurrentConnection().prepareStatement(SQL_SELECT_PACIENTE_BY_ID);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			while (rs.next()) {
				
				paciente.id = rs.getInt(1);
				paciente.nombre = rs.getString(3);
				paciente.dni = rs.getString(2);

				paciente.contacto = rs.getString(4);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return paciente;
	}

	public List<PacienteDto> getPacientePorNombre(String nombre) {
		List<PacienteDto> pacientes = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = Jdbc.getCurrentConnection().prepareStatement(SQL_SELECT_PACIENTE_BY_NOMBRE);
			st.setString(1, nombre);
			rs = st.executeQuery();
			pacientes = new ArrayList<PacienteDto>();
			while (rs.next()) {
				PacienteDto paciente = new PacienteDto();
				paciente.id = rs.getInt(1);
				paciente.nombre = rs.getString(2);
				paciente.dni = rs.getString(3);
				paciente.contacto = rs.getString(4);
				pacientes.add(paciente);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return pacientes;
	}
}
