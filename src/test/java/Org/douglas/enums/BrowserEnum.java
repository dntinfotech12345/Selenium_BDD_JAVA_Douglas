package Org.douglas.enums;

public enum BrowserEnum {
    CHROME("chrome"),
    EDGE("edge"),
    FIREFOX("firefox");

    private final String browserName;

    BrowserEnum(String browserName) {
        this.browserName = browserName;
    }

    public String getBrowserName() {
        return browserName;
    }

    public static BrowserEnum fromString(String browserName) {
        for (BrowserEnum browser : BrowserEnum.values()) {
            if (browser.browserName.equalsIgnoreCase(browserName)) {
                return browser;
            }
        }
        throw new IllegalArgumentException("Unknown browser: " + browserName);
    }
}
