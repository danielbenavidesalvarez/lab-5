package interface_adapter.edit_profile;

/**
 * View model for the Edit Profile use case.
 * It converts the state into a format suitable for the UI.
 */
public class EditProfileViewModel {
    private final EditProfileState state;

    public EditProfileViewModel(EditProfileState state) {
        this.state = state;
    }

    /**
     * Gets the formatted message for the UI.
     *
     * @return A user-friendly message indicating the operation's result.
     */
    public String getUserMessage() {
        if (state.isSuccess()) {
            return state.getMessage(); // Use the existing success message directly
        } else {
            return "Profile update failed: " + state.getMessage();
        }
    }

    /**
     * Provides access to the current state object.
     *
     * @return The state associated with this view model.
     */
    public EditProfileState getState() {
        return state;
    }
}
