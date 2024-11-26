package use_case.like;

/**
 * Input data for the Like use case.
 */
public class LikeInputData {
    private final String userId;
    private final String likedUserId;

    public LikeInputData(String userId, String likedUserId) {
        this.userId = userId;
        this.likedUserId = likedUserId;
    }

    public String getUserId() {
        return userId;
    }

    public String getLikedUserId() {
        return likedUserId;
    }
}
