package seedu.smartnus.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.smartnus.model.Model.PREDICATE_SHOW_ALL_NOTES;
import static seedu.smartnus.model.Model.PREDICATE_SHOW_ALL_QUESTIONS;

import seedu.smartnus.model.Model;

/**
 * Lists all questions in SmartNus to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";
    public static final String NOTE_KEYWORD = "note";
    public static final String QUESTION_KEYWORD = "question";

    public static final String MESSAGE_SUCCESS_QUESTIONS = "Listed all questions";
    public static final String MESSAGE_SUCCESS_NOTES = "Listed all notes";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": lists the contents of SmartNUS. "
            + "Parameters: "
            + NOTE_KEYWORD + ": list notes or "
            + QUESTION_KEYWORD + ": list questions "
            + "Example: " + COMMAND_WORD + " "
            + NOTE_KEYWORD;

    private String panel;

    /**
     * Instantiates a new ListCommand
     * @param listArg argument passed to the list command
     */
    public ListCommand(String listArg) {
        panel = listArg; // assuming given listArg is valid
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String successMessage;
        if (panel.equals(QUESTION_KEYWORD)) {
            model.updateFilteredQuestionList(PREDICATE_SHOW_ALL_QUESTIONS);
            model.setPanel(QUESTION_KEYWORD);
            successMessage = MESSAGE_SUCCESS_QUESTIONS;
        } else {
            model.updateFilteredNoteList(PREDICATE_SHOW_ALL_NOTES);
            model.setPanel(NOTE_KEYWORD);
            successMessage = MESSAGE_SUCCESS_NOTES;
        }
        return new CommandResult(successMessage);
    }

    /**
     * Checks if two instances are the same.
     * @param other the other instance of listCommand.
     * @return true if both instances are the same, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListCommand // instanceof handles nulls
                && panel.equals(((ListCommand) other).panel));
    }
}
