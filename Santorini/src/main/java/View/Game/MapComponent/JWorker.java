package View.Game.MapComponent;

/**
 * The {@code JWorker} enum defines the different worker types for each color and gender.
 * Each worker is represented by an enum constant (e.g., ORANGE_MALE, PURPLE_FEMALE) and is associated
 * with an image path used for rendering the worker's appearance.
 * <p>
 * The available worker types are distinguished by color (orange, purple, red) and gender (male, female).
 * </p>
 */
public enum JWorker {

    /**
     * Represents the orange male worker, associated with the image at the specified path.
     */
    ORANGE_MALE("/Image/Worker/Orange/male.png"),
    /**
     * Represents the orange female worker, associated with the image at the specified path.
     */
    ORANGE_FEMALE("/Image/Worker/Orange/female.png"),
    /**
     * Represents the purple male worker, associated with the image at the specified path.
     */
    PURPLE_MALE("/Image/Worker/Purple/male.png"),
    /**
     * Represents the purple female worker, associated with the image at the specified path.
     */
    PURPLE_FEMALE("/Image/Worker/Purple/female.png"),
    /**
     * Represents the red male worker, associated with the image at the specified path.
     */
    RED_MALE("/Image/Worker/Red/male.png"),
    /**
     * Represents the red female worker, associated with the image at the specified path.
     */
    RED_FEMALE("/Image/Worker/Red/female.png"),;

    public final String path;

    /**
     * Constructs a new {@code JWorker} with the specified image path.
     *
     * @param path The path to the image associated with the worker
     */
    JWorker(String path) {
        this.path = path;
    }

    /**
     * Retrieves the image path associated with the worker.
     *
     * @return The image path of the worker
     */
    public String getPath() {
        return path;
    }

    /**
     * Retrieves the corresponding {@code JWorker} for the specified color and gender.
     * If the combination is not found, an {@code IllegalArgumentException} is thrown.
     *
     * @param color The color of the worker (e.g., "orange", "purple", "red")
     * @param gender The gender of the worker (e.g., "male", "female")
     * @return The {@code JWorker} corresponding to the specified color and gender
     * @throws IllegalArgumentException if the combination of color and gender is not found
     */
    public static JWorker from(String color, String gender) {
        String key = color.toUpperCase() + "_" + gender.toUpperCase();
        return JWorker.valueOf(key); // Throws IllegalArgumentException if not found
    }
}
