package Controlador;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * La clase ConnectionDB proporciona una conexión a la base de datos utilizando la biblioteca Apache Commons DBCP2.
 */
public class ConnectionDB {

    private static BasicDataSource dataSource = null;

    /**
     * Obtiene el DataSource para conectar a la base de datos. Si no existe, crea uno nuevo.
     *
     * @return La instancia de DataSource.
     */
    private static DataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            dataSource.setUrl("jdbc:mariadb://localhost:3306/nomina");
            dataSource.setInitialSize(20);
            dataSource.setMaxIdle(15);
            dataSource.setMaxTotal(20);
        }
        return dataSource;
    }

    /**
     * Obtiene una conexión a la base de datos.
     *
     * @return Un objeto Connection.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    /**
     * Cierra la conexión a la base de datos.
     *
     * @param conn El objeto Connection que se cerrará.
     * @throws SQLException si ocurre un error de acceso a la base de datos.
     */
    public static void close(Connection conn) throws SQLException {
        if (conn != null)
            conn.close();
    }

}
