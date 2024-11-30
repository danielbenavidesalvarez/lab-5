package use_case.like;

import entity.User;

/**
 * Interactor for the Like User use case.
 */
public class LikeInteractor implements LikeInputBoundary {
    private final LikeOutputBoundary likepresenter;
    private final LikeUserDataAccessInterface dataAccess;

    public LikeInteractor(LikeOutputBoundary likepresenter, LikeUserDataAccessInterface dataAccess) {
        this.likepresenter = likepresenter;
        this.dataAccess = dataAccess;
    }

    @Override
    public void execute(LikeInputData inputData) {
        // Step 1: Retrieve the liking user
        User user = dataAccess.findById(inputData.getUserId());
        if (user == null) {
            likepresenter.present(new LikeOutputData(false, "User not found"));
            return;
        }

        // Step 2: Retrieve the liked user
        User likedUser = dataAccess.findById(inputData.getLikedUserId());
        if (likedUser == null) {
            likepresenter.present(new LikeOutputData(false, "Liked user not found"));
            return;
        }

        // Step 3: Validate the like operation
//        if (user.getUserId().equals(likedUser.getUserId())) {
//            likepresenter.present(new LikeOutputData(false, "Users cannot like themselves"));
//            return;
//        }

        if (((entity.CommonUser) user).hasLiked(likedUser.getUserId())) {
            likepresenter.present(new LikeOutputData(false, "User already liked this person"));
            return;
        }

        // Step 4: Perform the like operation
        try {
            user.likeUser(likedUser);
            dataAccess.save(user); // Save the updated user to persist the like
            likepresenter.present(new LikeOutputData(true, "User liked successfully"));
        }
        catch (Exception e) {
            likepresenter.present(new LikeOutputData(false, e.getMessage()));
        }
    }
}
