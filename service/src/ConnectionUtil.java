


import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

import com.objectfrontier.training.jdbc.dbconnection.ConnectionManager;

/**
 * @author hariraj.sampath
 * To find the connection Id in runtime
 */
public class ConnectionUtil {

    public static void logConnectionId(Connection con) {
        System.out.format("JDBC Connection Id : %s ", getJDBCConnectionId(con));
    }

    /**
     * Get the connection id for a particular connection, used for checking the
     * connection leaks
     * 
     * @param conn
     * @return connectionId - an unique id for a particular connection
     */
    private static long getJDBCConnectionId(Connection conn) {

        long connectionId = 0;
        try {

            Field connectionField = conn.getClass().getSuperclass()
                    .getDeclaredField("_conn");
            if (connectionField != null) {

                connectionField.setAccessible(true);
                Object connectionObject = connectionField.get(conn);
                Field fConNext = connectionObject.getClass().getSuperclass()
                        .getDeclaredField("_conn");
                if (fConNext != null) {

                    fConNext.setAccessible(true);
                    Object objConnNext = fConNext.get(connectionObject);
                    Field fConnectionId = objConnNext.getClass()
                            .getSuperclass().getDeclaredField("connectionId");
                    if (fConnectionId != null) {

                        fConnectionId.setAccessible(true);
                        connectionId = fConnectionId.getLong(objConnNext);
                        fConnectionId.setAccessible(false);
                    }
                    fConNext.setAccessible(false);
                }
                connectionField.setAccessible(false);
            }
        } catch (Exception e) {
            System.err.format("Exception occurred while getting connection id : %s ", e.getMessage());
        }
        return connectionId;
    }
public static void main(String[] args) throws SQLException {
//    ConnectionManager cm = new ConnectionManager();
    Connection connection = ConnectionManager.getConnection();
    logConnectionId(connection);
}
}