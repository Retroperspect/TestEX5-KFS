# LOG
>by kristian flejsborg Sørensen
## intro
it seems as though this assignment is best made while writing down logs doing project, as such this will stand as a log of my progress performing the various tasks.

## 25-03-2018 16:00 aprox
so far it's been trouble dealing with the IDE, as my limited experience had me on a goose chase trying to figure out how to access this "maven terminal", after some work I managed to figure it out and run the tests succesfully!   
interesting to note, the IDE I use is `IntelliJ` and I seems to have the same access to "build and run" the tests as if I used the command `mvn surefire:test -Dtest=net.sf.javaanpr.test.RecognitionIT` only time will tell if I'm doing something wrong by using the quicker way.   
after succesfully running the tests with the logging code implemented, I find the amount of debugging information overwhelming and proceed to find the debug out from the `intelligenceSingleTest()` as the name suggest it being a much smaller sample size, granted it still spews out this.
```
15:59:12.625 [main] INFO  net.sf.javaanpr.test.RecognitionIT - ####### RUNNING: intelligenceSingleTest ######
15:59:12.807 [main] DEBUG n.s.j.intelligence.parser.Parser - Comparing net.sf.javaanpr.intelligence.RecognizedPlate@4ae33a11 with form slovensko_nova and offset 0.
15:59:12.807 [main] DEBUG n.s.j.intelligence.parser.Parser - Adding FL102GB with required changes 2.5782614.
15:59:12.807 [main] DEBUG n.s.j.intelligence.parser.Parser - Comparing net.sf.javaanpr.intelligence.RecognizedPlate@4ae33a11 with form slovensko_nova and offset 1.
15:59:12.807 [main] DEBUG n.s.j.intelligence.parser.Parser - Adding LM025BD with required changes 0.0.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Comparing net.sf.javaanpr.intelligence.RecognizedPlate@4ae33a11 with form ceskoslovenska_novsia and offset 0.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Adding FLM0258 with required changes 1.234747.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Comparing net.sf.javaanpr.intelligence.RecognizedPlate@4ae33a11 with form ceskoslovenska_novsia and offset 1.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Adding LM02580 with required changes 2.396008.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Comparing net.sf.javaanpr.intelligence.RecognizedPlate@4ae33a11 with form ceska_nova and offset 0.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Adding 5L10258 with required changes 3.9878082.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Comparing net.sf.javaanpr.intelligence.RecognizedPlate@4ae33a11 with form ceska_nova and offset 1.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Adding 0M02580 with required changes 3.774897.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Plate 0 : FL102GB with required changes 2.5782614.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Plate 1 : LM025BD with required changes 0.0.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Plate 2 : FLM0258 with required changes 1.234747.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Plate 3 : LM02580 with required changes 2.396008.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Plate 4 : 5L10258 with required changes 3.9878082.
15:59:12.808 [main] DEBUG n.s.j.intelligence.parser.Parser - Plate 5 : 0M02580 with required changes 3.774897.
```
This doesn't help me much other than saying it tries out a few different plates and attempts to compare templates of different plates with it atleast, I have yet to do the test with failure but I'll do that after this log post.   
looking into the different functions called in `intelligenceSingleTest()` it seems `CarSnapshot` and `Intelligence` are they only classes used in this function, and after the use of the string image to find what it needs, it seems the test itself is hardcoded to test for the value "LM025BD" as seen in the last assert `assertEquals("LM025BD", spz);` this must clearly mean that by changing the picture it uses in the test it breaks at this code excatly.
