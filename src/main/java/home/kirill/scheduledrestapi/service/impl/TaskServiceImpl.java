package home.kirill.scheduledrestapi.service.impl;

import home.kirill.scheduledrestapi.entity.dto.Client;
import home.kirill.scheduledrestapi.entity.dto.ClientNote;
import home.kirill.scheduledrestapi.prop.PropertiesReader;
import home.kirill.scheduledrestapi.service.ITaskService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class TaskServiceImpl implements ITaskService {

    private static final Logger logger = Logger.getLogger(TaskServiceImpl.class.getName());

    private WebClient webClient;

    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(10);

    public TaskServiceImpl() {

        try {
            String baseUrl = PropertiesReader.getString("base_url");
            if (StringUtils.isNotBlank(baseUrl)) {
                this.webClient = WebClient.create(baseUrl);
            } else {
                logger.error("В application.properties не удалось получить значение base_url");
                this.webClient = null;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    @Override
    public Client getClient(String clientGUID) {

        String uriClients;

        if (webClient == null) {
            return null;
        }

        try {
            uriClients = PropertiesReader.getString("client_uri");
        } catch (Exception e) {
            logger.error("В application.properties не удалось получить значение client_uri");
            return null;
        }

        return webClient
                .post()
                .uri(uriClients)
                .bodyValue(Collections.singletonMap("client_guid", clientGUID))
                .retrieve()
                .bodyToMono(Client.class)
                .doOnError(error -> logger.error("Ошибка при выполнении запроса " + uriClients,
                        error))
                .block(REQUEST_TIMEOUT);

    }

    @Override
    public List<ClientNote> getClientNotes(String agency, String clientGUID) throws NullPointerException {

        String uriNotes;

        if (webClient == null) {
            return null;
        }

        try {
            uriNotes = PropertiesReader.getString("note_uri");
        } catch (Exception e) {
            logger.error("В application.properties не удалось получить значение note_uri");
            return null;
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("agency", agency);
        params.put("client_guid", clientGUID);

        return Objects.requireNonNull(webClient
                        .post()
                        .uri(uriNotes)
                        .bodyValue(params)
                        .retrieve()
                        .toEntityList(ClientNote.class)
                        .doOnError(error -> logger.error("Ошибка при выполнении запроса " + uriNotes,
                                error))
                        .block(REQUEST_TIMEOUT))
                .getBody();
    }
}
