package Model.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ActionList implements Iterable<Action> {

    private List<Action> actionList;
    private OptionalAction optionalAction = null;

    public ActionList() {
        this.actionList = new ArrayList<>();
        this.optionalAction = null;
    }

    public ActionList(List<Action> actionList) {
        this.actionList = actionList;
        this.optionalAction = null;
    }

    public ActionList(OptionalAction optionalAction) {
        this.actionList = new ArrayList<>();
        if (optionalAction != null) {
            setOptionalAction(optionalAction);
        }
    }

    public ActionList(List<Action> actionList, OptionalAction optionalAction) {
        this.actionList = actionList;
        if (optionalAction != null) {
            setOptionalAction(optionalAction);
        }
    }

    public void add(Action action) {
        this.actionList.add(action);
    }

    public ActionList filter(Predicate<Action> predicate) {
        List<Action> filtered = new ArrayList<>();
        for (Action action : actionList) {
            if (predicate.test(action)) {
                filtered.add(action);
            }
        }
        return new ActionList(filtered, optionalAction); // OptionalAction can be copied or left null based on design
    }

    public int size() {
        return actionList.size();
    }

    public Action get(int index) {
        return actionList.get(index);
    }

    public void set(int index, Action action) {
        actionList.set(index, action);
    }

    public void setOptionalAction(OptionalAction optionalAction) {
        System.out.println(optionalAction + " " +  optionalAction.getCurrentPhase().getPhaseText() + " " + optionalAction.getNextPhase().getPhaseText());
        this.optionalAction = optionalAction;
    }

    public OptionalAction getOptionalAction() {
        return optionalAction;
    }

    public Action getFirst() {
        return actionList.getFirst();
    }

    public boolean isEmpty() {
        return this.actionList.isEmpty();
    }

    @Override
    public java.util.Iterator<Action> iterator() {
        return actionList.iterator();
    }
}
