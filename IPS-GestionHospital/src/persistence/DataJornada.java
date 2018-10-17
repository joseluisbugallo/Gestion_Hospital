package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.JornadaLaboralDto;

public class DataJornada extends DataManager {

	private static final String SQL_SELECT_JORNADA = "Select * from jornadalaboral";
	private static final String SQL_INSERT_JORNADA = "Insert into jornadalaboral(idempleado, fechainicio, fechafin ) values (?, ?, ?)";
	private static final String SQL_DELETE_JORNADA = "Delete from jornadalaboral where idjornada=?";
	private static final String SQL_UPDATE_JORNADA = "Update jornadalaboral set idempleado=? fechainicio=?, fechafin=?,"
			+ "  where idjornada=?";
	
	private static final String SQL_SELECT_JORNADA_BY_MEDICO = "Select * from jornadalaboral where idempleado=?";

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
				jornada.idempleado = rs.getInt(2);
				jornada.fechainicio = rs.getTimestamp(4);
				jornada.fechafin = rs.getTimestamp(3);
				
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
			st.setInt(1, jornada.idempleado);
			st.setTimestamp(3, new Timestamp(jornada.fechainicio.getTime()));
			st.setTimestamp(2, new Timestamp(jornada.fechafin.getTime()));
			

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
			st.setInt(1,  jornada.id);

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
			st.setInt(1, jornada.idempleado);
			st.setTimestamp(3, new Timestamp(jornada.fechainicio.getTime()));
			st.setTimestamp(2, new Timestamp(jornada.fechafin.getTime()));
			
			st.setInt(4, jornada.id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}
	
	public List<JornadaLaboralDto> listJornadasByEmpleado(int id){
		PreparedStatement st= null;
		ResultSet rs = null;
		List<JornadaLaboralDto> jornadas = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_JORNADA_BY_MEDICO);
			st.setLong(1, id);
			
			rs = st.executeQuery();
			jornadas = new ArrayList<>();
			while(rs.next()) {
				JornadaLaboralDto jornada = new JornadaLaboralDto();
				jornada.id = rs.getInt(1);
				
				jornada.idempleado = rs.getInt(2);
				
				jornada.fechainicio = rs.getTimestamp(4);
				jornada.fechafin = rs.getTimestamp(3);
				
				jornadas.add(jornada);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs,st);
		}
		return jornadas;
	}

}
