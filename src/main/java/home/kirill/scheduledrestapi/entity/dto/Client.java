package home.kirill.scheduledrestapi.entity.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class Client {

    private String agency;

    private String client_guid;

    public Client(String agency, String client_guid) {
        this.agency = agency;
        this.client_guid = client_guid;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getClient_guid() {
        return client_guid;
    }

    public void setClient_guid(String client_guid) {
        this.client_guid = client_guid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(agency, client.agency) && Objects.equals(client_guid, client.client_guid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agency, client_guid);
    }

    @Override
    public String toString() {
        return "Client{" +
                "agency='" + agency + '\'' +
                ", client_guid='" + client_guid + '\'' +
                '}';
    }
}
