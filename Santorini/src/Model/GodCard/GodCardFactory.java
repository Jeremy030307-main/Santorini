package Model.GodCard;

import java.util.*;
import java.util.function.Supplier;

public class GodCardFactory {

    private final Map<String, Supplier<GodCard>> godCardRegistry;
    private final Random random;

    public GodCardFactory() {
        this.godCardRegistry = new HashMap<>();
        this.random = new Random();
    }

    public void registerGod(String name, Supplier<GodCard> constructor) {
        godCardRegistry.put(name, constructor);
    }

    public GodCard createGod(String name) {
        Supplier<GodCard> constructor = godCardRegistry.get(name);
        if (constructor != null) {
            return constructor.get();
        } else {
            throw new IllegalArgumentException("GodCard with name " + name + " is not registered.");
        }
    }

    // Not sure
    public List<String> getAvailableGodCards(int numPlayers) {
        List<String> allGods = new ArrayList<>(godCardRegistry.keySet());
        Collections.shuffle(allGods, random);
        return allGods.subList(0, Math.min(numPlayers * 2, allGods.size())); // 2x players for choice pool
    }

    public Set<String> getAllRegisteredGods() {
        return Collections.unmodifiableSet(godCardRegistry.keySet());
    }
}
