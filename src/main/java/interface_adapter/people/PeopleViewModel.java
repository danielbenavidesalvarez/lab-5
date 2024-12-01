package interface_adapter.people;

import interface_adapter.ViewModel;
import interface_adapter.change_password.LoggedInState;


public class PeopleViewModel extends ViewModel<PeopleState> {

    public PeopleViewModel() {
        super("people");
        setState(new PeopleState());
    }

}
