package home.kirill.scheduledrestapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "company_user")
@GenericGenerator(name = "persistGenerator", strategy = "native")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "persistGenerator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(name = "first_name", nullable = false)
    private String firsName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    public User() {
    }

    public User(String login, String firsName, String lastName, String middleName) {
        this.login = login;
        this.firsName = firsName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public User(String login, String firsName, String lastName) {
        this.login = login;
        this.firsName = firsName;
        this.lastName = lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && login.equals(user.login) && firsName.equals(user.firsName) && lastName.equals(user.lastName) && Objects.equals(middleName, user.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, firsName, lastName, middleName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firsName='" + firsName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                '}';
    }
}
