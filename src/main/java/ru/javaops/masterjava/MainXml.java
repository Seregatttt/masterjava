package ru.javaops.masterjava;

import com.google.common.io.Resources;
import one.util.streamex.StreamEx;
import ru.javaops.masterjava.xml.schema.*;
import ru.javaops.masterjava.xml.util.JaxbParser;
import ru.javaops.masterjava.xml.util.Schemas;
import ru.javaops.masterjava.xml.util.StaxStreamProcessor;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainXml {

    public static void main(String[] args) throws Exception {

        URL payloadUrl = Resources.getResource("payload.xml");

        List<User> users = parseByJaxb("topjava", payloadUrl);
        users.forEach(System.out::println);
    }

    private static List<User> parseByJaxb(String projectName, URL payloadUrl) throws Exception {
        JaxbParser parser = new JaxbParser(ObjectFactory.class);
        parser.setSchema(Schemas.ofClasspath("payload.xsd"));
        Payload payload;
        try (InputStream is = payloadUrl.openStream()) {
            payload = parser.unmarshal(is);
        }
        return StreamEx.of(payload.getUsers().getUser())
                .filter(new Predicate<User>() {
                    @Override
                    public boolean test(User u) {
                        List<Group> groups = u.getGroup();
                        for (Group group : groups) {
                            return group.getProject().getNameProject().equals(projectName);
                        }
                        return true;
                    }
                })
                .peek(user -> System.out.println(user.getFullName()))
                .sorted(Comparator.comparing(new Function<User, String>() {
                    @Override
                    public String apply(User user) {
                        return user.getFullName();
                    }
                }))
                .collect(Collectors.toList());
    }
}
