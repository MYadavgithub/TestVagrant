import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Repeatable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TestRCBHelper {

    public static JsonPath jsonPath;
    public int ForeignPlayersCount = 0;
    public int WicketKeeperCount = 0;
    public List<String> ForeignPlayersNames = new ArrayList<>();
    public List<String> WicketKeeperNames = new ArrayList<>();


    public void ReadJsonFile() throws IOException {
        String filepath = System.getProperty("user.dir") + "/src/main/resources/TeamRCB.json";
        String TeamRCB = FileUtils.readFileToString(new File(filepath), StandardCharsets.UTF_8);
        //System.out.println(TeamRCB);
        jsonPath = new JsonPath(TeamRCB);
    }

    public void FindForeignTeamPlayers(){
        List<Object> players = new ArrayList<>();
        players = jsonPath.get("player");
        for(int i =0 ; i< players.size();i++){
            String country = jsonPath.getString("player["+i+"].country");
            if (!country.equalsIgnoreCase("India")){
                ForeignPlayersCount++;
                ForeignPlayersNames.add(jsonPath.get("player["+i+"].name"));
            }
        }
        System.out.println("Foreign players are:");
        for(String foreignPlayersName : ForeignPlayersNames){
            System.out.print(foreignPlayersName+", ");
        }

    }

    public void FindWicketKeeperPlayers(){
        List<String> roles = new ArrayList<>();
        roles = jsonPath.getList("player.role");
        for(int i =0 ; i < roles.size() ; i++){
            if(roles.get(i).equalsIgnoreCase("Wicket-keeper")){
                WicketKeeperCount++;
                WicketKeeperNames.add(jsonPath.get("player["+i+"].name"));
            }
        }
        System.out.println("Wicket-keeper:");
        for(String wicketkeepername : WicketKeeperNames){
            System.out.print(wicketkeepername+", ");
        }
    }
}
