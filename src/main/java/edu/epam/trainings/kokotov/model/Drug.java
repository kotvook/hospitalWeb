package edu.epam.trainings.kokotov.model;

/**
 * the entity class describes medications that are prescribed by a doctor
 *
 * @author    Artem Kokotov
 */

public class Drug {

    /** field id of the entity Drug */
    private Integer id;

    /** list of prescribed drugs */
    private String drug;

    /** for whom are the medications prescribed */
    private Patient patient;

    /** the person who prescribed  drugs*/
    private Staff doctor;

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + drug.hashCode();
        result = 31 * result + patient.hashCode();
        result = 31 * result + doctor.hashCode();
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
        Drug nextDrug = (Drug) obj;
        return drug.equals(nextDrug.drug) && patient.equals(nextDrug.patient) &&
                doctor.equals(nextDrug.doctor);
    }

    public String toString(){
        return "\nID: " + this.id+ "\nлекарство: "+ this.drug
                + "\nпациенту: "+ this.patient.getSurname() + " " + this.patient.getName()
                + "\nвыписал врач: " + this.doctor.getSurname() + " " + this.doctor.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Staff getDoctor() {
        return doctor;
    }

    public void setDoctor(Staff doctor) {
        this.doctor = doctor;
    }
}
