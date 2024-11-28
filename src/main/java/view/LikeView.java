
package view;

import interface_adapter.like.LikeController;
import interface_adapter.like.LikeState;
import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LikeView extends JPanel implements PropertyChangeListener {

    private final LikeController likeController;
    private final LikeState likeState;
    private ViewManagerModel viewManagerModel; // Add ViewManagerModel for navigation

    private final JTextField likerUserIdField = new JTextField(15);
    private final JTextField likedUserIdField = new JTextField(15);
    private final JLabel resultLabel = new JLabel("");

    public LikeView(LikeController likeController, LikeState likeState) {
        this.likeController = likeController;
        this.likeState = likeState;

        // Register this view as a listener to the state
        likeState.addPropertyChangeListener(this);

        // Set up the layout
        setLayout(new GridLayout(5, 2));

        // Add components
        add(new JLabel("Your User ID:"));
        add(likerUserIdField);

        add(new JLabel("User ID to Like:"));
        add(likedUserIdField);

        JButton likeButton = new JButton("Like");
        add(likeButton);
        add(resultLabel);

        JButton backButton = new JButton("Back"); // Add Back Button for navigation
        add(backButton);

        // Add action listener for the Like button
        likeButton.addActionListener(e -> handleLikeAction());

        // Add action listener for the Back button
        backButton.addActionListener(e -> handleBackAction());
    }

    void handleLikeAction() {
        String likerUserId = likerUserIdField.getText();
        String likedUserId = likedUserIdField.getText();

        try {
            // Validation
            if (likerUserId == null || likerUserId.isEmpty()) {
                throw new IllegalArgumentException("Liker user ID cannot be null or empty.");
            }
            if (likedUserId == null || likedUserId.isEmpty()) {
                throw new IllegalArgumentException("Liked user ID cannot be null or empty.");
            }
            if (likerUserId.equals(likedUserId)) {
                throw new IllegalArgumentException("Users cannot like themselves.");
            }

            // Delegate the "Like" action to the controller
            likeController.likeUser(likerUserId, likedUserId);
        } catch (IllegalArgumentException ex) {
            // Display validation errors directly in the view
            resultLabel.setText(ex.getMessage());
        }
    }

    private void handleBackAction() {
        if (viewManagerModel != null) {
            viewManagerModel.setState("logged in"); // Navigate back to LoggedInView
            viewManagerModel.firePropertyChanged();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("userMessage".equals(evt.getPropertyName())) {
            // Update the result label when the state changes
            resultLabel.setText((String) evt.getNewValue());
        }
    }

    /**
     * Sets the ViewManagerModel for navigation.
     *
     * @param viewManagerModel the ViewManagerModel to set
     */
    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
    }
}
