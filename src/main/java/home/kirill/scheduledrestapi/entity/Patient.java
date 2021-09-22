package home.kirill.scheduledrestapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "patient_profile")
@GenericGenerator(name = "persistGenerator", strategy = "native")
public class Patient {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "persistGenerator")
    private Long id;

    @Column(name = "first_name")
    private String firsName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "old_client_guid", unique = true)
    private String oldClientGUID;

    public Patient() {
    }

    public Patient(String firsName, String lastName, String middleName) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public Patient(String firsName, String lastName, String middleName, String oldClientGUID) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.oldClientGUID = oldClientGUID;
    }

    public Patient(String oldClientGUID) {
        this.oldClientGUID = oldClientGUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getOldClientGUID() {
        return oldClientGUID;
    }

    public void setOldClientGUID(String oldClientGUID) {
        this.oldClientGUID = oldClientGUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(firsName, patient.firsName) && Objects.equals(lastName, patient.lastName) && Objects.equals(middleName, patient.middleName) && Objects.equals(oldClientGUID, patient.oldClientGUID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firsName, lastName, middleName, oldClientGUID);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", oldClientGUID='" + oldClientGUID + '\'' +
                '}';
    }
}
