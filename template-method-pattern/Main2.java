/**
 * Template method pattern:
 * prepareRecipe is our template method as it serves a template for an algorithm. In this case algorithm for making caffeinated beverages.
 * In the template each step of algorithm is represented by a method.
 * Some method are handled by this class and some by the subclasses.
 * The method that need to be supplied by a subclass are declared as abstract.
 */
abstract class CaffeineBeverage {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();
    abstract void addCondiments();

    void boilWater() {
        System.out.println("boiling water");
    }

    void pourInCup() {
        System.out.println("pouring in cup");
    }
}

class Tea extends CaffeineBeverage {

    public void brew() {
        System.out.println("steeping the tea");
    }

    public void addCondiments() {
        System.out.println("adding lemon");
    }
}

class Coffee extends CaffeineBeverage {

    public void brew() {
        System.out.println("Dripping coffee through filter");
    }

    public void addCondiments() {
        System.out.println("adding sugar and milk");
    }
}



public class Main2 {
    public static void main(String[] args) {
        
    }
}
