package edu.epam.trainings.kokotov.model;

/**
 * the entity class describes procedures that are prescribed by a doctor
 *
 * @author    Artem Kokotov
 */

public class Procedure {

    /** field id of the entity Drug */
    private Integer id;

    /** list of prescribed procedures */
    private String procedure;

    /** for whom are the procedure prescribed */
    private Patient patient;

    /** the person who prescribed  procedure*/
    private Staff doctor;

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + procedure.hashCode();
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
        Procedure nextProcedure = (Procedure) obj;
        return procedure.equals(nextProcedure.procedure) && patient.equals(nextProcedure.patient) &&
                doctor.equals(nextProcedure.doctor);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
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
