# TestEX5-KFS
> by Kristian Flejsborg Sørensen (cph-kf96)

## intro
This is the midterm assignment of the Test cause, which consist of 2 mandatory projects and 1 optional projects, which will be mandatory later down the road, so finishing it would be preferable.   

## project 1
The first project is testing out the `JavaANPR - Automatic
Number Plate Recognition System for Java` project, which is a program who's purpose is to scan pictures and read numberplates, and then pinpoint what is on the numberplate.   

### purpose of the test
The original test focuses on 2 test cases, one aim's to verify a single plate test works as intended, and another aims to verify all plates in a collection. Problem is from personal testing the single plate test is hard coded to test for one specific plate, and can't be applied to other cases, so a confirmation bias is present, and the second test works more dynamically and test all cases, and makes a checklist for all correct answers out of the sum total, the problem is if even one case fail we should expect a failure of the system, but the test shows green as the test did what was asked of the test, creating another false positiv scenario.   
After some personal Testing other than tweaking the initial test cases to see how they tick, I found the system have 2 failure states present, one case happens when the system translate the image to a number that is incorrect compared to the expected value, and another case where the system doesn't find any value where there is one.

### Parameterized Tests
Making hardcoded test cases for each individual test to get insight in which fails and why would take to long, and parameterized tests helps with a data driven approach as we have hard data both in picture form and as properties in expected values, with this we can make a singlegular test case that can be run on all the data, both reducing the coding needed and making the testing dynamic enough to handle future data sets of the same kind.

### Data Driven Testing
The problem with not doing Data Driven testing is much easier to explain, as it's essentially hardcoded static testing, so any future alteration to the code requires refactoring the previous test cases, to ensure they work with the current state of the system, therefor Data Driven Testing is a logical step to improve testing, as by making the test able to handle ever changing data from a out of the code source, allows for a stronger foundation for testing.

### Unit test or JUnit test
Unit Test are specifically test on individual components, thier behaviour on the singular level, to confirm thier intended role is fulfilled. A JUnit is a framework for Test Driven Development, and is designed to help with the TDD model, but in the case of it being a unit test or a junit test it's a "junit test" as in it uses the framework but still is a unit test in essence.   
The problem I find is my lack of knowledge to explain the exact differences given it's a parameterized unit test, and I don't know the implication this may have.

### Hamcrest
the implementation of the matcher was simple, as I only needed to add the dependency of it and import it into my project, with this I could change my final assert into a hamcrest one, where the test results gave the same information, but in a more structured way that allowed me to find the two previously mentioned failure cases of wrong result or null result.

## Project 2
The second project is presented without any test cases, and a ugly mess of code to test, the purpose is to make untestable code testable and explain the process.

### Making the code testable
the original code violated Single Responsibility Principle and had many hidden dependencies, which made it practically impossible to make test cases for various behaviours in the code. To fix this it was needed to refactor the code and add dependency injections and Interfaces, this allowed for the use of mockito to make System Under Testing cases allowing for easier behaviour testing on the newly refactored code. It was also nessecary to refactor the private jokes functions into a factory pattern system to allow for polymorphism, which in turn allowed more use of mockito.

### Tests
as of now my current test cases looks as follows.
```
@Test
    void getAvailableTypes() throws JokeException {
        JokeFetcher JF = new JokeFetcher(factory,IDF);
        assertThat(JF.getAvailableTypes(), equalTo(availableTypes));
    }
```
```
    @Test
    void isStringValid() {
        JokeFetcher JF = new JokeFetcher(factory,IDF);
        assertThat(JF.isStringValid(availableTypes.get(0)),is(true));
    }
```
```
    @Test
    void getJokes() throws JokeException {
        JokeFetcher JF = new JokeFetcher(factory,IDF);
        JF.getJokes("chucknorris","Europe/Copenhagen");
        verify(IDF).getFormattedDate(eq("Europe/Copenhagen"),anyObject());
        //verify(jokes).getJokes();
    }
```
```
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
```
```
    @Test
    void FirstSimpleTest() throws JokeException {
        JokeFetcher JF = new JokeFetcher(factory,IDF);
        JF.getJokes("chucknorris","Europe/Copenhagen");
        given(IDF.getFormattedDate(eq("Europe/Copenhagen"), anyObject())).willReturn("17 feb. 2018 10:56 AM");
        //verify(joke).getJokes(eq("chucknorris"),eq("Europe/Copenhagen"),eq(time),eq(IDF)).timeZoneString.equals(anyString());
        //assertThat(joke.getJokes("chucknorris","Europe/Copenhagen").timeZoneString,is(""));
    }
```
and their results   
![](https://i.gyazo.com/feec63bd5049bde805b15490f513c645.png)   
### Testers Tools
Junit and Hamcrest are tools designed to help tester make easilly structured tests and retrieve the desired information from said tests with ease. Mockito allows for the flexibility of testing SUTs `System Under Testing` while several dependencies are missing in action. Jacoco allows testers to gain a overview of what code is being tested and what code isn't through a full test coverage run.   
all these tools allow for greater testing with the correct use.

### Mockito
