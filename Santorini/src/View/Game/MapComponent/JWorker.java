package View.Game.MapComponent;

public enum JWorker {

    ORANGE_MALE("Asset/Image/Worker/Orange/male.png"),
    ORANGE_FEMALE("Asset/Image/Worker/Orange/female.png"),
    PURPLE_MALE("Asset/Image/Worker/Purple/male.png"),
    PURPLE_FEMALE("Asset/Image/Worker/Purple/female.png"),
    RED_MALE("Asset/Image/Worker/Red/male.png"),
    RED_FEMALE("Asset/Image/Worker/Red/female.png"),;

    public final String path;

    /**
     * Constructor of the JCellStatus
     *
     * @param path the path containing the proper image
     */
    JWorker(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public static JWorker from(String color, String gender) {
        String key = color.toUpperCase() + "_" + gender.toUpperCase();
        return JWorker.valueOf(key); // Throws IllegalArgumentException if not found
    }
}
