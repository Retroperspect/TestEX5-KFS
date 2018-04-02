package testex;

import com.jayway.restassured.response.ExtractableResponse;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import testex.jokefetching.FetcherFactory;
import testex.jokefetching.IFetcherFactory;
import testex.jokefetching.IJokeFetcher;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;

import java.security.PublicKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static com.jayway.restassured.RestAssured.given;

public class JokeFetcher {

  private final List<String> availableTypes = Arrays.asList("eduprog","chucknorris","moma","tambal");

  private IDateFormatter dateFormatter;

  private IFetcherFactory factory;

  public JokeFetcher(IFetcherFactory IFF, IDateFormatter DF)
  {
    factory = IFF;
    dateFormatter = DF;
  }

  public List<String> getAvailableTypes(){
    return availableTypes;
  }

  boolean isStringValid(String jokeTokens){
    String[] tokens = jokeTokens.split(",");
      for(String token: tokens){
      if(!availableTypes.contains(token)){
        return false;
      }
    }
    return true;
  }

  public Jokes getJokes(String jokesToFetch, String timeZone) throws JokeException {
    checkIfValidToken(jokesToFetch);
    Jokes jokes = new Jokes();
    for (IJokeFetcher fetcher : factory.getJokeFetchers(jokesToFetch)) {
      jokes.addJoke(fetcher.getJoke());
    }
    String tzString = dateFormatter.getFormattedDate(timeZone, new Date());
    jokes.setTimeZoneString(tzString);
    return jokes;
  }

  private void checkIfValidToken(String jokesToFetch) {
  }
}
