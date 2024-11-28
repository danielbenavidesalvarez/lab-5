package interface_adapter.like;

import use_case.like.LikeOutputBoundary;
import use_case.like.LikeOutputData;

/**
 * The presenter for the "Like" use case.
 */
public class LikePresenter implements LikeOutputBoundary {

    private String userMessage;
    private LikeState likeState; // Link to the LikeState

    /**
     * Formats the output data into a user-friendly message.
     *
     * @param outputData the output data from the interactor
     */
    @Override
    public void present(LikeOutputData outputData) {
        // Format the user-friendly message
        if (outputData.isSuccess()) {
            userMessage = "Like action succeeded: " + outputData.getMessage();
        } else {
            userMessage = "Like action failed: " + outputData.getMessage();
        }

        // Update the state with the user message
        if (likeState != null) {
            likeState.setUserMessage(userMessage);
        }
    }

    /**
     * Retrieves the user-friendly message.
     *
     * @return the user message for the view
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * Links the presenter to the state.
     *
     * @param likeState the LikeState to update
     */
    public void setState(LikeState likeState) {
        this.likeState = likeState;
    }

    public boolean isSuccess() {
        return userMessage != null;
    }
}
