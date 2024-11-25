package view;

import interface_adapter.edit_profile.EditProfileController;
import interface_adapter.edit_profile.EditProfileViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProfileView extends JPanel {
    private final EditProfileViewModel viewModel;
    private EditProfileController controller;

    private final JTextField userIdField = new JTextField(15);
    private final JTextField nameField = new JTextField(15);
    private final JTextField ageField = new JTextField(5);
    private final JTextField interestsField = new JTextField(20);
    private final JLabel resultLabel = new JLabel();

    public EditProfileView(EditProfileViewModel viewModel) {
        this.viewModel = viewModel;

        // Set up the layout
        setLayout(new GridLayout(6, 2));
        add(new JLabel("User ID:"));
        add(userIdField);
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Age:"));
        add(ageField);
        add(new JLabel("Interests:"));
        add(interestsField);

        JButton submitButton = new JButton("Update Profile");
        add(submitButton);
        add(resultLabel);

        // Handle button click
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userId = userIdField.getText();
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String interests = interestsField.getText();

                // Delegate to the controller
                controller.handleEditProfile(userId, name, age, interests);

                // Update the result label
                resultLabel.setText(viewModel.getUserMessage());
            }
        });
    }

    public void setEditProfileController(EditProfileController controller) {
        this.controller = controller;
    }

    public String getViewName() {
        return "EditProfileView";
    }
}
