package use_case.people;

import use_case.logout.LogoutInputData;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface PeopleInputBoundary {

    /**
     * Executes the Logout use case.
     * @param peopleInputData the input data
     */
    void execute(PeopleInputData peopleInputData);
}
