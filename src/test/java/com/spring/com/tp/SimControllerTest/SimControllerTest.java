package com.spring.com.tp.SimControllerTest;

import com.spring.com.tp.controller.SimController;
import com.spring.com.tp.controller.dto.SimInput;
import com.spring.com.tp.model.Sim;
import com.spring.com.tp.model.Strategy.ProfesionesEnum;
import com.spring.com.tp.services.SimService;
import org.assertj.core.internal.Classes;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SimControllerTest {

    @Autowired
    @LocalServerPort
    private int port = 8080;

    @Autowired
    private SimService simService;

    private Sim simInitialized;

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    HttpHeaders httpHeaders = new HttpHeaders();

    @Before
    public void simAInitialized()throws JSONException{
        SimInput sim = new SimInput("fdsh","a123xx","1999/01/21",
                Arrays.asList("gotham", "lie to me"),
                Arrays.asList("9780007103072", "9786070709548", "9788472095328"),
                ProfesionesEnum.POLICIA,200.00
        );

        HttpEntity<SimInput> entityPost = new HttpEntity<SimInput>(sim, httpHeaders);
        ResponseEntity<Sim> responsePost = testRestTemplate.exchange(
                createURLWithPort("/sims"),
                HttpMethod.POST, entityPost, Sim.class);

        HttpEntity<Sim> entityGet = new HttpEntity<Sim>(null, httpHeaders);
        ResponseEntity<Sim> responseGet = testRestTemplate.exchange(
                createURLWithPort("/sims/a123xx"),
                HttpMethod.GET, entityGet, Sim.class);

        this.simInitialized = responseGet.getBody();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }


//Me esta devolviendo SimOutpost por eso no me da el dni
    @Test
    public void createSim() throws JSONException {

        SimInput sim = new SimInput("fdsh","a123xx","1999/01/21",
                Arrays.asList("gotham", "lie to me"),
                Arrays.asList("9780007103072", "9786070709548", "9788472095328"),
                ProfesionesEnum.MEDICO,200.00
                );

        HttpEntity<SimInput> entity = new HttpEntity<SimInput>(sim, httpHeaders);

        ResponseEntity<Sim> response = testRestTemplate.exchange(
                createURLWithPort("/sims"),
                HttpMethod.POST, entity, Sim.class);

        String actual = response.getBody().getName();

        Assert.assertEquals("fdsh", actual );

    }

    @Test
    public void getSim() throws JSONException{

        HttpEntity<Sim> simHttpEntity = new HttpEntity<Sim>(null, this.httpHeaders);


    }

    @Test
    public void putSim() throws JSONException{
        simInitialized.setTipoProfesion(ProfesionesEnum.MEDICO);

        HttpEntity<Sim > entityPut = new HttpEntity<Sim>(simInitialized, httpHeaders);
        ResponseEntity<Sim> responsePut = testRestTemplate.exchange(
                createURLWithPort("/sims"),HttpMethod.PUT, entityPut, Sim.class
        );

        ProfesionesEnum actual = responsePut.getBody().getTipoProfesion();
        Assert.assertEquals(ProfesionesEnum.MEDICO, actual);
    }

    @Test
    public void strategyMoneyTest() throws JSONException {

        HttpEntity<Sim > entity1 = new HttpEntity<Sim>(simInitialized, httpHeaders);

        ResponseEntity<Sim> response1 = testRestTemplate.exchange(
                createURLWithPort("/sims/a123xx/rutina-de-trabajo"),
                HttpMethod.GET, entity1, Sim.class);

        Optional<Double> actual = Optional.of(response1.getBody().getDinero());
        Assert.assertEquals(Optional.of(1700.00).get(), actual.get());

    }
}
