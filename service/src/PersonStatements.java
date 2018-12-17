
public interface PersonStatements {

    String CREATE_STATEMENT = new StringBuffer()
            .append("INSERT INTO service_person (first_name, last_name, email, birth_date, address_id, create_date)")
            .append("VALUES(?,?,?,?,?,?) " ).toString();

    String READ_STATEMENT = new StringBuffer()
            .append("SELECT id, name, email, address_id, birth_date, create_date ")
            .append("  FROM service_person ")
            .append(" WHERE id = ?").toString();

    String READALL_STATEMENT = new StringBuffer()
            .append("SELECT id, name, email, birth_date, address_id ")
            .append("  FROM service_person ").toString();

    String UPDATE_STATEMENT = new StringBuffer()
            .append("UPDATE service_person ")
            .append("   SET first_name = ?, last_name = ?, email = ?, birth_date = ?")
            .append(" WHERE id = ?").toString();

    String DELETE_STATEMENT = new StringBuffer()
            .append("DELETE")
            .append("  FROM service_person ")
            .append(" WHERE id = ? ").toString();
}
