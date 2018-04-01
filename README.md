# TestEX5-KFS
> by Kristian Flejsborg Sørensen (cph-kf96)

## intro
This is hte midterm assignment of the Test cause, which consist of 2 mandatory projects and 1 optional projects, which will be mandatory later down the road, so finishing it would be preferable.   

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
