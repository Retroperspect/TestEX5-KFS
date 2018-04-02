package net.sf.javaanpr.test;

import net.sf.javaanpr.imageanalysis.CarSnapshot;
import net.sf.javaanpr.intelligence.Intelligence;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.internal.matchers.Equals;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RecognitionAllIT {

    private File file;

    @Parameterized.Parameters
    public static File[] data() {
        File snapshotDir = new File("src/test/resources/snapshots");
        File[] snapshots = snapshotDir.listFiles();
        return snapshots;
    }

    public RecognitionAllIT(File file)
    {
        this.file = file;
    }

    @Test
    public void RecognitionAllITTest() throws IOException, ParserConfigurationException, SAXException {
        String snapshotDirPath = "src/test/resources/snapshots";
        String resultsPath = "src/test/resources/results.properties";
        InputStream resultsStream = new FileInputStream(new File(resultsPath));

        Properties properties = new Properties();
        properties.load(resultsStream);
        resultsStream.close();
        assertTrue(properties.size() > 0);

        File snapshotDir = new File(snapshotDirPath);
        File[] snapshots = snapshotDir.listFiles();
        assertNotNull(snapshots);
        assertTrue(snapshots.length > 0);

        Intelligence intel = new Intelligence();
        assertNotNull(intel);
        CarSnapshot carSnap = new CarSnapshot(new FileInputStream(file));
        assertNotNull("carSnap is null", carSnap);
        assertNotNull("carSnap.image is null", carSnap.getImage());

        String snapName = file.getName();
        String plateCorrect = properties.getProperty(snapName);
        assertNotNull(plateCorrect);

        String numberPlate = intel.recognize(carSnap, false);
        assertThat(numberPlate, equalTo(plateCorrect));
        //assertEquals(numberPlate,plateCorrect);
        //assertTrue(numberPlate.equals(plateCorrect));
        carSnap.close();
    }

}
