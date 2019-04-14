import mobiletests.AppLanding;
import mobiletests.Functionality;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        Functionality.class,
        AppLanding.class
})
public class TestPrimer {
}
