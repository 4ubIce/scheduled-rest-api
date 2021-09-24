package home.kirill.scheduledrestapi.entity.dto;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ClientNote {

    private String agency;

    private String logged_user;

    private String client_guid;

    private String comments;

    private Date datetime;

    private Date modified_datetime;

    public ClientNote() {
    }

    public ClientNote(String agency, String logged_user, String client_guid, String comments, Date datetime, Date modified_datetime) {
        this.agency = agency;
        this.logged_user = logged_user;
        this.client_guid = client_guid;
        this.comments = comments;
        this.datetime = datetime;
        this.modified_datetime = modified_datetime;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getLogged_user() {
        return logged_user;
    }

    public void setLogged_user(String logged_user) {
        this.logged_user = logged_user;
    }

    public String getClient_guid() {
        return client_guid;
    }

    public void setClient_guid(String client_guid) {
        this.client_guid = client_guid;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public Date getModified_datetime() {
        return modified_datetime;
    }

    public void setModified_datetime(Date modified_datetime) {
        this.modified_datetime = modified_datetime;
    }

    @Override
    public String toString() {
        return "ClientNote{" +
                "agency='" + agency + '\'' +
                ", logged_user='" + logged_user + '\'' +
                ", client_guid='" + client_guid + '\'' +
                ", comments='" + comments + '\'' +
                ", datetime=" + datetime +
                ", modified_datetime=" + modified_datetime +
                '}';
    }
}
