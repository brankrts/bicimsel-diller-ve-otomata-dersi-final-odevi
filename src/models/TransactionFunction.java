package models;

public class TransactionFunction {
    String target;
    String targetValue;

    public TransactionFunction(String target, String targetValue) {
        this.target = target;
        this.targetValue = targetValue;
    }

    public void print() {
        System.out.println("target: " + this.target + " targetValue: " + this.targetValue);
    }
}
