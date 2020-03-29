import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ConsumerExample {

    public static void main(String[] args) throws IOException {

        List<Car> carsList=StuffWithPeopleAndJava8.getCars();

        Consumer<Car> printCarModel= car -> System.out.println(car.getModel());
        Consumer<Car> printCarMakeAndPrice= car -> System.out.println(car.getMake() + " " +car.getPrice() + " " + car.getYear());

        Predicate<Car> checkIfAnyMiata= car -> car.getModel().contains("Mazda");
        Predicate<Car> checkIf2010=car -> car.getYear()>2010;

        carsList.stream().filter(checkIfAnyMiata.and(checkIf2010)).forEach(printCarMakeAndPrice.andThen(printCarModel));

        carsList.stream().peek(System.out::println)
                .filter(car -> car.getMake().equalsIgnoreCase("Toyota") || car.getMake().equalsIgnoreCase("Cadillac"))
                .peek(car -> System.out.println("peeking: "+car))
               .sorted(Comparator.comparing(Car::getYear))
                .forEach(System.out::println);


        Function<Car, String> carModelsFunction= car -> car.getModel();
        Function<String, String> modelsToUpperCase= carModel -> carModel.toUpperCase();

        List<String> carModels=carsList.stream().map(carModelsFunction.andThen(modelsToUpperCase)).collect(Collectors.toList());
        carModels.forEach(System.out::println);




    }
}
