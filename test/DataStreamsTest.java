import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataStreamsTest {

    DataStreams data;

    @BeforeEach
    void setUp()
    {
        data = new DataStreams();
    }

    @Test
    void testConstructor()
    {
        assertNotNull(data.getChooser());
        assertFalse(data.hasSelectedFile());
    }

    @Test
    void testLineFiltering()
    {
        List<String> exampleInput = Arrays.asList("Example text 1", "Example text 2", "Example text 3");

        List<String> result = data.filterLines(exampleInput, "example");
        assertEquals(3, result.size());
        assertEquals("Example text 1", result.get(0));
        assertEquals("Example text 2", result.get(1));
        assertEquals("Example text 3", result.get(2));

        result = data.filterLines(exampleInput, "1");
        assertEquals(1, result.size());
        assertEquals("Example text 1", result.get(0));

        result = data.filterLines(exampleInput, "Java");
        assertTrue(result.isEmpty());
    }

    @Test
    void testClearProgram()
    {
        data.setSelectedFile(new File("file.txt"));
        data.setOriginalStreamResult(new ArrayList<>(Arrays.asList("Example 1", "Example 2")));
        data.setFileNameTF(new JTextField("Example"));
        data.setSearchStringTF(new JTextField("Example"));
        data.setOriginalFileTA(new JTextArea("Example"));
        data.setFilteredFileTA(new JTextArea("Example"));
        data.setSearchFileBtn(new JButton());
        data.getSearchFileBtn().setEnabled(true);

        data.clearProgram();

        assertNull(data.getSelectedFile());
        assertTrue(data.getOriginalStreamResult().isEmpty());
        assertEquals("", data.getFileNameTF().getText());
        assertEquals("", data.getSearchStringTF().getText());
        assertEquals("", data.getOriginalFileTA().getText());
        assertEquals("", data.getFilteredFileTA().getText());
        assertFalse(data.getSearchFileBtn().isEnabled());
    }
}