package View.Game;

public enum JWorker {

    ORANGE_MALE("Asset/Image/Worker/Orange/male.png"),
    ORANGE_FEMALE("Asset/Image/Worker/Orange/female.png"),
    PURPLE_MALE("Asset/Image/Worker/Purple/male.png"),
    PURPLE_FEMALE("Asset/Image/Worker/Purple/female.png"),
    BLUE_MALE("Asset/Image/Worker/Red/male.png"),
    BLUE_FEMALE("Asset/Image/Worker/Red/female.png"),;

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
}
