package use_case.people;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PeopleOutputData {
    private List<JButton> peoplebuttons;

    public PeopleOutputData() {
        peoplebuttons = new ArrayList<>();
    }

    public List<JButton> getPeoplebuttons() {
        return peoplebuttons;
    }
}
