package Org.douglas.enums;

public enum BrowserEnum {
    CHROME("chrome"),
    EDGE("edge"),
    FIREFOX("firefox");

    private final String browserName;

    BrowserEnum(String browserName) {
        this.browserName = browserName;
    }

    public static BrowserEnum fromString(String browserName) {
        if (browserName == null || browserName.isEmpty()) {
            throw new IllegalArgumentException("Browser name cannot be null or empty.");
        }

        try {
            return BrowserEnum.valueOf(browserName.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown browser: '" + browserName + "'. Valid options are: "
                    + getValidBrowserNames(), e);
        }
    }

    private static String getValidBrowserNames() {
        StringBuilder validBrowsers = new StringBuilder();
        for (BrowserEnum browser : BrowserEnum.values()) {
            validBrowsers.append(browser.getBrowserName()).append(", ");
        }
        return validBrowsers.length() > 0 ? validBrowsers.substring(0, validBrowsers.length() - 2) : "";
    }

    public String getBrowserName() {
        return browserName;
    }
}
