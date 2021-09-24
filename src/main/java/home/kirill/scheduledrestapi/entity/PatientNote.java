package home.kirill.scheduledrestapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "patient_note")
@GenericGenerator(name = "persistGenerator", strategy = "native")
public class PatientNote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "persistGenerator")
    private Long id;

    @Column(name = "created_date_time", nullable = false)
    private Date createDate;

    @Column(name = "last_modified_date_time", nullable = false)
    private Date lastModifiedDate;

    @OneToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdUser;

    @OneToOne
    @JoinColumn(name = "last_modified_by_user_id")
    private User lastModifiedUser;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column
    private String note;

    public PatientNote() {
    }

    public PatientNote(Date createDate, Date lastModifiedDate, User createdUser, User lastModifiedUser, Patient patient, String note) {
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
        this.createdUser = createdUser;
        this.lastModifiedUser = lastModifiedUser;
        this.patient = patient;
        this.note = note;
    }

    public PatientNote(User createdUser, Patient patient, String note) {
        this.createdUser = createdUser;
        this.patient = patient;
        this.note = note;
        this.createDate = new Date();
        this.lastModifiedDate = this.createDate;
        this.lastModifiedUser = createdUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public User getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(User createdUser) {
        this.createdUser = createdUser;
    }

    public User getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(User lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientNote that = (PatientNote) o;
        return id.equals(that.id) && createDate.equals(that.createDate) && lastModifiedDate.equals(that.lastModifiedDate) && Objects.equals(createdUser, that.createdUser) && Objects.equals(lastModifiedUser, that.lastModifiedUser) && patient.equals(that.patient) && Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, lastModifiedDate, createdUser, lastModifiedUser, patient, note);
    }

    @Override
    public String toString() {
        return "PatientNote{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", createdUser=" + createdUser +
                ", lastModifiedUser=" + lastModifiedUser +
                ", patient=" + patient +
                ", note='" + note + '\'' +
                '}';
    }
}
