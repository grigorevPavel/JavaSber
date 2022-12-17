package hw1.client;

import java.util.*;
import hw1.parser.Expression;
import lombok.var;

public interface Client {
    public enum Type {
        INDIVIDUAL {
            @Override
            public Client makeClient(HashMap<String, Expression> json) {
                Double inn = (Double) json.get("inn").extract();
                String name = (String) json.get("name").extract();

                return new IndividualClient(name, inn.intValue());
            }
        },

        LEGAL_ENTITY {
            @Override
            public Client makeClient(HashMap<String, Expression> json) {
                Double inn = (Double) json.get("inn").extract();
                String name = (String) json.get("name").extract();
                String owner = (String) json.get("owner").extract();

                return new LegalEntityClient(name, inn.intValue(), owner);
            }
        },

        HOLDING {
            @Override
            public Client makeClient(HashMap<String, Expression> json) {
                Double inn = (Double) json.get("inn").extract();
                String name = (String) json.get("name").extract();
                var parts_field = (ArrayList<Expression>) json.get("holding_parts").extract();
                var parts = new ArrayList<String>();

                for (Expression cur_part : parts_field) {
                    ((ArrayList) parts).add((String) cur_part.extract());
                }

                return new HoldingClient(name, inn.intValue(), parts);
            }
        };

        public abstract Client makeClient(HashMap<String, Expression> json);
    }

    public static Client create(HashMap<String, Expression> json) {
        Expression clientTypeField = json.get("clientType");
        String clientTypeName = (String)clientTypeField.extract();
        return Type.valueOf(clientTypeName).makeClient(json);
    }

    public Type getType();
    public void introduce();
}
