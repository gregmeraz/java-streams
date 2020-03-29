package operations;

import java.io.IOException;
import java.util.List;

public class StreamsFlatMap {

    static List<Person> people;

    static {
        try {
            people = MockData.getPeople();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
