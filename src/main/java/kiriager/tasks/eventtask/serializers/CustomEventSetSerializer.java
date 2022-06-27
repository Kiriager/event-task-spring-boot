package kiriager.tasks.eventtask.serializers;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import kiriager.tasks.eventtask.domain.Event;

public class CustomEventSetSerializer extends StdSerializer<Set<Event>>{

    public CustomEventSetSerializer() {
        this(null);
    }

    public CustomEventSetSerializer(Class<Set<Event>> t) {
        super(t);
    }

    @Override
    public void serialize(Set<Event> value, JsonGenerator gen, SerializerProvider provider) 
            throws IOException {
        
        Set<Long> ids = new HashSet<Long>();
        for (Event event : value) {
            ids.add(event.getId());
        }
        gen.writeObject(ids);
    }
    
}
