package app.engine.search.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class CategoryReaderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private CategoryReader categoryReader;

    @Mock
    private TextProcessor textProcessor;

    @Mock
    private Consumer<? super String> execution;

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        categoryReader = new CategoryReader(textProcessor);
    }

    @After
    public void cleanUp() {
        System.setOut(null);
    }

    @Test
    public void test_should_get_system_out() {
        System.out.print("hello");
        assertThat(outContent.toString()).isEqualTo("hello");
    }

    @Test
    public void should_scan_all_files_in_directory() {
        doReturn(execution).when(textProcessor).execute(any());
        categoryReader.processCategory("./src/test/resources/test");
        verify(textProcessor).execute("sampleCatTest00.txt");
    }

    @Test
    public void should_get_number_of_files_in_directory_via_console() {
        doReturn(execution).when(textProcessor).execute(any());
        categoryReader.processCategory("./src/test/resources/test");
        assertThat(outContent.toString().trim()).isEqualTo("1 files read in directory ./src/test/resources/test");
    }

}
