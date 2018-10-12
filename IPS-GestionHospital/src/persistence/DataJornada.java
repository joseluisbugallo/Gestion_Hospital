package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.JornadaLaboralDto;

public class DataJornada extends DataManager {

	private static final String SQL_SELECT_JORNADA = "Select * from jornadalaboral";
	private static final String SQL_INSERT_JORNADA = "Insert into jornadalaboral(fechainicio, fechafin, idempleado) values (?, ?, ?)";
	private static final String SQL_DELETE_JORNADA = "Delete from jornadalaboral where idjornada=?";
	private static final String SQL_UPDATE_JORNADA = "Update jornadalaboral set fechainicio=?, fechafin=?,"
			+ " idempleado=? where idjornada=?";

	public List<JornadaLaboralDto> list() {
		List<JornadaLaboralDto> jornadas = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_JORNADA);
			rs = st.executeQuery();
			jornadas = new ArrayList<>();
			while (rs.next()) {
				JornadaLaboralDto jornada = new JornadaLaboralDto();
				jornada.id = rs.getInt(1);
				jornada.fechainicio = rs.getDate(2);
				jornada.fechafin = rs.getDate(3);
				jornada.idempleado = rs.getInt(4);
				jornadas.add(jornada);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return jornadas;
	}

	public void add(JornadaLaboralDto jornada) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_INSERT_JORNADA);
			st.setDate(1, jornada.fechainicio);
			st.setDate(2, jornada.fechafin);
			st.setInt(3, jornada.idempleado);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void delete(JornadaLaboralDto jornada) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_DELETE_JORNADA);
			st.setInt(1, ((JornadaLaboralDto) jornada).id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	public void update(JornadaLaboralDto jornada) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_UPDATE_JORNADA);
			st.setDate(1, jornada.fechainicio);
			st.setDate(2, jornada.fechafin);
			st.setInt(3, jornada.idempleado);
			st.setInt(4, jornada.id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

}
