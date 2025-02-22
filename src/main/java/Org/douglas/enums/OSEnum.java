package Org.douglas.enums;

public enum OSEnum {
    WINDOWS("windows"),
    MAC("mac"),
    LINUX("linux");

    private final String osName;

    OSEnum(String osName) {
        this.osName = osName;
    }

    public static OSEnum fromString(String osName) {
        if (osName == null || osName.trim().isEmpty()) {
            throw new IllegalArgumentException("OS name cannot be null or empty.");
        }

        try {
            return OSEnum.valueOf(osName.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            String errorMessage = String.format("Invalid OS: '%s'. Valid options are: %s", osName, getValidOsNames());
            throw new IllegalArgumentException(errorMessage, e);
        }
    }

    private static String getValidOsNames() {
        StringBuilder validOs = new StringBuilder();
        for (OSEnum os : OSEnum.values()) {
            validOs.append(os.getOsName()).append(", ");
        }
        return validOs.length() > 0 ? validOs.substring(0, validOs.length() - 2) : "";
    }

    public String getOsName() {
        return osName;
    }
}
