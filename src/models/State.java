package models;

import java.util.Map;

import configs.StateCache;
import interfaces.IObserver;

public class State implements IObserver {

    private String name;
    private Map<String, TransactionFunction> transactionFunctions;
    private StateCache applicationStateCacher;

    public State(String name, Map<String, TransactionFunction> transactionFuntions) {
        this.name = name;
        this.transactionFunctions = transactionFuntions;
        this.applicationStateCacher = StateCache.getInstance();
    }

    public String getName() {
        return this.name;
    }

    public void printTransactionFunctions() {
        for (String key : this.transactionFunctions.keySet()) {
            this.transactionFunctions.get(key).print();
        }
    }

    @Override
    public void update(String input) {
        if (this.applicationStateCacher.getCurrentState().equals(this.name)) {
            this.applicationStateCacher.setCurrentState(this.transactionFunctions.get(input).target);
            this.applicationStateCacher.setCurrentTargetValue(this.transactionFunctions.get(input).targetValue);
        }

    }

}
