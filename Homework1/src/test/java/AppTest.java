import hw1.parser.*;
import hw1.client.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void testIndividual() throws Exception {
        Parser parser = new Parser("../resources/individual.json");
        Client client = Client.create(parser.getJson());
        Assertions.assertEquals(client.getType(), Client.Type.INDIVIDUAL);
    }

    @Test
    public void testLegalEntity() throws Exception {
        Parser parser = new Parser("../resources/legal_entity.json");
        Client client = Client.create(parser.getJson());
        Assertions.assertEquals(client.getType(), Client.Type.LEGAL_ENTITY);
    }

    @Test
    public void testHolding() throws Exception {
        Parser parser = new Parser("../resources/holding.json");
        Client client = Client.create(parser.getJson());
        Assertions.assertEquals(client.getType(), Client.Type.HOLDING);
    }
}
