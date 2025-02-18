package Org.douglas.enums;

public enum OSEnum {
    WINDOWS("windows"),
    MAC("mac"),
    LINUX("linux");

    private final String osName;

    OSEnum(String osName) {
        this.osName = osName;
    }

    public String getOsName() {
        return osName;
    }

    public static OSEnum fromString(String osName) {
        for (OSEnum os : OSEnum.values()) {
            if (os.osName.equalsIgnoreCase(osName)) {
                return os;
            }
        }
        throw new IllegalArgumentException("Unknown OS: " + osName);
    }
}
