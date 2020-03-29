import com.google.common.collect.ImmutableList;
import com.google.common.io.Resources;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StuffWithPeopleAndJava8 {

    static  Function<List<Car>, Car> getMostExpensiveCar=carList -> carList.stream().max((car1, car2) -> Double.compare(car1.getPrice(), car2.getPrice())
    ).get();

    static Predicate<Person> isCarOwnedByFemale=person -> person.getGender().equalsIgnoreCase("female");
    static Predicate<Person> oldHags=person -> person.getAge()>95;

    static BiFunction<List<Person>, List<Car>, Map<Person, Car>>  oldHagsWithCarsBiFunction=(people, cars) -> {
       Map<Person, Car> oldHagsWithCars= new HashMap<>();
        people.forEach(person1 -> {
          oldHagsWithCars.put(person1, cars.stream().findAny().orElse(new Car(-1, "Default", "Default", "Default", -1, 0.0)));
       });
        return oldHagsWithCars;
    };

    public static void main(String[] args) throws IOException{
        List<Person> peopleList= getPeople();
        List<Car> cars= getCars();

        cars.forEach(System.out::println);

        peopleList.stream().map(person -> person.lastName.toUpperCase()).forEach(System.out::println);


        System.out.println("-------------------old females-------------------------------------");
        List<Person> oldFemales=peopleList.stream().filter(isCarOwnedByFemale.and(oldHags)).collect(Collectors.toList());
        Map<Person, Car> oldFemalesWithCars=oldHagsWithCarsBiFunction.apply(oldFemales, cars);
        oldFemalesWithCars.forEach((person, car) -> System.out.println(person +" "+ car));


        Map<Person, Car> carOwners=getCarOwners(peopleList, cars);
        //cadillacOwners.entrySet().forEach(System.out::println);

       Map<Person, Car> cadillacOwners= carOwners.entrySet().stream()
               .filter(personCarEntry -> personCarEntry.getValue().getMake().equalsIgnoreCase("Cadillac"))
               .sorted((personCarEntry, personCarEntry2) -> personCarEntry.getKey().getId() - personCarEntry2.getKey().getId())
               .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (car, car2) -> car, LinkedHashMap::new));
        cadillacOwners.entrySet().forEach(System.out::println);

       Map.Entry<Person, Car> youngestCadillacOwner=cadillacOwners.entrySet().stream()
               .max((personCarEntry, personCarEntry2) -> personCarEntry.getKey().getAge() - personCarEntry2.getKey().getAge()).get();
        System.out.println(youngestCadillacOwner);

/*
        Function<List<Car>, Map<String, Double>> createModelAndPriceMap= carList -> {
            HashMap<String, Double> modelAndPriceMap= new HashMap<>();
            carList.forEach(car -> {
                modelAndPriceMap.put(car.getModel(), car.getPrice());
            });

            return modelAndPriceMap;
        };

       Map<String, Double> modelAndPriceMap= createModelAndPriceMap.apply(cars);
        System.out.println(modelAndPriceMap);*/


      // cars.stream().collect(Collectors.toMap(o -> o.getId(), o -> o)).entrySet().forEach(System.out::println);

        Car expensiveCarId=getMostExpensiveCar.apply(cars);
        System.out.println(expensiveCarId);

    }


    public static Map<Person, Car> getCarOwners(List<Person> personList, List<Car> carList){
        Map<Person, Car> carOwners= new HashMap<>();
        int counter=1;
        while(counter<1000){
            carOwners.put(personList.get(counter), carList.get(counter));
            counter++;
        }
        return carOwners;
    }

    public static ImmutableList<Person> getPeople() throws IOException {
        InputStream inputStream = Resources.getResource("people.json").openStream();
        String json = IOUtils.toString(inputStream);
        Type listType = new TypeToken<ArrayList<Person>>() {
        }.getType();
        List<Person> people = new Gson().fromJson(json, listType);
        return ImmutableList.copyOf(people);
    }

    public static ImmutableList<Car> getCars() throws IOException {
        InputStream inputStream = Resources.getResource("cars.json").openStream();
        String json = IOUtils.toString(inputStream);
        Type listType = new TypeToken<ArrayList<Car>>() {
        }.getType();
        List<Car> cars = new Gson().fromJson(json, listType);
        return ImmutableList.copyOf(cars);
    }
}
