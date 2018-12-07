package edu.epam.trainings.kokotov.model;

/**
 * the entity class describes the patient model
 *
 * @author    Artem Kokotov
 */

public class Patient {

    /** field id of the entity patient */
    private Integer id;

    /** field name of the entity patient */
    private String name;

    /** field surname of the entity patient */
    private String surname;

    /** field phone of the entity patient */
    private String phone;

    /** field birthday of the entity patient */
    private String birthday;

    /** field email of the entity patient */
    private String email;

    /** field startDiagnosis which indicates the primary diagnosis of the patient */
    private String startDiagnosis;

    /** field startDiagnosis which indicates the final  diagnosis of the patient */
    private String endDiagnosis;

    /** date of admission to hospital */
    private String come;

    /** field startDiagnosis which indicates the final  diagnosis of the patient */
    private String out;

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + startDiagnosis.hashCode();
        result = 31 * result + endDiagnosis.hashCode();
        result = 31 * result + come.hashCode();
        result = 31 * result + out.hashCode();
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
        Patient nextPatient = (Patient) obj;
        return name.equals(nextPatient.name) && surname.equals(nextPatient.surname) &&
                phone.equals(nextPatient.phone) && email.equals(nextPatient.email);
    }

    public String toString(){
        return "ID: " + this.id+ "\nимя: "+ this.name
                + "\nфамилия: "+ this.surname
                + "\nтелефон: " + this.phone
                + "\ne-mail: " + this.email
                + "\nпервичный диагноз: "+this.startDiagnosis
                + "\nокончательный диагноз: " +this.endDiagnosis
                + "\nприбыл: " + this.come
                + "\nвыписан: " + this.out;
    }

    public Integer getId() {
        return id;
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

    public String getStartDiagnosis() {
        return startDiagnosis;
    }

    public void setStartDiagnosis(String startDiagnosis) {
        this.startDiagnosis = startDiagnosis;
    }

    public String getEndDiagnosis() {
        return endDiagnosis;
    }

    public void setEndDiagnosis(String endDiagnosis) {
        this.endDiagnosis = endDiagnosis;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCome() {
        return come;
    }

    public void setCome(String come) {
        this.come = come;
    }

    public String getOut() {
        return out;
    }

    public void setOut(String out) {
        this.out = out;
    }
}
