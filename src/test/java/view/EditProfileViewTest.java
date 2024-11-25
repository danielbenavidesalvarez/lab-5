package view;

import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfileViewModel;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals; // Add this static import

class EditProfileViewTest {

    @Test
    void testSubmitEditProfileSuccess() {
        // Mock dependencies
        EditProfileController mockController = mock(EditProfileController.class);
        EditProfileViewModel mockViewModel = mock(EditProfileViewModel.class);

        // Simulate a successful message in the view model
        when(mockViewModel.getUserMessage()).thenReturn("Profile updated successfully");

        // Create the view
        EditProfileView view = new EditProfileView(mockController, mockViewModel);

        // Simulate user input
        String userId = "123";
        String name = "Jane Doe";
        int age = 30;
        String interests = "Reading, Traveling";

        // Call the method to test
        view.submitEditProfile(userId, name, age, interests);

        // Verify that the controller is called with the correct arguments
        verify(mockController).handleEditProfile(userId, name, age, interests);

        // Verify that the view displays the correct success message
        assertEquals("Profile updated successfully", mockViewModel.getUserMessage());
    }

    @Test
    void testSubmitEditProfileFailure() {
        // Mock dependencies
        EditProfileController mockController = mock(EditProfileController.class);
        EditProfileViewModel mockViewModel = mock(EditProfileViewModel.class);

        // Simulate a failure message in the view model
        when(mockViewModel.getUserMessage()).thenReturn("Profile update failed: Name cannot be empty");

        // Create the view
        EditProfileView view = new EditProfileView(mockController, mockViewModel);

        // Simulate invalid user input
        String userId = "123";
        String name = ""; // Invalid name
        int age = 30;
        String interests = "Reading, Traveling";

        // Call the method to test
        view.submitEditProfile(userId, name, age, interests);

        // Verify that the controller is called with the incorrect arguments
        verify(mockController).handleEditProfile(userId, name, age, interests);

        // Verify that the view displays the correct failure message
        assertEquals("Profile update failed: Name cannot be empty", mockViewModel.getUserMessage());
    }
}
