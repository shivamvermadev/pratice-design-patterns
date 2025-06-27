import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

abstract class CaffeineBeverageWithHook {
    final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        if (customerWantsCondiments()) {
            addCondiments();
        }
    }

    abstract void brew();
    abstract void addCondiments();

    void boilWater() {
        System.out.println("boiling water");
    }

    void pourInCup() {
        System.out.println("pouring into cup");
    }

    boolean customerWantsCondiments() {
        return true;
    }
}

class CoffeeWithHook extends CaffeineBeverageWithHook {
    public void brew() {
        System.out.println("dripping coffee through filter");
    }

    public void addCondiments() {
        System.out.println("adding sugar and milk");
    }

    public boolean customerWantsCondiments() {
        String input = getUserInput();

        if(input.toLowerCase().startsWith("y")) {
            return true;
        } else {
            return false;
        }
    }

    private String getUserInput() {
        String ans = null;

        System.out.println("Enter y/n for condiments");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            ans = br.readLine();
        } catch(Exception e) {

        }
        
        if (Objects.isNull(ans)) {
            return "no";
        }
        return ans;
    }
}

public class Main3 {
    public static void main(String[] args) {
        CoffeeWithHook coffeeWithHook = new CoffeeWithHook();
        coffeeWithHook.prepareRecipe();
    }    
}
