package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.analytics.AnalyticsController;
import interface_adapter.analytics.AnalyticsState;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class AnalyticsView extends JPanel {
    private final AnalyticsController controller;
    private final AnalyticsState state;
    private ViewManagerModel viewManagerModel; // For navigation

    public AnalyticsView(AnalyticsController controller, AnalyticsState state) {
        this.controller = controller;
        this.state = state;

        // Layout and components
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel titleLabel = new JLabel("Analytics Dashboard");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(titleLabel);

        // Fetch analytics when the view is initialized
        String userId = "dummy_user_id"; // Replace with actual user ID logic
        controller.executeAnalytics(userId);

        JLabel likesGivenLabel = new JLabel("Likes Given: " + state.getLikesGiven());
        JLabel likesReceivedLabel = new JLabel("Likes Received: " + state.getLikesReceived());
        JLabel matchesLabel = new JLabel("Matches: " + state.getMatches());
        JLabel sharedInterestsLabel = new JLabel("Shared Interests: " + state.getSharedInterests());

        this.add(likesGivenLabel);
        this.add(likesReceivedLabel);
        this.add(matchesLabel);
        this.add(sharedInterestsLabel);

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> navigateToLoggedInView());
        this.add(backButton);
    }


    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Analytics Menu ===");
        System.out.println("1. View Analytics");
        System.out.println("0. Back to Logged In View");
        System.out.print("Enter your choice: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                executeAnalytics(scanner);
                break;
            case 0:
                navigateToLoggedInView();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    private void executeAnalytics(Scanner scanner) {
        System.out.print("Enter your user ID: ");
        String userId = scanner.nextLine();

        try {
            controller.executeAnalytics(userId);
            state.displayAnalytics();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void navigateToLoggedInView() {
        if (viewManagerModel != null) {
            viewManagerModel.setState("logged in");
            viewManagerModel.firePropertyChanged();
        } else {
            System.err.println("ViewManagerModel is not set! Cannot navigate to LoggedInView.");
        }
    }

    public void setViewManagerModel(ViewManagerModel viewManagerModel) {
        System.out.println("ViewManagerModel injected: " + (viewManagerModel != null));
        this.viewManagerModel = viewManagerModel;
    }
}
