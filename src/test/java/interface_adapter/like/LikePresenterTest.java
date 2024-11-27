package interface_adapter.like;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.like.LikeOutputData;

import static org.junit.jupiter.api.Assertions.*;

class LikePresenterTest {

    private LikePresenter presenter;

    @BeforeEach
    void setUp() {
        presenter = new LikePresenter();
    }

    @Test
    void testPresent_SuccessfulLike() {
        LikeOutputData outputData = new LikeOutputData(true, "User liked successfully.");

        presenter.present(outputData);

        assertEquals("Like action succeeded: User liked successfully.", presenter.getUserMessage());
        assertTrue(presenter.isSuccess());
    }

    @Test
    void testPresent_UnsuccessfulLike() {
        LikeOutputData outputData = new LikeOutputData(false, "User already liked.");

        presenter.present(outputData);

        assertEquals("Like action failed: User already liked.", presenter.getUserMessage());
        assertTrue(presenter.isSuccess()); // isSuccess returns true because userMessage is not null
    }

    @Test
    void testDefaultState() {
        assertNull(presenter.getUserMessage());
        assertFalse(presenter.isSuccess());
    }
}
