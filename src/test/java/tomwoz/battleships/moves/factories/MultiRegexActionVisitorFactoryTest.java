package tomwoz.battleships.moves.factories;

import org.junit.Test;
import org.mockito.Mockito;
import tomwoz.battleships.api.IActionVisitor;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MultiRegexActionVisitorFactoryTest {

    public static final String TEST_INPUT = "TESTSTRING";

    @Test
    public void testCanBuild() throws Exception {

        IRegexActionVisitorFactory mockedVisitor = mock(IRegexActionVisitorFactory.class);
        when(mockedVisitor.canBuild(TEST_INPUT)).thenReturn(true);

        IRegexActionVisitorFactory mockedVisitor2 = mock(IRegexActionVisitorFactory.class);
        when(mockedVisitor.canBuild(TEST_INPUT)).thenReturn(true);

        final MultiRegexActionVisitorFactory factory = new MultiRegexActionVisitorFactory(Arrays.asList(mockedVisitor, mockedVisitor2));

        assertTrue(factory.canBuild(TEST_INPUT));
        verify(mockedVisitor, times(1)).canBuild(TEST_INPUT);
        verify(mockedVisitor2, never()).canBuild(anyString());//Should not be invoked as the first handler returned true
    }

    @Test
    public void testCannotBuild() throws Exception {

        IRegexActionVisitorFactory mockedVisitor = mock(IRegexActionVisitorFactory.class);
        when(mockedVisitor.canBuild(TEST_INPUT)).thenReturn(false);

        IRegexActionVisitorFactory mockedVisitor2 = mock(IRegexActionVisitorFactory.class);
        when(mockedVisitor.canBuild(TEST_INPUT)).thenReturn(false);

        final MultiRegexActionVisitorFactory factory = new MultiRegexActionVisitorFactory(Arrays.asList(mockedVisitor, mockedVisitor2));

        assertFalse(factory.canBuild(TEST_INPUT));
        verify(mockedVisitor, times(1)).canBuild(TEST_INPUT);
        verify(mockedVisitor2, times(1)).canBuild(TEST_INPUT);
    }

    @Test
    public void testFromString() throws Exception {

        final IActionVisitor mockedAction = mock(IActionVisitor.class);

        IRegexActionVisitorFactory mockedVisitor = mock(IRegexActionVisitorFactory.class);
        when(mockedVisitor.canBuild(TEST_INPUT)).thenReturn(true);
        when(mockedVisitor.fromString(TEST_INPUT)).thenReturn(mockedAction);

        IRegexActionVisitorFactory mockedVisitor2 = mock(IRegexActionVisitorFactory.class);
        when(mockedVisitor.canBuild(TEST_INPUT)).thenReturn(true);

        final MultiRegexActionVisitorFactory factory = new MultiRegexActionVisitorFactory(Arrays.asList(mockedVisitor, mockedVisitor2));

        assertEquals(mockedAction, factory.fromString(TEST_INPUT));

        verify(mockedVisitor, times(1)).fromString(TEST_INPUT);
        verify(mockedVisitor2, never()).fromString(anyString());
    }

    @Test
    public void testNullIfBadString() throws Exception {
        IRegexActionVisitorFactory mockedVisitor = mock(IRegexActionVisitorFactory.class);
        when(mockedVisitor.canBuild(TEST_INPUT)).thenReturn(false);

        IRegexActionVisitorFactory mockedVisitor2 = mock(IRegexActionVisitorFactory.class);
        when(mockedVisitor.canBuild(TEST_INPUT)).thenReturn(false);

        final MultiRegexActionVisitorFactory factory = new MultiRegexActionVisitorFactory(Arrays.asList(mockedVisitor, mockedVisitor2));

        assertNull(factory.fromString(TEST_INPUT));

        verify(mockedVisitor, times(1)).canBuild(TEST_INPUT);
        verify(mockedVisitor2, times(1)).canBuild(TEST_INPUT);
        verify(mockedVisitor, never()).fromString(TEST_INPUT);
        verify(mockedVisitor2, never()).fromString(TEST_INPUT);

    }
}