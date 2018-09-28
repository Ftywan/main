package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.machine.Machine;
import seedu.address.model.admin.Admin;
import seedu.address.model.admin.Username;
import seedu.address.model.person.Person;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    Admin findAdmin(Username username);

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the admins list.
     * This list will not contain any duplicate admins.
     */
    ObservableList<Admin> getAdminList();


    /**
     * Returns an unmodifiable view of the machines list.
     * This list will not contain any duplicate machines.
     *
     */
    ObservableList<Machine> getMachineList();

}
