package testScripts;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TeamRCBTest {

    private Logger log = Logger.getGlobal();

    @Test
    public void maxFourForeignPlayerTest()  throws IOException, ParseException {

        List foreignPlayerList = (List) ((JSONArray) getRCBJson().get("player")).stream()
                .map(Object::toString).filter(p -> !p.toString().contains("India"))
                .collect(Collectors.toList());

        log.info("Count of foreign players: "+foreignPlayerList.size());
        Assert.assertEquals(foreignPlayerList.size(),4,"Team RCB has more than 4 foreign players");
    }

    @Test
    public void atleastOneWicketKeeperTest() throws IOException, ParseException
    {
        List wicketKeeperList = (List)((JSONArray)getRCBJson().get("player")).stream()
                .map(Object::toString).filter(p->p.toString().contains("Wicket-keeper"))
                .collect(Collectors.toList());

        log.info("Count of Wicket-keeper: "+wicketKeeperList.size());
        Assert.assertTrue(wicketKeeperList.size()>=1,"Team RCB has no Wicket-keeper");
    }

    private JSONObject getRCBJson() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        return (JSONObject)parser.parse(new FileReader("src/test/resources/rcb.json"));
    }
}
