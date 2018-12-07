package edu.epam.trainings.kokotov.model;

/**
 * the entity class describes operations that are prescribed by a doctor
 *
 * @author    Artem Kokotov
 */

public class Operation {

    /** field id of the entity Operation */
    private Integer id;

    /** list of prescribed operations */
    private String operation;

    /** for whom are the operation prepared */
    private Patient patient;

    /** the person who prescribed  operation*/
    private Staff doctor;

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + operation.hashCode();
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
        Operation nextOperation = (Operation) obj;
        return operation.equals(nextOperation.operation) && patient.equals(nextOperation.patient) &&
                doctor.equals(nextOperation.doctor);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
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
