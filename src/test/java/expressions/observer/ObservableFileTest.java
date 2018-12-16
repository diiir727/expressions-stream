package expressions.observer;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;

import static org.mockito.Mockito.*;

public class ObservableFileTest {

    @Test
    public void testSeeChanges() throws InterruptedException {
        File mockFile = mock(File.class);
        Observer mockObserver = mock(Observer.class);

        when(mockFile.lastModified()).thenReturn(100L).thenReturn(200L);

        ObservableFile observableFile = new ObservableFile(mockFile, 1);
        observableFile.add(mockObserver);
        observableFile.start();
        Thread.sleep(500);

        verify(mockObserver, times(2)).handleEvent();
    }

}
