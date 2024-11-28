package view;

import interface_adapter.like.LikeController;
import interface_adapter.like.LikeState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LikeViewTest {

    private LikeController mockController;
    private LikeState mockState;
    private LikeView likeView;

    @BeforeEach
    void setUp() {
        // Mock the dependencies
        mockController = mock(LikeController.class);
        mockState = new LikeState();

        // Initialize the LikeView
        likeView = new LikeView(mockController, mockState);
    }

    @Test
    void testInitialViewSetup() {
        // Check the components exist
        JTextField likerUserIdField = (JTextField) likeView.getComponent(1);
        JTextField likedUserIdField = (JTextField) likeView.getComponent(3);
        JButton likeButton = (JButton) likeView.getComponent(4);
        JLabel resultLabel = (JLabel) likeView.getComponent(5);

        assertNotNull(likerUserIdField, "Liker User ID field should be initialized.");
        assertNotNull(likedUserIdField, "Liked User ID field should be initialized.");
        assertNotNull(likeButton, "Like button should be initialized.");
        assertNotNull(resultLabel, "Result label should be initialized.");
    }

    @Test
    void testLikeAction_SuccessfulLike() {
        // Simulate user input
        JTextField likerUserIdField = (JTextField) likeView.getComponent(1);
        JTextField likedUserIdField = (JTextField) likeView.getComponent(3);
        likerUserIdField.setText("user1");
        likedUserIdField.setText("user2");

        // Click the like button
        JButton likeButton = (JButton) likeView.getComponent(4);
        likeButton.doClick();

        // Verify the controller was called with the correct input
        verify(mockController).likeUser("user1", "user2");
    }

    @Test
    void testLikeAction_InvalidInput() {
        // Simulate invalid user input (liker and liked IDs are the same)
        JTextField likerUserIdField = (JTextField) likeView.getComponent(1);
        JTextField likedUserIdField = (JTextField) likeView.getComponent(3);
        likerUserIdField.setText("user1");
        likedUserIdField.setText("user1");

        // Click the like button
        JButton likeButton = (JButton) likeView.getComponent(4);
        likeButton.doClick();

        // Verify no interaction with the controller
        verifyNoInteractions(mockController);

        // Check the result label shows the appropriate error message
        JLabel resultLabel = (JLabel) likeView.getComponent(5);
        assertEquals("Users cannot like themselves.", resultLabel.getText());
    }

    @Test
    void testStateChangeUpdatesView() {
        // Simulate a state change
        mockState.setUserMessage("Like successful!");

        // Verify the result label is updated
        JLabel resultLabel = (JLabel) likeView.getComponent(5);
        assertEquals("Like successful!", resultLabel.getText());
    }

    @Test
    void testPropertyChangeListenerRegistered() {
        PropertyChangeListener[] listeners = mockState.getPropertyChangeListeners();
        assertEquals(1, listeners.length, "The LikeView should register exactly one listener.");
        assertSame(likeView, listeners[0], "The registered listener should be the LikeView instance.");
    }
}

