package edu.epam.trainings.kokotov.model;

/**
 * the entity class describes the staff model
 *
 * @author    Artem Kokotov
 */

public class Staff {

    /** field id of the entity patient */
    private Integer id;

    /** field name of the entity patient */
    private String name;

    /** field surname of the entity patient */
    private String surname;

    /** field phone of the entity patient */
    private String phone;

    /** field e-mail of the entity patient */
    private String email;

    /** staff qualification (doctor, nurse) */
    private Integer qual_id;


    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + qual_id.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Staff nextStaff = (Staff) obj;
        return name.equals(nextStaff.name) && surname.equals(nextStaff.surname) &&
                phone.equals(nextStaff.phone) && email.equals(nextStaff.email) &&
                qual_id == nextStaff.qual_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getQual_id() {
        return qual_id;
    }

    public void setQual_id(Integer qual_id) {
        this.qual_id = qual_id;
    }
}
