package seedu.smartnus.logic.parser;

import static seedu.smartnus.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_IMPORTANCE;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.smartnus.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.smartnus.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Set;

import seedu.smartnus.logic.commands.questions.AddMcqCommand;
import seedu.smartnus.logic.parser.exceptions.ParseException;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.tag.Tag;




public class AddMcqCommandParser implements Parser<AddMcqCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddMcqCommand
     * and returns an AddMcqCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddMcqCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_OPTION,
                        PREFIX_ANSWER, PREFIX_IMPORTANCE, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_OPTION, PREFIX_ANSWER, PREFIX_IMPORTANCE)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddMcqCommand.MESSAGE_USAGE));
        }

        Name questionName = ParserUtil.parseName(argMultimap.getValue(PREFIX_QUESTION).get());
        Importance importance = ParserUtil.parseImportance(argMultimap.getValue(PREFIX_IMPORTANCE).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Set<Choice> choices = ParserUtil.parseChoices(argMultimap.getAllValues(PREFIX_OPTION),
                argMultimap.getValue(PREFIX_ANSWER).get());

        Question toAdd = new MultipleChoiceQuestion(questionName, importance, tagList, choices);

        return new AddMcqCommand(toAdd);
    }

}
