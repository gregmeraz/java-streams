public class People {

    private String name;
    private String age;
    private String lastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public People(String name, String age, String lastName) {
        this.name = name;
        this.age = age;
        this.lastName = lastName;
    }


    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
