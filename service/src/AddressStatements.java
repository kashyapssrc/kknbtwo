
public interface AddressStatements {

    String CREATE_STATEMENT = new StringBuffer()
            .append("INSERT INTO service_address (street, city, postal_code)")
            .append("VALUES(?,?,?)").toString();

    String READ_STATEMENT = new StringBuffer()
            .append("SELECT id, street, city, postal_code")
            .append("  FROM service_address")
            .append(" WHERE id = ?").toString();

    String READALL_STATEMENT = new StringBuffer()
            .append("SELECT id, street, city, postal_code ")
            .append("  FROM service_address").toString();


    String UPDATE_STATEMENT = new StringBuffer()
            .append("UPDATE service_address ")
            .append("   SET street = ?, city = ?, postal_code = ?")
            .append(" WHERE id = ?").toString();

    String DELETE_STATEMENT = new StringBuffer()
            .append("DELETE")
            .append("  FROM service_address ")
            .append(" WHERE id = ? ").toString();

    String SEARCH_STATEMENT = new StringBuffer()
            .append("SELECT id, street, city, postal_code")
            .append("  FROM service_address ")
            .append(" WHERE ").toString();

    String SEARCH_STREET_STATEMENT = new StringBuffer()
            .append(" street LIKE ? ").toString();

    String SEARCH_CITY_STATEMENT = new StringBuffer()
            .append(" city LIKE ? ").toString();

    String SEARCHPOSTAL_CODE_STATEMENT = new StringBuffer()
            .append(" postal_code LIKE ? ").toString();

    String SEARCH_DEFAULT_STATEMENT = new StringBuffer()
            .append(" street LIKE ? OR city LIKE ? OR postal_code LIKE ? ").toString();
}
