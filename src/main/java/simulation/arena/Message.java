package simulation.arena;

import org.json.JSONArray;
import org.json.JSONObject;
import simulation.entities.Tribute;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that acts as a wrapper for the events stored within the json files
 * Parses the message to include any Tributes characteristics
 */
public class Message {

    private final Tribute[] tributes;
    private final Tribute[] killers;
    private final Tribute[] killed;

    private final String text;

    /**
     * Constructor for the Message object
     *
     * @param data
     * The paticular JSON data that is needed for an Event
     *
     * @param livingTributes
     * The remaining tributes that are available to use in the Event
     */
    public Message(final JSONObject data, final List<Tribute> livingTributes) {
        this.tributes = getRandomTributes(livingTributes, data.getInt("tributes"));
        this.killers = getTributes(data, "tributes");
        this.killed = getTributes(data, "killed");
        this.text = generateText(data.getString("message"));
    }

    /**
     * Get the formatted text for an Event
     *
     * @return
     * The text wanted by the Event with Tribute characteristics inserted
     */
    public String getText() {
        return this.text;
    }

    /**
     * Get all the Tributes that "won" and engagement
     *
     * @return
     * An array of Tributes that were on the positive side of an engagement
     * Can return null if the event was peaceful
     */
    public Tribute[] getKillers() {
        return this.killers;
    }

    /**
     * Get all the Tributes that "lost" and engagement
     *
     * @return
     * An array of Tributes that were on the negative side of an engagement
     * Can return null if the event was peaceful
     */
    public Tribute[] getKilled() {
        return this.killed;
    }

    /**
     * A private helper method for the Message class
     * Formats the text for the Event to input any characteristics of the Tributes
     * that are needed.
     *
     * This includes any gender specific pronouns.
     * "{0}" will be replaced with the first Tribute's name, {1} will be replaced
     * with
     * the second Tribute's name, etc. Any formatting with the syntax
     * "{0.one_two}" will choose the String "one" if the Tribute is a male and
     * the String "two" if the tribute is female
     *
     * @param format
     * The String format that has to be followed
     *
     * @return
     * The formatted String with Tributes names and other checks taken into account
     */
    private String generateText(final String format) {
        int start = format.indexOf("{");
        int end = format.indexOf("}");
        if(start != -1 && end != -1 && start < end) {
            final Tribute tribute = this.tributes[Integer.parseInt(String.valueOf(format.charAt(start+1)))];

            final String replacement;
            if(format.charAt(start+2) == '.'){
                final String[] options = format.substring(start+3, end).split("_");
                replacement = tribute.isMale() ? options[0] : options[1];
            }else {
                replacement = tribute.getName();
            }

            return generateText(format.substring(0, start) + replacement + format.substring(end+1));
        }

        return format;
    }

    /**
     * A private helper method for the Message class
     * Gets an arrays of tributes using a specified key because tributes
     * are referenced as ints in the JSON data
     *
     * @param data
     * The JSON that holds the info for Tributes
     *
     * @param key
     * The key required to get the Tribute info from the JSON
     *
     * @return
     * An array of Tributes that are represented in the JSON by the specified key
     */
    private Tribute[] getTributes(final JSONObject data, final String key) {
        if(!data.has(key))
            return null;

        final JSONArray array = data.getJSONArray(key);
        final Tribute[] output = new Tribute[array.length()];
        for(int i = 0; i < array.length(); i++)
            output[i] = this.tributes[array.getInt(i)];

        return output;
    }

    /**
     * Gets a random group of Tributes to be used in the Event
     *
     * @param tributes
     * A list of Tributes to choose randomly from
     *
     * @param count
     * The number of randomly selected tributes to return
     *
     * @return
     * An array of Tributes with a size specified by int value count
     */
    private Tribute[] getRandomTributes(final List<Tribute> tributes, final int count) {
        if(count > tributes.size())
            throw new IllegalArgumentException("List of Tributes is too small to pick from");

        final Tribute[] output = new Tribute[count];
        final List<Tribute> tempList = new ArrayList<>(tributes);
        for(int i = 0; i < count; i++) {
            final Tribute tribute = tempList.get((int)(Math.random() * tempList.size()));
            tempList.remove(tribute);
            output[i] = tribute;
        }

        return output;
    }
}