package interface_adapter.like;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The state class for the Like use case.
 * Responsible for maintaining and notifying changes in state.
 */
public class LikeState {

    private final PropertyChangeSupport support;
    private String userMessage; // Message to be displayed in the view.

    public LikeState() {
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Adds a listener for property changes.
     *
     * @param listener the property change listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a listener for property changes.
     *
     * @param listener the property change listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    /**
     * Gets the current user message.
     *
     * @return the user message
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * Sets a new user message and notifies listeners.
     *
     * @param newMessage the new user message
     */
    public void setUserMessage(String newMessage) {
        String oldMessage = this.userMessage;
        this.userMessage = newMessage;
        support.firePropertyChange("userMessage", oldMessage, newMessage);
    }
}
