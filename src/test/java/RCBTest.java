import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class RCBTest extends TestRCBHelper {


    @BeforeClass
    public void ReadFile() throws IOException {
        ReadJsonFile();
    }

    @Test
    public void ValidateForeignTeamPlayers() throws IOException {
        SoftAssert softAssert = new SoftAssert();
        FindForeignTeamPlayers();
        softAssert.assertTrue(ForeignPlayersCount ==4,"only 4 foreign players are not in team");
        softAssert.assertAll();

    }

    @Test
    public void ValidateWicketKeeper(){
        SoftAssert softAssert = new SoftAssert();
        FindWicketKeeperPlayers();
        softAssert.assertTrue(WicketKeeperCount >=1,"No Wicket-keeper in the team");
        softAssert.assertAll();

    }

}
