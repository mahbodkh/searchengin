package app.engine.search;

import app.engine.search.core.CategoryReader;
import app.engine.search.core.CommandHandler;
import app.engine.search.error.InputException;
import app.engine.search.error.NotFoundCategoryException;
import app.engine.search.error.NotFoundCommandException;
import app.engine.search.service.SearchFactoryService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private Application application;

    @Mock
    private SearchFactoryService factoryService;

    @Mock
    private CommandHandler commandHandler;

    @Mock
    private CategoryReader categoryReader;

    @Before
    public void setUp() {
        application = new Application(factoryService);
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUp() {
        System.setErr(null);
    }

    @Test
    public void test_should_get_system_err() {
        System.err.print("just checking err output");
        assertThat(errContent.toString()).isEqualTo("just checking err output");
    }

    @Test
    public void should_pass_a_directory_as_parameter() {
        try {
            application.run(null);
        } catch (NotFoundCategoryException parameters) {
            assertThat(errContent.toString().trim()).isEqualTo("No category given to index.");
        }
    }

    @Test
    public void should_invoke_factory_for_documents_scanning() {
        doReturn(categoryReader).when(factoryService).getCategoryReader();
        doReturn(commandHandler).when(factoryService).getCommandHandler();
        application.run(new String[]{"my_path"});
        verify(categoryReader).processCategory("my_path");
    }

    @Test
    public void should_invoke_factory_for_command_line_prompt_executor() {
        doReturn(categoryReader).when(factoryService).getCategoryReader();
        doReturn(commandHandler).when(factoryService).getCommandHandler();
        application.run(new String[]{"my_path"});
        verify(commandHandler).execute();
    }
}
