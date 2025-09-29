package se.apiva;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.File;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        String validJSON = """
            {
                "id": 123,
                "name": "Alice",
                "email": "alice@grit.se"
            }
        """;

        String invalidJSON = """
            {
                "id": 0,
                "name": "",
                "email": 2
            }        
        """;

        try {
            validateJSON(validJSON);
            validateJSON(invalidJSON);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static void validateJSON(String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
        JsonSchema schema = schemaFactory.getSchema(
          new File("user-schema.json").toURI()
        );

        JsonNode node = mapper.readTree(json);
        Set<ValidationMessage> errors = schema.validate(node);

        if(errors.isEmpty()) {
            System.out.println("JSON validated without errors!");
        } else {
            System.out.println("JSON validated with errors: ");
            errors.forEach(msg -> System.out.println("  " + msg.getMessage()));
        }

    }
}









