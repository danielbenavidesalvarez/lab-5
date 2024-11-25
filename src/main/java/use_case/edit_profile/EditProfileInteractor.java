package use_case.edit_profile;

public class EditProfileInteractor implements EditProfileInputBoundary {
    private final EditProfileOutputBoundary outputBoundary;
    private final UserDataAccessInterface dataAccess;

    public EditProfileInteractor(EditProfileOutputBoundary outputBoundary, UserDataAccessInterface dataAccess) {
        this.outputBoundary = outputBoundary;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(EditProfileInputData inputData) {
        // Validate input
        if (inputData.getName() == null || inputData.getName().isEmpty()) {
            outputBoundary.present(new EditProfileOutputData(false, "Name cannot be empty"));
            return;
        }

        // Retrieve user and update profile
        User user = dataAccess.findById(inputData.getUserId());
        if (user == null) {
            outputBoundary.present(new EditProfileOutputData(false, "User not found"));
            return;
        }

        user.setName(inputData.getName());
        user.setAge(inputData.getAge());
        user.setInterests(inputData.getInterests());
        user.setProfilePicture(inputData.getProfilePicture());

        dataAccess.save(user);

        outputBoundary.present(new EditProfileOutputData(true, "Profile updated successfully"));
    }
}

