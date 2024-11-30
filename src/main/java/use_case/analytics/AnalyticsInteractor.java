package use_case.analytics;

import entity.User;

public class AnalyticsInteractor implements AnalyticsInputBoundary {

    private final AnalyticsOutputBoundary presenter;
    private final AnalyticsUserDataAccessInterface userDataAccess;

    public AnalyticsInteractor(AnalyticsOutputBoundary presenter, AnalyticsUserDataAccessInterface userDataAccess) {
        this.presenter = presenter;
        this.userDataAccess = userDataAccess;
    }

    @Override
    public void execute(AnalyticsInputData inputData) {
        User user = userDataAccess.getUserById(inputData.getUserId());

        if (user == null) {
            throw new IllegalArgumentException("User not found.");
        }

        // Retrieve analytics data
        int likesGiven = user.getLikedUsers().size();
        int likesReceived = user.getLikesReceivedCount();
        int matches = userDataAccess.countMatches(user.getUserId());
        int sharedInterests = userDataAccess.countSharedInterests(user.getUserId());

        // Create the output data
        AnalyticsOutputData outputData = new AnalyticsOutputData(likesGiven, likesReceived, matches, sharedInterests);

        // Send the output to the presenter
        presenter.present(outputData);
    }
}

