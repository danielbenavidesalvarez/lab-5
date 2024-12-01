package use_case.people;

import entity.CommonUser;
import entity.User;
import use_case.like.LikeOutputBoundary;
import use_case.like.LikeUserDataAccessInterface;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PeopleInteractor implements PeopleInputBoundary {
    private final PeopleOutputBoundary peoplepresenter;
    private final PeopleUserDataAccessInterface dataAccess;

    public PeopleInteractor(PeopleOutputBoundary peoplepresenter, PeopleUserDataAccessInterface dataAccess) {
        this.peoplepresenter = peoplepresenter;
        this.dataAccess = dataAccess;

    }


    @Override
    public void execute(PeopleInputData peopleInputData) {
        PeopleOutputData peopleOutputData = new PeopleOutputData();

        List<User> databaseusers = dataAccess.getUsers();
//        List<CommonUser> databaseusers = new ArrayList<>();
//        databaseusers.add(new CommonUser("ya", "123"));
//        databaseusers.add(new CommonUser("da", "456"));

        for (User user : databaseusers) {
            JButton newbutton = new JButton(user.getName());
            peopleOutputData.getPeoplebuttons().add(newbutton);
        }
        peoplepresenter.present(peopleOutputData);

    }
}