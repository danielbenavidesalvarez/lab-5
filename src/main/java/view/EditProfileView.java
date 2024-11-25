package view;

import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfileViewModel;

/**
 * View for the Edit Profile use case.
 * It interacts with the controller and displays the output from the view model.
 */
public class EditProfileView {
    private final EditProfileController controller;
    private final EditProfileViewModel viewModel;

    public EditProfileView(EditProfileController controller, EditProfileViewModel viewModel) {
        this.controller = controller;
        this.viewModel = viewModel;
    }

    /**
     * Simulates a form submission with user input.
     *
     * @param userId    The user's ID.
     * @param name      The user's name.
     * @param age       The user's age.
     * @param interests The user's interests.
     */
    public void submitEditProfile(String userId, String name, int age, String interests) {
        // Send input to the controller
        controller.handleEditProfile(userId, name, age, interests);

        // Display the result to the user
        System.out.println(viewModel.getUserMessage());
    }
}
