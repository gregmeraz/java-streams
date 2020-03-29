import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparatorLambdaExample {

    public static void main(String[] args) {

        Comparator<Integer> myIntegerComparator= (o1, o2) -> o1.compareTo(o2);
        int biggestNumber= myIntegerComparator.compare(21, 20);
        System.out.println(biggestNumber);



        List<People> peopleList= new ArrayList<>();
        People gregorio= new People("gregorio", "25", "meraz" );
        People gregorio2= new People("gregorio", "24", "meraz" );
        People jonathan1= new People("jonathan", "30", "ordaz");
        People jonathan2= new People("jonathan", "30", "ordaz");

        peopleList.add(gregorio);
        peopleList.add(gregorio2);
        peopleList.add(jonathan1);
        peopleList.add(jonathan2);

        List<People> underTwentyFivePeople= peopleList.stream().filter(people -> Integer.valueOf(people.getAge())<25).collect(Collectors.toList());

        underTwentyFivePeople.forEach(people -> System.out.println(people.getName()));

        Comparator<People> myPeopleComparator= (person1, person2) -> {
            if(person1.getName().equalsIgnoreCase(person2.getName())&&person1.getLastName().
                    equalsIgnoreCase(person2.getLastName())&&person1.getAge().equalsIgnoreCase(person2.getAge())){
                return 0;
            }
            return -1;
        };

        Comparator<People> ageComparator= (person1, person2) -> Integer.valueOf(person1.getAge())-Integer.valueOf(person2.getAge());

        List<People> sortedByAgePeople=peopleList.stream().sorted(ageComparator.reversed()).collect(Collectors.toList());

        sortedByAgePeople=sortedByAgePeople.stream().map(people -> new People(people.getName().toUpperCase(), people.getAge(), people.getLastName().toUpperCase())
        ).collect(Collectors.toList());



        sortedByAgePeople.forEach(System.out::println);

        int isTheSamePerson=myPeopleComparator.compare(gregorio, gregorio2);
        if(isTheSamePerson==0){
            System.out.println("is the same person");
        }
        else{
            System.out.println("they are different people");
        }


    }

}
