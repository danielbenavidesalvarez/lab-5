package app;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryUserDataAccessObject;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.ChangePasswordController;
import interface_adapter.change_password.ChangePasswordPresenter;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.edit_profile.EditProfileState;
import interface_adapter.like.LikeController;
import interface_adapter.like.LikePresenter;
import interface_adapter.like.LikeState;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.logout.LogoutController;
import interface_adapter.logout.LogoutPresenter;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfilePresenter;
import interface_adapter.edit_profile.EditProfileViewModel;
import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInteractor;
import use_case.change_password.ChangePasswordOutputBoundary;
import use_case.edit_profile.UserDataAccessInterface;
import use_case.like.LikeInputBoundary;
import use_case.like.LikeInteractor;
import use_case.like.LikeOutputBoundary;
import use_case.like.LikeUserDataAccessInterface;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.logout.LogoutInputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.logout.LogoutOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInteractor;
import use_case.edit_profile.EditProfileOutputBoundary;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 * <p/>
 * This is done by adding each View and then adding related Use Cases.
 */
// Checkstyle note: you can ignore the "Class Data Abstraction Coupling"
//                  and the "Class Fan-Out Complexity" issues for this lab; we encourage
//                  your team to think about ways to refactor the code to resolve these
//                  if your team decides to work with this as your starter code
//                  for your final project this term.
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    // thought question: is the hard dependency below a problem?
    private final UserFactory userFactory = new CommonUserFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);


    // thought question: is the hard dependency below a problem?
    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private EditProfileView editProfileView;
    private EditProfileViewModel editProfileViewModel;


    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);

        // Set the ViewManagerModel for navigation
        loggedInView.setViewManagerModel(viewManagerModel);

        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    public AppBuilder addEditProfileView() {
        EditProfileState state = new EditProfileState();
        editProfileViewModel = new EditProfileViewModel(state, viewManagerModel);
        editProfileView = new EditProfileView(editProfileViewModel);

        // Add EditProfileView to the CardLayout
        cardPanel.add(editProfileView, editProfileView.getViewName());
        return this;
    }

    public AppBuilder addLikeView() {
        // Step 1: Create the State and Presenter
        LikeState likeState = new LikeState();
        LikePresenter likePresenter = new LikePresenter();

        // Step 2: Set up the Data Access Layer
        // Replace `InMemoryLikeDataAccess` with your actual implementation
        LikeUserDataAccessInterface likeDataAccess = new InMemoryUserDataAccessObject();

        // Step 3: Create the Interactor
        LikeInputBoundary likeInputBoundary = new LikeInteractor(likePresenter, likeDataAccess);

        // Step 4: Link the Presenter to the State
        likePresenter.setState(likeState);

        // Step 5: Create the Controller and View
        LikeController likeController = new LikeController(likeInputBoundary);
        LikeView likeView = new LikeView(likeController, likeState);

        // Step 6: Register the View with ViewManagerModel
        likeView.setViewManagerModel(viewManagerModel); // This method must exist in LikeView

        // Step 7: Add the View to the CardLayout
        cardPanel.add(likeView, "LikeView");

        return this;
    }

    private LikeInputBoundary getLikeInputBoundary() {
        LikeOutputBoundary likePresenter = new LikePresenter();
        return new LikeInteractor(likePresenter, getLikeUserDataAccess());
    }

    private LikeUserDataAccessInterface getLikeUserDataAccess() {
        return new LikeUserDataAccessInterface() {
            @Override
            public User findById(String userId) {
                return null;
            }

            @Override
            public void save(User user) {

            }
        }; // Replace with your actual data access implementation
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                loggedInViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        loggedInView.setLogoutController(logoutController);
        return this;
    }

    public AppBuilder addEditProfileUseCase() {
        // Use the existing state and viewModel
        EditProfileOutputBoundary presenter = new EditProfilePresenter(editProfileViewModel.getState());

        // Use the shared userDataAccessObject
        EditProfileInputBoundary interactor = new EditProfileInteractor(presenter, userDataAccessObject);

        // Create the controller and wire it to the interactor
        EditProfileController editProfileController = new EditProfileController(interactor);

        // Connect the controller to the view
        editProfileView.setEditProfileController(editProfileController);

        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Login Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
