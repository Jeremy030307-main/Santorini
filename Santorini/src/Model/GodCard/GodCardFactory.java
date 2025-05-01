
package Model.GodCard;

import java.util.*;
import java.util.function.Supplier;

/**
 * The {@code GodCardFactory} class is responsible for creating and managing the registration of god cards.
 * It allows god cards to be registered with a name and constructor and enables the creation of god cards
 * by name. Additionally, the class provides methods to retrieve available god cards and all registered god cards.
 * <p>
 * This class provides functionality to handle god card creation and selection for players in the game.
 * </p>
 */
public class GodCardFactory {

    private final Map<String, Supplier<GodCard>> godCardRegistry;
    private final Random random;

    /**
     * Constructs a new {@code GodCardFactory} with an empty registry of god cards
     * and a random number generator.
     */
    public GodCardFactory() {
        this.godCardRegistry = new HashMap<>();
        this.random = new Random();
    }

    /**
     * Registers a new god card with the specified name and constructor.
     *
     * @param name The name of the god card to register
     * @param constructor A supplier that creates the god card
     */
    public void registerGod(String name, Supplier<GodCard> constructor) {
        godCardRegistry.put(name, constructor);
    }

    /**
     * Creates a new god card by the specified name.
     * If the god card is not registered, an {@code IllegalArgumentException} is thrown.
     *
     * @param name The name of the god card to create
     * @return A new instance of the god card
     * @throws IllegalArgumentException if the god card with the specified name is not registered
     */
    public GodCard createGod(String name) {
        Supplier<GodCard> constructor = godCardRegistry.get(name);
        if (constructor != null) {
            return constructor.get();
        } else {
            throw new IllegalArgumentException("GodCard with name " + name + " is not registered.");
        }
    }

    /**
     * Retrieves a list of available god cards for the specified number of players.
     * The list is shuffled randomly, and the number of available god cards is twice the number of players,
     * ensuring each player has at least one choice.
     *
     * @param numPlayers The number of players in the game
     * @return A list of god card names available for selection
     */
    public List<String> getAvailableGodCards(int numPlayers) {
        List<String> allGods = new ArrayList<>(godCardRegistry.keySet());
        Collections.shuffle(allGods, random);
        return allGods.subList(0, Math.min(numPlayers * 2, allGods.size())); // 2x players for choice pool
    }

    /**
     * Retrieves a set of all registered god cards.
     *
     * @return An unmodifiable set containing the names of all registered god cards
     */
    public Set<String> getAllRegisteredGods() {
        return Collections.unmodifiableSet(godCardRegistry.keySet());
    }
}
