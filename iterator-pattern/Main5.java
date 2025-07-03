import java.util.HashMap;


import java.util.ArrayList;
import java.util.Iterator;

interface Menu {
    Iterator<MenuItem> createIterator();
}

class MenuItem {
    String name;
    String description;
    boolean vegetarian;
    double price;
    public MenuItem(String name, String description, boolean vegetarian, double price) {
        this.name = name;
        this.description = description;
        this.vegetarian = vegetarian;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public double getPrice() {
        return price;
    }
}


class DinerMenuIterator implements Iterator<MenuItem> {
    MenuItem[] items;
    int position = 0;

    public DinerMenuIterator(MenuItem[] items) {
        this.items = items;
    }

    public boolean hasNext() {
        return position < items.length && items[position] != null;
    }

    public MenuItem next() {
        MenuItem menuItem = items[position];
        position++;
        return menuItem;
    }

    public void remove() {
        if (position <= 0 || position > items.length) {
            throw new IllegalStateException("You can't remove an item until you've called next()");
        }
        if (items[position - 1] != null) {
            for (int i = position - 1; i < items.length - 1; i++) {
                items[i] = items[i + 1];        
            }
            items[items.length - 1] = null; // Clear the last item
        }
    }
}

/* 
// this is not required in Java 8 and later, as ArrayList already implements Iterator
// but if you want to implement your own iterator, you can uncomment this part
// and use it instead of the built-in iterator
// Note: This is not necessary in Java 8 and later, as ArrayList already implements.
class PancakeHouseIterator implements Iterator<MenuItem> {
    ArrayList<MenuItem> items;
    int position = 0;

    public PancakeHouseIterator(ArrayList<MenuItem> items) {
        this.items = items;
    }

    public boolean hasNext() {
        return position < items.size();
    }

    public MenuItem next() {
        MenuItem menuItem = items.get(position);
        position++;
        return menuItem;
    }
}
*/

class DinerMenu implements Menu {
    static final int MAX_ITEMS = 6;
    int numberOfItems = 0;
    MenuItem[] menuItems;

    public DinerMenu() {
        menuItems = new MenuItem[MAX_ITEMS];
        addItem("Vegetarian BLT", "Fake bacon with lettuce & tomato on whole wheat bread", true, 2.99);
        addItem("BLT", "Bacon with lettuce & tomato on whole wheat bread", false, 2.99);
        addItem("Soup of the day", "Soup of the day with a side of potato salad", false, 3.29);
        addItem("Hotdog", "A hot dog with sauerkraut, relish, onions, topped with cheese", false, 3.05);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        if (numberOfItems >= MAX_ITEMS) {
            System.err.println("Sorry, menu is full! Can't add item to menu");
        } else {
            menuItems[numberOfItems] = menuItem;
            numberOfItems++;
        }
    }

    public Iterator<MenuItem> createIterator() {
        return new DinerMenuIterator(menuItems);
    }
}

class PancakeHouseMenu implements Menu {
    ArrayList<MenuItem> menuItems;

    public PancakeHouseMenu() {
        menuItems = new ArrayList<>();
        addItem("K&B's Pancake Breakfast", "Pancakes with scrambled eggs, and toast", true, 2.99);
        addItem("Regular Pancake Breakfast", "Pancakes with fried eggs, sausage", false, 2.99);
        addItem("Blueberry Pancakes", "Pancakes made with fresh blueberries", true, 3.49);
        addItem("Waffles", "Waffles with your choice of blueberries or strawberries", true, 3.59);
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.add(menuItem);
    }

    public Iterator<MenuItem> createIterator() {
        return menuItems.iterator();
    }
}

class CafeMenu implements Menu {
    HashMap<String, MenuItem> menuItems = new HashMap<>();
    public CafeMenu() {
        addItem("Veggie Burger and Air Fries", "Veggie burger on a whole wheat bun, lettuce, tomato, and fries", true, 3.99);
        addItem("Soup of the day", "A cup of the soup of the day, with a side salad", false, 3.69);
        addItem("Burrito", "A large burrito with whole pinto beans, salsa, guacamole", true, 4.29); 
    }

    public void addItem(String name, String description, boolean vegetarian, double price) {
        MenuItem menuItem = new MenuItem(name, description, vegetarian, price);
        menuItems.put(name, menuItem);
    }

    public Iterator<MenuItem> createIterator() {
        return menuItems.values().iterator();
    }
}

class Waitress {
    Menu pancakeHouseMenu;
    Menu dinerMenu;
    Menu cafeMenu;

    public Waitress(Menu pancakeHouseMenu, Menu dinerMenu, Menu cafeMenu) { // here waitress is accepting constructors of concrete classes rather than interface.problem
        this.pancakeHouseMenu = pancakeHouseMenu;
        this.dinerMenu = dinerMenu;
        this.cafeMenu = cafeMenu;
    }

    public void printMenu() {
        Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
        Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();
        Iterator<MenuItem> cafeIterator = cafeMenu.createIterator();

        System.out.println("MENU\n----\nBREAKFAST");
        printMenu(pancakeIterator);
        System.out.println("\nLUNCH");
        printMenu(dinerIterator);
        System.out.println("\nDINNER");
        printMenu(cafeIterator);
    }

    private void printMenu(Iterator<MenuItem> iterator) {
        while (iterator.hasNext()) {
            MenuItem menuItem = (MenuItem) iterator.next();
            System.out.print(menuItem.getName() + ", ");
            System.out.print(menuItem.getPrice() + " -- ");
            System.out.println(menuItem.getDescription());
        }
    }
}

public class Main5 {
    public static void main(String[] args) {
        Menu pancakeMenu = new PancakeHouseMenu();
        Menu dinerMenu = new DinerMenu();
        Menu cafeMenu = new CafeMenu();

        Waitress waitress = new Waitress(pancakeMenu, dinerMenu, cafeMenu);
        waitress.printMenu();
    }    
}