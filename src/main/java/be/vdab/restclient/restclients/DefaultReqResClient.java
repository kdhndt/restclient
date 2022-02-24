package be.vdab.restclient.restclients;

import be.vdab.restclient.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
public class DefaultReqResClient implements ReqResClient {
    private final WebClient client;
    private final String userURI;

    //@Value met variable expression verwijst naar de URI/URL in app.properties
    public DefaultReqResClient(WebClient.Builder builder, @Value("${reqres.user}") String userURI) {
        //Spring maakt altijd een WebClient.Builder bean als je de Reactive Web dependency installeert, daarmee maak je een WebClient
        client = builder.build();
        this.userURI = userURI;
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            return Optional.of(client.get()
                    //URI template, waarde voor de {id} parameter in de template
                    .uri(userURI, id)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block());
        } catch (WebClientResponseException.NotFound ex) {
            return Optional.empty();
        }
    }
}
