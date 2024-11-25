package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    public static void main(String[] args) {
        final AppBuilder appBuilder = new AppBuilder();

        final JFrame application = appBuilder
                .addLoginView()
                .addSignupView()
                .addLoggedInView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addChangePasswordUseCase()
                .addLogoutUseCase()
                .addEditProfileView()      // Add the Edit Profile View
                .addEditProfileUseCase()   // Add the Edit Profile Use Case
                .build();

        application.pack();
        application.setVisible(true);
    }
}
