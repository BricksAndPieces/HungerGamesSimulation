package hungergames.simulation.arena;

import org.json.JSONArray;
import org.json.JSONObject;
import hungergames.simulation.entities.Tribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that acts as a wrapper for the events stored within the json files
 * Parses the message to include any Tributes characteristics
 */
public class Event {

    final List<Tribute> tributes;

    private final Tribute[] randomTributes;
    private final Tribute[] killers;
    private final Tribute[] killed;

    private final String text;

    /**
     * Constructor for the Event object
     *
     * @param data
     * The paticular JSON data that is needed for an Event
     *
     * @param tributes
     * The remaining randomTributes that are available to use in the Event
     */
    public Event(final JSONObject data, final List<Tribute> tributes) {
        this.tributes = tributes;
        this.randomTributes = getRandomTributes(tributes, data.getInt("randomTributes"));
        this.killers = getTributes(data, "randomTributes");
        this.killed = getTributes(data, "killed");
        this.text = generateText(data.getString("message"));

        if(this.killers != null && this.killed != null) {
            for(final Tribute killer : this.randomTributes) {
                for(final Tribute dead : this.killed) {
                    killer.addKill(dead);
                    dead.die();
                }
            }
        }
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
     * Get a list of the remaining Tributes after the Event is completed
     *
     * @return
     * An unmodifiable List with the remaining living Tributes
     */
    public List<Tribute> getLivingTributes() {
        return Collections.unmodifiableList(tributes.stream().filter(t -> !t.isDead()).collect(Collectors.toList()));
    }

    /**
     * A private helper method for the Event class
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
            final Tribute tribute = this.randomTributes[Integer.parseInt(String.valueOf(format.charAt(start + 1)))];

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
     * A private helper method for the Event class
     * Gets an arrays of randomTributes using a specified key because randomTributes
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
            output[i] = this.randomTributes[array.getInt(i)];

        return output;
    }

    /**
     * Gets a random group of Tributes to be used in the Event
     *
     * @param tributes
     * A list of Tributes to choose randomly from
     *
     * @param count
     * The number of randomly selected randomTributes to return
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