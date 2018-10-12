package persistence;

import java.sql.Connection;
import alb.util.jdbc.Jdbc;

public abstract class DataManager {

	private Connection conexion;

	public Connection getConexion() {
		if (conexion == null)
			conexion = Jdbc.getCurrentConnection();
		return conexion;
	}
}
