package home.kirill.scheduledrestapi.service;

import home.kirill.scheduledrestapi.entity.dto.Client;
import home.kirill.scheduledrestapi.entity.dto.ClientNote;

import java.util.List;

public interface ITaskService {
    Client getClient(String clientGUID);

    List<ClientNote> getClientNotes(String agency, String clientGUID);
}
