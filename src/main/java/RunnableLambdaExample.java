public class RunnableLambdaExample  {

    public static void main(String[] args) {
        //prior java 8
        Runnable runnable= new Runnable() {
            @Override
            public void run() {
                System.out.println("inside runnable 1");
            }
        };

        new Thread(runnable).start();

        Runnable runnable2= () -> System.out.println("inside runnable 2");

        new Thread(runnable2).start();
        new Thread(() -> System.out.println("Another way of implementing this shit!")).start();
    }



}
