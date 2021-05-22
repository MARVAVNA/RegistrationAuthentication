package services.enums;

import exception.EnumException;

public enum MainMenu {
    REGISTRATION("1"),
    AUTHORIZATION("2"),
    EXIT("3");
    public String value;

    MainMenu(String value) {
        this.value = value;
    }

    public static MainMenu getSelectedMenu(String input) throws EnumException {
        for (MainMenu menu : MainMenu.values()) {
            if (menu.value.equals(input)) {
                return menu;
            }
        }

        throw new EnumException(MainMenu.class.getSimpleName(), input);
    }
}
