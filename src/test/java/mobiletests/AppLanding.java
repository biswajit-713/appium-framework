package mobiletests;

import api.Platform;
import api.apps.wunderlist.Wunderlist;
import core.managers.TestManager;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AppLanding extends TestManager {
    public static Wunderlist wunderlist = Platform.app.wunderlist;

    @Test
    @Ignore
    public void verifyLandingPageTitles(){
        Map<String, HashMap<String, String>> landingPageTitles = new HashMap<>();
        HashMap<String, String> description = new HashMap<>();

        description.put("title", "Get stuff done easily");
        description.put("description", "Organize your work, share a shopping list or plan your next trip with Wunderlist.");
        landingPageTitles.put("1", new HashMap<>(description));

        description.put("title", "Collaborate with anyone");
        description.put("description", "Ask a question, leave feedback and add Comments to any of your to-dos.");
        landingPageTitles.put("2", new HashMap<>(description));

        description.put("title", "Free on all your devices");
        description.put("description", "No matter where you are, Wunderlist is the easiest way to get stuff done.");
        landingPageTitles.put("3", new HashMap<>(description));

        wunderlist.landingPage.swipeLeft();
        assert landingPageTitles.get("2").get("title").equals(wunderlist.landingPage.getTitleText());
        assert landingPageTitles.get("2").get("description").equals(wunderlist.landingPage.getTitleDescription());

        wunderlist.landingPage.swipeLeft();
        assert landingPageTitles.get("3").get("title").equals(wunderlist.landingPage.getTitleText());
        assert landingPageTitles.get("3").get("description").equals(wunderlist.landingPage.getTitleDescription());

        wunderlist.landingPage.swipeLeft();
        assert landingPageTitles.get("3").get("title").equals(wunderlist.landingPage.getTitleText());
        assert landingPageTitles.get("3").get("description").equals(wunderlist.landingPage.getTitleDescription());

        wunderlist.landingPage.swipeRight();
        assert landingPageTitles.get("2").get("title").equals(wunderlist.landingPage.getTitleText());
        assert landingPageTitles.get("2").get("description").equals(wunderlist.landingPage.getTitleDescription());

        wunderlist.landingPage.swipeRight();
        assert landingPageTitles.get("1").get("title").equals(wunderlist.landingPage.getTitleText());
        assert landingPageTitles.get("1").get("description").equals(wunderlist.landingPage.getTitleDescription());

        wunderlist.landingPage.swipeRight();
        assert landingPageTitles.get("1").get("title").equals(wunderlist.landingPage.getTitleText());
        assert landingPageTitles.get("1").get("description").equals(wunderlist.landingPage.getTitleDescription());

    }
}
