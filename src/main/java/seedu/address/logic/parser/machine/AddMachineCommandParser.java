package seedu.address.logic.parser.machine;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MACHINE_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.machine.AddMachineCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.job.Job;
import seedu.address.model.machine.Machine;
import seedu.address.model.machine.MachineName;
import seedu.address.model.machine.MachineStatus;
import seedu.address.model.tag.Tag;


/**
 * Parses input arguments and creates a new AddMachineCommand object
 */
public class AddMachineCommandParser implements Parser<AddMachineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMachineCommand
     * and returns an AddMachineCommand object for execution
     *
     * @throws ParseException if the user input does not conform the expected format
     */

    public AddMachineCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_MACHINE_STATUS);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_MACHINE_STATUS) || !argMultimap.getPreamble()
            .isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMachineCommand.MESSAGE_USAGE));
        }

        MachineName name = ParserUtil.parseMachineName(argMultimap.getValue(PREFIX_NAME).get());
        if (name.fullName.equals("AUTO")) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMachineCommand.MESSAGE_USAGE));
        }
        MachineStatus machineStatus = ParserUtil.parseMachineStatus(argMultimap.getValue(PREFIX_MACHINE_STATUS).get());
        ArrayList<Job> jobs = new ArrayList<>();
        Set<Tag> tags = new HashSet<>();
        Machine machine = new Machine(name, jobs, tags, machineStatus);

        return new AddMachineCommand(machine);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
