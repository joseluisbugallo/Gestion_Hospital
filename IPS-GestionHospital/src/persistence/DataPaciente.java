package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.PacienteDto;

public class DataPaciente extends DataManager {

	private static final String SQL_SELECT_PACIENTE = "Select * from paciente";
	private static final String SQL_INSERT_PACIENTE = "Insert into paciente(dni, nombrepaciente, contacto) values (?, ?, ?)";
	private static final String SQL_DELETE_PACIENTE = "Delete from paciente where dni=?";
	private static final String SQL_UPDATE_PACIENTE = "Update paciente set nombrepaciente=?, contacto=? where dni=?";

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
}
