package simulation.entities;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class that defines a single Tribute object within the simulation
 *
 * Each Tribute has a name, icon, gender, and district to help perform calculations
 * and make the UI appealing to the eye
 */
public class Tribute {

    private final String name;
    private final BufferedImage icon;
    private final boolean male;
    private final int district;

    private final List<Tribute> kills;
    private boolean dead;

    /**
     * Constructor for the Tribute class, creates a new Tribute object from an existing one
     *
     * @param other
     * The Tribute object to copy attributes from
     */
    public Tribute(final Tribute other) {
        this.name = other.name;
        this.icon = other.icon;
        this.male = other.male;
        this.district = other.district;

        this.kills = other.kills;
        this.dead = other.dead;
    }

    /**
     * Constructor for the Tribute class, creates a new Tribute object
     *
     * @param name
     * The name of this Tribute
     *
     * @param icon
     * The image to be displayed alongside this Tribute in the User Interface
     *
     * @param male
     * The gender of the Tribute, user for assigning pronouns and other gender
     * sensitive words
     *
     * @param district
     * The district that this Tribute is from, displayed alongside the name in
     * The User Interface
     */
    public Tribute(final String name, final BufferedImage icon, final boolean male, final int district) {
        this.name = name;
        this.icon = icon;
        this.male = male;
        this.district = district;

        this.kills = new ArrayList<>();
        this.dead = false;
    }

    /**
     * Get the name of the Tribute object
     *
     * @return
     * A String representation of the Tribute's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the image associated with Tribute object
     *
     * @return
     * A Bufferedimage representing the image of the Tribute
     */
    public BufferedImage getIcon() {
        return this.icon;
    }

    /**
     * Get the gender of the Tribute
     *
     * @return
     * Returns true if the tribute is male, else returns false
     */
    public boolean isMale() {
        return this.male;
    }

    /**
     * Get the District of the Tribute
     *
     * @return
     * The Integer representation of the Tribute's District
     */
    public int getDistrict() {
        return this.district;
    }

    /**
     * Kills the Tribute
     */
    public void die() {
        this.dead = true;
    }

    /**
     * Allows the Tribute to kill another Tribute
     *
     * @param other
     * The Tribute to be killed
     */
    public void addKill(final Tribute other) {
        if(!this.kills.contains(other))
            this.kills.add(other);
    }

    /**
     * Get the living state of the Tribute
     *
     * @return
     * Returns true if the Tribute is dead, els returns false
     */
    public boolean isDead() {
        return this.dead;
    }

    /**
     * Get a List of all the Tributes killed
     *
     * @return
     * An unmodifiable list of all Tributes that have been killed by the Tribute object
     */
    public List<Tribute> getKills() {
        return Collections.unmodifiableList(this.kills);
    }
}