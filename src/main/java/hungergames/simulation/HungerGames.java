package hungergames.simulation;

import com.sun.tools.internal.jxc.ap.Const;
import hungergames.Constants;
import org.json.JSONArray;
import org.json.JSONObject;
import hungergames.simulation.arena.Event;
import hungergames.simulation.arena.Phase;
import hungergames.simulation.entities.Tribute;
import util.JSONFileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class HungerGames {

    private Phase phase = Phase.valueOf("TODO LOL");
    private final List<Tribute> tributes;

    private final JSONArray BLOODBATH_PEACEFUL;
    private final JSONArray BLOODBATH_FATAL;
    private final JSONArray DAY_PEACEFUL;
    private final JSONArray DAY_FATAL;
    private final JSONArray NIGHT_PEACEFUL;
    private final JSONArray NIGHT_FATAL;
    //private final JSONArray arenaEvents;    TODO: figure out how to do this one

    public HungerGames() {
        final JSONObject data;
        try { data = JSONFileReader.read(Constants.JSON_FILE_NAME); }
        catch(final IOException e) {
            throw new IllegalArgumentException("Could not locate the json file containing the events");
        }

        final JSONObject bloodbath = data.getJSONObject("bloodbath");
        this.BLOODBATH_PEACEFUL = bloodbath.getJSONArray("peaceful");
        this.BLOODBATH_FATAL = bloodbath.getJSONArray("fatal");

        final JSONObject day = data.getJSONObject("day");
        this.DAY_PEACEFUL = day.getJSONArray("peaceful");
        this.DAY_FATAL = day.getJSONArray("fatal");

        final JSONObject night = data.getJSONObject("night");
        this.NIGHT_PEACEFUL = night.getJSONArray("peaceful");
        this.NIGHT_FATAL = night.getJSONArray("fatal");








        this.tributes = null;
    }

    public Event nextEvent() {


        // if no tributes left...


        switch(this.phase) {
            case BLOODBATH: {

            }
            case DAY: {

            }
            case NIGHT: {

            }
            case ARENA_EVENT: {

            }
        }




        return null;
    }
}