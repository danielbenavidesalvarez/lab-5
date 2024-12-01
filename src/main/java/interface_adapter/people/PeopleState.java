package interface_adapter.people;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PeopleState {
    private List<JButton> peoplebuttons;

    public PeopleState(List<JButton> peoplebuttons) {
        this.peoplebuttons = peoplebuttons;
    }

    public PeopleState() {
        peoplebuttons = new ArrayList<>();
    }
    public List<JButton> getPeoplebuttons() {
        return peoplebuttons;
    }
}
