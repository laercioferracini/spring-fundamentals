package br.com.ferracini.repository;

import br.com.ferracini.model.Speaker;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.event.annotation.AfterTestClass;

import static java.text.MessageFormat.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName(value = "Speaker repository integration test")
@DataJpaTest
class SpeakerRepositoryIntegrationTest {

    @Autowired
    private SpeakerRepository subject;

    @AfterTestClass
    void tearDown() {
        subject.deleteAll();
    }

    @Test
    @DisplayName(value = "should save and fetch speaker")
    void shouldSaveAndFetchSpeaker() {
        long expectedId = 1L;
        var speaker = new Speaker("Freddy", "Mercury", "Test company", "Test title", "Test bio");

        var expectedSpeaker = subject.saveAndFlush(speaker);

        assertThat(expectedSpeaker, is(speaker));
        assertEquals(expectedId, expectedSpeaker.getSpeakerId(), format("Speaker ids are not equals: expected:{0}, received:{1}", expectedId, expectedSpeaker.getSpeakerId()));
    }
}
