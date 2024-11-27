package interface_adapter.like;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class LikeStateTest {

    private LikeState likeState;

    @BeforeEach
    void setUp() {
        likeState = new LikeState();
    }

    @Test
    void testInitialState() {
        // Assert the initial state
        assertNull(likeState.getUserMessage());
    }

    @Test
    void testSetUserMessage() {
        // Set a new user message
        String newMessage = "Like action succeeded!";
        likeState.setUserMessage(newMessage);

        // Assert the state is updated
        assertEquals(newMessage, likeState.getUserMessage());
    }

    @Test
    void testPropertyChangeListener_NotifiedOnMessageChange() {
        // Mock listener to capture property change events
        MockPropertyChangeListener mockListener = new MockPropertyChangeListener();
        likeState.addPropertyChangeListener(mockListener);

        // Set a new message
        String oldMessage = null;
        String newMessage = "Like action failed.";
        likeState.setUserMessage(newMessage);

        // Assert the listener received the event
        assertNotNull(mockListener.getLastEvent());
        PropertyChangeEvent event = mockListener.getLastEvent();
        assertEquals("userMessage", event.getPropertyName());
        assertEquals(oldMessage, event.getOldValue());
        assertEquals(newMessage, event.getNewValue());
    }

    @Test
    void testRemovePropertyChangeListener() {
        // Mock listener
        MockPropertyChangeListener mockListener = new MockPropertyChangeListener();
        likeState.addPropertyChangeListener(mockListener);

        // Remove the listener
        likeState.removePropertyChangeListener(mockListener);

        // Set a new message
        likeState.setUserMessage("This will not notify the listener.");

        // Assert no events were captured after removal
        assertNull(mockListener.getLastEvent());
    }
}

/**
 * A simple mock implementation of PropertyChangeListener for testing.
 */
class MockPropertyChangeListener implements PropertyChangeListener {

    private PropertyChangeEvent lastEvent;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.lastEvent = evt;
    }

    public PropertyChangeEvent getLastEvent() {
        return lastEvent;
    }
}
