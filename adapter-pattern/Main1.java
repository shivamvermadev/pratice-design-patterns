interface Duck {
    void quack();
    void fly();
}

class MallardDuck implements Duck {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}

interface Turkey {
    void gobble();
    void fly();
}

class WildTurkey implements Turkey {

    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("Im flying short distance");
    }
}

/**
 * Lets say we are lacking Duck objects and we want to use Turkey objects in their place.
 * We can't use the turkey objects directly because they have different interace.
 * So, we will be writing an adapter to achieve this.
 * So we are adapting Turkey to a Duck, i.e. kinda converting Turkey to Duck not exactly. 
 */
class TurkeyAdapter implements Duck { //first we need to implement the interface of the type we are adapting to.
    Turkey turkey; // next we need to get a reference to the abject that we are adapting. We are doing this via constructor.

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for(int i=0;i<5;i++) {
            turkey.fly();
        }
    }

}

public class Main1 {
    public static void main(String[] args) {
        MallardDuck duck = new MallardDuck();
        WildTurkey turkey = new WildTurkey();

        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("The turkey says...");
        turkey.gobble();
        turkey.fly();

        System.out.println("The duck says...");
        testDuck(duck);

        System.out.println("The turkeyadapter says...");
        testDuck(turkeyAdapter);

    }

    static void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }   
}