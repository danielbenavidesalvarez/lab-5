package use_case.people;

import entity.CommonUser;
import entity.User;

import java.util.List;

public interface PeopleUserDataAccessInterface {
     List<User>  getUsers ();
}
