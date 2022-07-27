package br.com.cleo.AluraAPi.controller;

import static br.com.cleo.AluraAPi.util.LanguageCreator.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.cleo.AluraAPi.form.LanguageForm;
import br.com.cleo.AluraAPi.model.Language;
import br.com.cleo.AluraAPi.repository.LanguageRepository;

import br.com.cleo.AluraAPi.utils.UpdateObjectValues;

@ExtendWith(SpringExtension.class)
public class LanguageControllerTests {

    @InjectMocks
    LanguageController languageController;

    @Mock
    LanguageRepository languageRepository;

    @Mock
    UpdateObjectValues updateObjectValues;

    @BeforeEach
    public void setUp() {
        List<Language> languageList = List.of(LanguageValid());

    }

    @Test
    public void testListAllLanguage() {
        when(languageRepository.findAll(Sort.by("ranking"))).thenReturn(List.of(LanguageValid()));
        ResponseEntity<List<Language>> response = languageController.listAllLanguage();
        assertEquals(1, response.getBody().size());
        assertEquals("1", response.getBody().get(0).getId());
        assertEquals("Java", response.getBody().get(0).getName());
        assertEquals("Java is a programming language",
                response.getBody().get(0).getDescription());
        assertEquals("https://www.w3schools.com/images/java.jpg",
                response.getBody().get(0).getImage());

    }

    @Test
    public void testFindLanguageById() {
        when(languageRepository.findByIdOrThrow(ArgumentMatchers.anyString()))
                .thenReturn(LanguageValid());
        ResponseEntity<Language> response = languageController.findLanguageById("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Java", response.getBody().getName());
        assertEquals("Java is a programming language",
                response.getBody().getDescription());
        assertEquals("https://www.w3schools.com/images/java.jpg", response.getBody().getImage());
    }

    @Test
    public void testCreateLanguage() {
        when(languageRepository.save(ArgumentMatchers.any(Language.class)))
                .thenReturn(LanguageValid());
        ResponseEntity<Language> response = languageController.createLanguage(LanguageToBeSave());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
        assertEquals("Java", response.getBody().getName());
        assertEquals("Java is a programming language",
                response.getBody().getDescription());
        assertEquals("https://www.w3schools.com/images/java.jpg", response.getBody().getImage());
    }

    @Test
    public void testUpdateLanguage() {
        when(languageRepository.findByIdOrThrow(ArgumentMatchers.anyString()))
                .thenReturn(LanguageValid());
        when(languageRepository.save(ArgumentMatchers.any()))
                .thenReturn(LanguageToBeUpdate());
        ResponseEntity<Language> response = languageController.updateLanguage("1", new LanguageForm());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("1", response.getBody().getId());
        assertEquals("Java1", response.getBody().getName());
        assertEquals("Java is a programming language",
                response.getBody().getDescription());
        assertEquals("https://www.w3schools.com/images/java.jpg",
                response.getBody().getImage());
    }

    @Test
    public void testDeleteLanguage() {
        when(languageRepository.findByIdOrThrow(ArgumentMatchers.anyString()))
                .thenReturn(LanguageValid());
        ResponseEntity<String> response = languageController.deletLanguage("1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
