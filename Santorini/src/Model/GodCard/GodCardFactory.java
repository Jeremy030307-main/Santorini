package Model.GodCard;

import java.util.*;
import java.util.function.Supplier;

public enum GodCardFactory {
    ARTEMIS(Artemis ::new),
    DEMETER(Demeter ::new),;

    private final Supplier<GodCard> godConstructor;
    private final String description;

    GodCardFactory(Supplier<GodCard> godConstructor){
        this.godConstructor = godConstructor;

        GodCard godCard = this.godConstructor.get();
        this.description = godCard.getDescription();
    }

    public static List<GodCardFactory> getAllGods() {
        return List.of(values());
    }

    public Supplier<GodCard> getConstructor() {
        return godConstructor;
    }

    public String getName() {
        return toString();
    }

    public String getDescription() {
        return description;
    }
}
