package Model.GodCard;

public abstract class GodCard {

    protected  String name;
    protected String description;

    public GodCard(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void onSetup(){
    };

    public void beforeMove(){
    };

    public void afterMove(){
    };

    public void beforeBuild() {
    };

    public void afterBuild() {}

    public void beforeOpponentMove(){
    };

    public void afterOpponentMove(){
    };

    public void beforeOpponentBuild() {
    };

    public void afterOpponentBuild() {}

    public void isWin(){}

    public void isLose(){}
}
