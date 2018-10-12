package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.HistorialDto;

public class DataHistorial extends DataManager {

	private static final String SQL_SELECT_HISTORIAL = "Select * from historial";
	private static final String SQL_INSERT_HISTORIAL = "Insert into historial(datos, idpaciente) values (?, ?)";
	private static final String SQL_DELETE_HISTORIAL = "Delete from historial where idhistorial=?";
	private static final String SQL_UPDATE_HISTORIAL = "Update historial set datos=?, idpaciente=? where idhistorial=?";

	public List<HistorialDto> list() {
		List<HistorialDto> historiales = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_HISTORIAL);
			rs = st.executeQuery();
			historiales = new ArrayList<>();
			while (rs.next()) {
				HistorialDto historial = new HistorialDto();
				historial.id = rs.getInt(1);
				historial.datos = rs.getString(2);
				historial.idPaciente = rs.getInt(3);
				historiales.add(historial);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return historiales;
	}

	public void add(HistorialDto historial) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_INSERT_HISTORIAL);
			st.setString(1, historial.datos);
			st.setInt(2, historial.idPaciente);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void delete(HistorialDto historial) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_DELETE_HISTORIAL);
			st.setInt(1, historial.id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void update(HistorialDto historial) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_UPDATE_HISTORIAL);
			st.setString(1, historial.datos);
			st.setInt(2, historial.idPaciente);
			st.setInt(3, historial.id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}
}
