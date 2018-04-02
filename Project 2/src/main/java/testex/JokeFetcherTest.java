package testex;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import testex.jokefetching.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

class JokeFetcherTest {

    private JokeFetcher jokeFetcher;

    @Mock IDateFormatter IDF;

    @Mock Date time;

    @Mock JokeFetcher joke;

    @Mock IJokeFetcher Ijoke;

    @Mock Jokes jokes;

    @Mock EduJoke eduJoke;

    @Mock ChuckNorris chuckNorris;

    @Mock Moma moma;

    @Mock Tambal tambal;

    @Mock IFetcherFactory factory;

    private final List<String> availableTypes = Arrays.asList("eduprog","chucknorris","moma","tambal");

    @Before
    public void setup() {
        List<IJokeFetcher> fetchers = Arrays.asList(eduJoke, chuckNorris,moma,tambal);
        when(factory.getJokeFetchers("EduJoke,ChuckNorris,Moma,Tambal")).thenReturn(fetchers);
        List<String> types = Arrays.asList("EduJoke","ChuckNorris","Moma","Tambal");
        when(factory.getAvailableTypes()).thenReturn(types);
        jokeFetcher = new JokeFetcher (factory, IDF);
    }

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAvailableTypes() throws JokeException {
        JokeFetcher JF = new JokeFetcher(factory,IDF);
        assertThat(JF.getAvailableTypes(), equalTo(availableTypes));
    }

    @Test
    void isStringValid() {
        JokeFetcher JF = new JokeFetcher(factory,IDF);
        assertThat(JF.isStringValid(availableTypes.get(0)),is(true));
    }

    @Test
    void getJokes() throws JokeException {
        JokeFetcher JF = new JokeFetcher(factory,IDF);
        JF.getJokes("chucknorris","Europe/Copenhagen");
        verify(IDF).getFormattedDate(eq("Europe/Copenhagen"),anyObject());
        //verify(jokes).getJokes();
    }

    @Test
    void testFetcher() throws JokeException
    {
        JokeFetcher JF = new JokeFetcher(factory,IDF);
        JF.getJokes("chucknorris","Europe/Copenhagen");
        verify(factory).getJokeFetchers(anyString());
        given(factory.getJokeFetchers(anyString())).willReturn(Arrays.asList(eduJoke,chuckNorris,moma,tambal));
        //given(eduJoke.getJoke()).willReturn(joke())
        assertThat(factory.getJokeFetchers(anyString()).size(),is(4));
    }

    @Test
    void FirstSimpleTest() throws JokeException {
        JokeFetcher JF = new JokeFetcher(factory,IDF);
        JF.getJokes("chucknorris","Europe/Copenhagen");
        given(IDF.getFormattedDate(eq("Europe/Copenhagen"), anyObject())).willReturn("17 feb. 2018 10:56 AM");
        //verify(joke).getJokes(eq("chucknorris"),eq("Europe/Copenhagen"),eq(time),eq(IDF)).timeZoneString.equals(anyString());
        //assertThat(joke.getJokes("chucknorris","Europe/Copenhagen").timeZoneString,is(""));
    }
}