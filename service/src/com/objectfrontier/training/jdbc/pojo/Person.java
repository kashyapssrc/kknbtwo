package com.objectfrontier.training.jdbc.pojo;

import java.sql.Timestamp;

public class Person {

    private long id;
    private String firstName;
    private String email;
    private String birthDate;
    private Timestamp createDate;
    private Address address;
    private String lastName;

    public Person() { super(); }

    public Person(String firstName, String lastName,String email, String birthDate,  Address address) {

        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
    }

    public Person(String firstName, String email, String birthDate, Timestamp createDate, String lastName,Address address) {
        super();
        this.firstName = firstName;
        this.email = email;
        this.birthDate = birthDate;
        this.createDate = createDate;
        this.lastName = lastName;
        this.address = address;
    }

    public Person(long id, String firstName, String lastName, String email, String birthDate, Address address) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
    }


    public Person(String firstName, String lastName, String email, String birthDate) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public Person(long id, String firstName, String email, String birthDate, Timestamp createDate, String lastName) {

        super();
        this.id = id;
        this.firstName = firstName;
        this.email = email;
        this.birthDate = birthDate;
        this.createDate = createDate;
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getBirthDate() {
        return birthDate;
    }
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    public Timestamp getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Person [id=");
        builder.append(id);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", email=");
        builder.append(email);
        builder.append(", birthDate=");
        builder.append(birthDate);
        builder.append(", createDate=");
        builder.append(createDate);
        builder.append(", address=");
        builder.append(address);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append("]");
        return builder.toString();
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + (int) (id ^ (id >>> 32));
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (birthDate == null) {
            if (other.birthDate != null)
                return false;
        } else if (!birthDate.equals(other.birthDate))
            return false;
//        if (createDate == null) {
//            if (other.createDate != null)
//                return false;
//        } else if (!createDate.equals(other.createDate))
//            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
//        if (id != other.id)
//            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        return true;
    }

}
