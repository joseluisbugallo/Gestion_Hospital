package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import alb.util.jdbc.Jdbc;
import business.dto.EmpleadoDto;

public class DataEmpleado extends DataManager {

	private static final String SQL_SELECT_EMPLEADO = "Select * from empleado";
	private static final String SQL_SELECT_EMPLEADO_BY_DNI = "Select * from empleado where dni=?";
	private static final String SQL_INSERT_EMPLEADO = "Insert into empleado(nombreempleado, dni, cargo, correo, estado)"
			+ " values (?, ?, ?, ?, ?)";
	private static final String SQL_DELETE_EMPLEADO = "Delete from empleado where dni=?";
	private static final String SQL_UPDATE_EMPLEADO = "Update empleado set nombreempleado=?, cargo=?, correo=?, estado=?, dni=? where idempleado=?";

	
	public List<EmpleadoDto> list() {
		List<EmpleadoDto> empleados = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_SELECT_EMPLEADO);
			rs = st.executeQuery();
			empleados = new ArrayList<>();
			while (rs.next()) {
				EmpleadoDto empleado = new EmpleadoDto();
				empleado.id = rs.getInt(1);
				empleado.nombre = rs.getString(2);
				empleado.dni = rs.getString(3);
				empleado.cargo = rs.getString(4);
				empleado.correo = rs.getString(5);
				empleado.estado= rs.getString(6);
				empleados.add(empleado);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return empleados;
	}

	
	public void add(EmpleadoDto empleado) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_INSERT_EMPLEADO);
			st.setString(1, empleado.nombre);
			st.setString(2, empleado.dni);
			st.setString(3, empleado.cargo);
			st.setString(4, empleado.correo);
			st.setString(5, "Activado");

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	
	public void delete(EmpleadoDto empleado) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_DELETE_EMPLEADO);
			st.setString(1, empleado.dni);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}

	
	public void update(EmpleadoDto empleado) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = getConexion().prepareStatement(SQL_UPDATE_EMPLEADO);
			st.setString(1, empleado.nombre);
			st.setString(2, empleado.cargo);
			st.setString(3, empleado.correo);
			st.setString(4, empleado.estado);
			st.setString(5, empleado.dni);
			st.setLong(6, empleado.id);

			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
	}


	@SuppressWarnings("null")
	public EmpleadoDto getEmpleadoPorDni(String dni) {
		EmpleadoDto empleado = new EmpleadoDto();
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = Jdbc.getCurrentConnection().prepareStatement(SQL_SELECT_EMPLEADO_BY_DNI);
			st.setString(1, dni);
			rs = st.executeQuery();
			while (rs.next()) {
				empleado.id = rs.getInt(1);
				empleado.nombre = rs.getString(2);
				empleado.dni = rs.getString(3);
				empleado.cargo = rs.getString(4);
				empleado.correo = rs.getString(5);
				empleado.estado= rs.getString(6);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Jdbc.close(rs, st);
		}
		return empleado;
	}

}
