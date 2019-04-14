package core;

public class UiSelector {
    private String locator = "new UiSelector()";

    public UiSelector resourceId(String value) {
        this.locator += ".resourceId(\"" + value + "\")";
        return this;
    }
    public UiSelector className(String value) {
        this.locator += ".className(\"" + value + "\")";
        return this;
    }
    public UiSelector classNameMatches(String regex) {
        this.locator += ".classNameMatches(\"" + regex + "\")";
        return this;
    }
    public UiSelector text(String value) {
        this.locator += ".text(\"" + value + "\")";
        return this;
    }
    public UiSelector textContains(String value) {
        this.locator += ".textContains(\"" + value + "\")";
        return this;
    }
    public UiSelector index(String value) {
        this.locator += ".index(" + value + ")";
        return this;
    }
    public UiSelector clickable(boolean value) {
        this.locator += ".clickable(" + value + ")";
        return this;
    }
    public UiSelector checked(boolean value) {
        this.locator += ".checked(" + value + ")";
        return this;
    }
    public UiSelector enabled(boolean value) {
        this.locator += ".enabled(" + value + ")";
        return this;
    }
    public UiSelector description(String value) {
        this.locator += ".description(\"" + value + "\")";
        return this;
    }
    public UiSelector descriptionContains(String value) {
        this.locator += ".descriptionContains(\"" + value + "\")";
        return this;
    }
    public UiSelector descriptionMatches(String regex) {
        this.locator += ".descriptionMatches(\"" + regex + "\")";
        return this;
    }
    public UiSelector xpath(String xPath) {
        this.locator = xPath;
        return this;
    }

    public UiObject getUiObject() {
        return new UiObject(this.locator);
    }

}
