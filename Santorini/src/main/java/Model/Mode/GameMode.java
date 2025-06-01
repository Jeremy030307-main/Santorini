package Model.Mode;

import java.util.ArrayList;
import java.util.List;

public enum GameMode {

    CLASSIC("Button/classic_mode_btn.png"),
    TIMER("Button/timer_mode_btn.png"),
    WEALTH("Button/wealth_mode_btn.png");

    private final String btnImagePath;

    GameMode(String btnImagePath) {
        this.btnImagePath = btnImagePath;
    }

    public String getBtnImagePath() {
        return btnImagePath;
    }

    public static List<GameMode> getGameModes() {
        List<GameMode> gameModes = new ArrayList<>();

        gameModes.add(CLASSIC);
        gameModes.add(TIMER);
        gameModes.add(WEALTH);

        return gameModes;
    }

    public static List<String> getAllBtnImagePaths() {
        List<String> imagePaths = new ArrayList<>();

        for (GameMode gameMode : getGameModes()) {
            imagePaths.add(gameMode.getBtnImagePath());
        }
        return imagePaths;
    }
}
