package little.old.me.shared.infra.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;

public class TrimStringModule extends SimpleModule {

    public TrimStringModule() {
        // Register the custom deserializer for all String fields
        addDeserializer(String.class, new StringTrimmerDeserializer());
    }

    public class StringTrimmerDeserializer extends JsonDeserializer<String> {
        @Override
        public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String value = p.getValueAsString();
            return (value != null) ? value.trim() : null;
        }
    }
}
