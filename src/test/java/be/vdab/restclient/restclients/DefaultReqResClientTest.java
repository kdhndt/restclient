package be.vdab.restclient.restclients;

import be.vdab.restclient.dto.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DefaultReqResClientTest {
    private final DefaultReqResClient client;

    DefaultReqResClientTest(DefaultReqResClient client) {
        this.client = client;
    }

    @Test
    void findBestaandeUser() {
        assertThat(client.findById(1)).hasValueSatisfying(user -> assertThat(user.data().id()).isOne());
        //zelf toegevoegd
        assertThat(client.findById(1)).hasValueSatisfying(user -> assertThat(user.data().firstName()).isEqualTo("George"));
    }

    @Test
    void findOnbestaandeUser() {
        assertThat(client.findById(-1)).isEmpty();
    }
}