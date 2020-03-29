package operations;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamsMap {

    static List<Person> people;

    static {
        try {
            people = MockData.getPeople();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Set<String> peopleNames= people.stream().map(Person::getFirstName).
                map(String::toUpperCase).
                filter(name-> name.startsWith("E")).
                collect(Collectors.toSet());
        peopleNames.forEach(System.out::println);
    }
}
