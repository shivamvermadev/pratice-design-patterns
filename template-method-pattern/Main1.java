class Coffee {
    void prepareRecipe() {
        boilWater();
        brewCoffeeGrinds();
        pourInCup();
        addSugarAndMilk();
    }

    void boilWater() {
        System.out.println("Boiling water");
    }

    void brewCoffeeGrinds() {
        System.out.println("Dripping coffee through filter");
    }

    void pourInCup() {
        System.out.println("pouring in cup");
    }
    
    void addSugarAndMilk() {
        System.out.println("adding sugar and milk");
    }
}


class Tea {
    void prepareRecipe() {
        boilWater();
        steepTeaBag();
        pourInCup();
        addLemon();
    }

    void boilWater() {
        System.out.println("Boiling water");
    }

    void steepTeaBag() {
        System.out.println("steeping the tea");
    }

    void pourInCup() {
        System.out.println("pouring in cup");
    }
    
    void addLemon() {
        System.out.println("adding lemon");
    }
}

public class Main1 {
    public static void main(String[] args) {
        
    }
}
