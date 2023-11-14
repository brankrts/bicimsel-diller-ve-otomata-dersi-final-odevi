
import java.util.List;
import java.util.Map;

import configs.ApplicationConfigParser;
import configs.FileReader;
import configs.StateCache;
import enums.LanguageKeywords;
import models.State;

import java.util.ArrayList;
import observer.ObserverPool;

public class App {
    public static void main(String[] args) throws Exception {
        FileReader reader = new FileReader("input.txt");
        Map<String, ArrayList<String>> applicationConfig = reader.Read();
        ApplicationConfigParser parser = new ApplicationConfigParser(applicationConfig);
        List<State> states = parser.parse();
        ObserverPool observerPool = new ObserverPool();
        observerPool.registerObserver(states);
        StateCache.getInstance()
                .setCurrentState(applicationConfig.get(LanguageKeywords.INITIAL_STATE.toString()).get(0));
        StateCache.getInstance()
                .addStateToCache(applicationConfig.get(LanguageKeywords.INITIAL_STATE.toString()).get(0));
        String input = "202001";
        String[] inputArray = new String[input.length()];
        for (int i = 0; i < input.length(); i++) {
            inputArray[i] = String.valueOf(input.charAt(i));
        }
        for (String newInput : inputArray) {
            observerPool.setValue(newInput);
            StateCache.getInstance().addStateToCache(StateCache.getInstance().getCurrentState());
            StateCache.getInstance().addExecutationResult(StateCache.getInstance().getCurrentTargetValue());

        }

        StateCache.getInstance().printCachedStates();
        StateCache.getInstance().printCachedResult();

    }

}
