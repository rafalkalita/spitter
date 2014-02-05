package com.rafalkalita.spitter.mvc;

import com.rafalkalita.spitter.model.Spitter;
import com.rafalkalita.spitter.model.Spittle;
import com.rafalkalita.spitter.service.SpitterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

/**
 * User: rafalkalita
 * Date: 30/01/2014
 * Time: 18:26
 */
@RunWith(MockitoJUnitRunner.class)
public class SpitterControllerTest {

    @InjectMocks
    SpitterController instance;

    @Mock
    private SpitterService service;

    @Mock
    AuthenticationManager authenticationManager;

    private ExtendedModelMap model = new ExtendedModelMap();

    @Test
    public void producesSpittlesForSpitter() {

        // given
        Spitter spitter = aSpitter();
        List<Spittle> spittles = listOfTwoSpittles();

        given(service.getSpitterByUsername(spitter.getUsername())).willReturn(spitter);
        given(service.getSpittlesForSpitter(spitter.getUsername())).willReturn(spittles);

        // when
        String view = instance.displaySpittlesForSpitter(spitter.getUsername(), model);

        // then
        assertEquals("spittles/list", view);
        assertEquals(spitter, model.get("spitter"));
        assertEquals(spittles, model.get("spittleList"));
    }

    @Test
    public void displaysCreateNewSpitterForm() {

        // given

        // when
        String view = instance.showNewSpitterForm(model);

        // then
        assertEquals("spitters/edit", view);
        assertTrue(model.containsAttribute("spitter"));
        assertNotNull((Spitter)model.get("spitter"));
    }

    @Test
    public void processNewSpitterForm() {

        // given
        Spitter spitter = aSpitter();
        BindingResult mockedBindingResult = mock(BindingResult.class);
        HttpServletRequest mockedServletRequest = mock(HttpServletRequest.class);

        // when
        String view = instance.processNewSpitterForm(spitter, mockedBindingResult, mockedServletRequest);

        // then
        verify(service).saveSpitter(spitter);
        verifyZeroInteractions(service);
        verify(authenticationManager).authenticate(isA(UsernamePasswordAuthenticationToken.class));

        assertEquals("redirect:/spitters/" + spitter.getUsername() + "/spittles", view);
    }

    @Test
    public void processNewSpitterFormWithErrors() {

        // given
        BindingResult mockedBindingResult = mock(BindingResult.class);
        given(mockedBindingResult.hasErrors()).willReturn(true);
        HttpServletRequest mockedServletRequest = mock(HttpServletRequest.class);

        // when
        String view = instance.processNewSpitterForm(aSpitter(), mockedBindingResult, mockedServletRequest);

        // then
        verifyZeroInteractions(service);
        assertEquals("spitters/edit", view);
    }

    private List<Spittle> listOfTwoSpittles() {

        List<Spittle> spittles = new ArrayList<Spittle>();

        Spittle spittle1 = new Spittle();
        spittle1.setMessage("message1");
        Spittle spittle2 = new Spittle();
        spittle2.setMessage("message2");

        spittles.add(spittle1);
        spittles.add(spittle2);

        return spittles;
    }

    private Spitter aSpitter() {

        Spitter spitter = new Spitter();

        spitter.setId(1L);
        spitter.setUsername("John");
        spitter.setFullName("John Downs");
        spitter.setPassword("pass");

        return spitter;
    }
}
