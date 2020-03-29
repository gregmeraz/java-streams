import java.io.IOException;
import java.util.List;
import java.util.function.UnaryOperator;

public class UnaryOperatorExample {

   static  UnaryOperator<String> convertStringToUpperCase= s -> s.toUpperCase();

    public static void main(String[] args) throws IOException {

        List<Car> cars= StuffWithPeopleAndJava8.getCars();

        cars.stream().forEach(car -> convertStringToUpperCase.apply(car.getModel()));

    }
}
