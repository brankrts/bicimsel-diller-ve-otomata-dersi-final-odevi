package enums;

public enum LanguageKeywords {
    STATES("Q"),
    INPUT_ALPHABET("Î£"),
    TAPE_ALPHABET("Î“"),
    TRANSACTION_FUNCTION("Î´"),
    INITIAL_STATE("q0");

    private String state;

    LanguageKeywords(String state) {
        this.state = state;
    }

    public String get() {
        return this.state;
    }

    public static LanguageKeywords fromString(String text) {
        for (LanguageKeywords b : LanguageKeywords.values()) {
            if (b.state.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }

}
