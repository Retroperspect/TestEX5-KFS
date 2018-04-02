package testex.jokefetching;

import org.mockito.Mock;
import testex.JokeException;
import testex.jokefetching.IJokeFetcher;
import testex.jokefetching.Tambal;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FetcherFactory implements IFetcherFactory {

    private final List<String> availableTypes = Arrays.asList("EduJoke", "ChuckNorris", "Moma", "Tambal");

    @Override
    public List<String> getAvailableTypes(){ return availableTypes;}

    @Override
    public List<IJokeFetcher> getJokeFetchers(String jokesToFetch) {
        String[] tokens = jokesToFetch.split(",");
        List<IJokeFetcher> listOfJokes = Arrays.asList();
        for (String token: tokens) {
            if (token == "EduJoke")
            {
                listOfJokes.add(new EduJoke());
            }
            else if (token == "ChuckNorris")
            {
                listOfJokes.add(new ChuckNorris());
            }
            else if (token == "Moma")
            {
                listOfJokes.add(new Moma());
            }
            else if (token == "Tambal")
            {
                listOfJokes.add(new Tambal());
            }
        }
        return listOfJokes;
    }
}
