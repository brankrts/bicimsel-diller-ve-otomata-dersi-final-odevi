package configs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import enums.LanguageKeywords;
import models.State;
import models.TransactionFunction;

public class ApplicationConfigParser {

    private Map<String, ArrayList<String>> applicationConfig;

    public ApplicationConfigParser(Map<String, ArrayList<String>> applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    public List<State> parse() {
        Map<String, Map<String, TransactionFunction>> stateTransactions = new HashMap<String, Map<String, TransactionFunction>>();
        List<State> states = new ArrayList<State>();

        for (String key : this.applicationConfig.keySet()) {
            if (this.applicationConfig.get(LanguageKeywords.STATES.toString()).contains(key)) {

                for (int i = 0; i < this.applicationConfig.get(key).size(); i++) {
                    final int keyI = i;
                    String[] tfunc = this.applicationConfig.get(key).get(i).replace("(", "").replace(")", "")
                            .split(",");
                    if (!stateTransactions.containsKey(key)) {
                        stateTransactions.put(key, new HashMap<String, TransactionFunction>() {
                            {
                                put(applicationConfig.get(LanguageKeywords.INPUT_ALPHABET.toString()).get(keyI),
                                        new TransactionFunction(tfunc[0], tfunc[1]));
                            }
                        });
                        continue;
                    }
                    stateTransactions.get(key)
                            .put(this.applicationConfig.get(LanguageKeywords.INPUT_ALPHABET.toString()).get(i),
                                    new TransactionFunction(tfunc[0], tfunc[1]));

                }
            }
        }
        for (String key : stateTransactions.keySet()) {
            states.add(new State(key, stateTransactions.get(key)));
        }
        return states;

    }
}
