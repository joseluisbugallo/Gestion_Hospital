package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.CitaDto;

public class DataCita extends DataManager {

	private static final String SQL_SELECT_CITA = "Select * from cita";
	private static final String SQL_SELECT_CITA_BY_IDEMPLEADO = "Select * from cita where idempleado = ?";
	private static final String SQL_INSERT_CITA = "Insert into empleado"
			+ "(urgente, fechainicio, fechafin, idpaciente, idempleado, sala) " + "values (?, ?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_CITA = "Delete from cita where idcita=?";
	private static final String SQL_UPDATE_CITA = "Update cita set urgente=?, fechainicio=?,"
			+ " fechafin=?, idpaciente=?, idempleado=?, sala=? where idcita=?";

	public List<CitaDto> list() {
		List<CitaDto> citas = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_CITA);
			rs = st.executeQuery();
			citas = new ArrayList<>();
			while (rs.next()) {
				CitaDto cita = new CitaDto();
				cita.id = rs.getInt(1);
				cita.urgente = rs.getBoolean(2);
				cita.fechainicio = rs.getDate(3);
				cita.fechafin = rs.getDate(4);
				cita.idPaciente = rs.getInt(5);
				cita.idEmpleado = rs.getInt(6);
				citas.add(cita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return citas;
	}
	
	public List<CitaDto> listCitasByidEmpleado(int id) {
		List<CitaDto> citas = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_CITA_BY_IDEMPLEADO);
			st.setInt(1, id);
			rs = st.executeQuery();
			citas = new ArrayList<>();
			while (rs.next()) {
				CitaDto cita = new CitaDto();
				cita.id = rs.getInt(1);
				cita.urgente = rs.getBoolean(2);
				cita.fechainicio = rs.getDate(3);
				cita.fechafin = rs.getDate(4);
				cita.idPaciente = rs.getInt(5);
				cita.idEmpleado = rs.getInt(6);
				citas.add(cita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return citas;
	}

	public void add(CitaDto cita) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_INSERT_CITA);
			st.setBoolean(1, cita.urgente);
			st.setDate(2, cita.fechainicio);
			st.setDate(3, cita.fechafin);
			st.setInt(4, cita.idPaciente);
			st.setInt(5, cita.idEmpleado);
			st.setString(6, cita.sala);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void delete(CitaDto cita) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_DELETE_CITA);
			st.setInt(1, cita.id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void update(CitaDto cita) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_UPDATE_CITA);
			st.setBoolean(1, cita.urgente);
			st.setDate(2, cita.fechainicio);
			st.setDate(3, cita.fechafin);
			st.setInt(4, cita.idPaciente);
			st.setInt(5, ((CitaDto) cita).idEmpleado);
			st.setString(6, ((CitaDto) cita).sala);
			st.setInt(7, ((CitaDto) cita).id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}
}
