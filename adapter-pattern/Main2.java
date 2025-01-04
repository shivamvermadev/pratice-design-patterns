import java.util.Enumeration;
import java.util.Iterator;


//adapting Enumeration to an Iterator
class EnumerationIterator implements Iterator<Object> {

    Enumeration<?> enumeration;

    public EnumerationIterator(Enumeration<?> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
       return enumeration.hasMoreElements();
    }

    @Override
    public Object next() {
       return enumeration.nextElement();
    }

}

public class Main2 {
    public static void main(String[] args) {
        
    }
}
