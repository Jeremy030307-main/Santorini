package View.Game.MapComponent;

public enum JPlayer {
    ORANGE("Asset/Image/Worker/Orange/tag.png"),
    RED("Asset/Image/Worker/Red/tag.png"),
    PURPLE("Asset/Image/Worker/Purple/tag.png");

    public final String path;

    /**
     * Constructor of the JCellStatus
     *
     * @param path the path containing the proper image
     */
    JPlayer(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
    public static JPlayer from(String color) {
        String key = color.toUpperCase() ;
        return JPlayer.valueOf(key); // Throws IllegalArgumentException if not found
    }
}
