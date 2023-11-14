package configs;

import java.util.ArrayList;

public class StateCache {

    private ArrayList<String> cachedStates;
    private ArrayList<String> cachedResult;
    private String currentState = "";
    private String currentTargetValue = "";

    private static StateCache instance;

    public static StateCache getInstance() {
        if (instance == null) {
            instance = new StateCache();
        }
        return instance;
    }

    public void setCurrentTargetValue(String value) {
        this.currentTargetValue = value;
    }

    public String getCurrentTargetValue() {
        return this.currentTargetValue;
    }

    public StateCache() {
        this.cachedStates = new ArrayList<String>();
        this.cachedResult = new ArrayList<String>();
    }

    public void setCurrentState(String state) {
        this.currentState = state;
    }

    public String getCurrentState() {
        return this.currentState;
    }

    public void clearCache() {
        this.cachedStates.clear();
        this.cachedResult.clear();
    }

    public void addStateToCache(String state) {
        this.cachedStates.add(state);
    }

    public void addExecutationResult(String result) {
        this.cachedResult.add(result);
    }

    public void printCachedStates() {
        System.out.println(this.cachedStates.toString());
    }

    public void printCachedResult() {
        System.out.println(this.cachedResult.toString());
    }

}
