package View.Game.MapComponent;

/**
 * The {@code JPlayer} enum defines the different player colors and their associated image paths.
 * Each player color is represented by an enum constant (ORANGE, RED, PURPLE), and each color has an associated image path
 * used for rendering the player's worker tag.
 * <p>
 * This enum provides a way to associate player colors with images, and it also includes a utility method to convert a string
 * representing a color into the corresponding enum value.
 * </p>
 */
public enum JPlayer {
    /**
     * Represents the orange player color, associated with the image at the specified path.
     */
    ORANGE("Orange"),
    /**
     * Represents the red player color, associated with the image at the specified path.
     */
    RED("Red"),
    /**
     * Represents the purple player color, associated with the image at the specified path.
     */
    PURPLE("Purple");

    private final String color;

    /**
     * Constructs a new {@code JPlayer} with the specified image path.
     *
     * @param path The path to the image associated with the player color
     */
    JPlayer(String path) {
        this.color = path;
    }

    /**
     * Retrieves the image path associated with the player color.
     *
     * @return The image path of the player color
     */
    public String getColor() {
        return color;
    }
    /**
     * Retrieves the {@code JPlayer} corresponding to the specified color.
     * If the color is not found, an {@code IllegalArgumentException} is thrown.
     *
     * @param color The color string (case-insensitive) representing the player color
     * @return The {@code JPlayer} corresponding to the specified color
     * @throws IllegalArgumentException if the color does not match any player color
     */
    public static JPlayer from(String color) {
        String key = color.toUpperCase() ;
        return JPlayer.valueOf(key); // Throws IllegalArgumentException if not found
    }
}
