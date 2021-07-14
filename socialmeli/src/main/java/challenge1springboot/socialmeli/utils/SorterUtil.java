package challenge1springboot.socialmeli.utils;

import challenge1springboot.socialmeli.globalconstants.Reference;
import java.util.Comparator;
import java.util.List;

public class SorterUtil<T> {

    public void sort(List<T> list, Comparator<T> c, String ORDER) {
        if (ORDER.equals(Reference.ORDER_ASC)) list.sort(c);
        else if (ORDER.equals(Reference.ORDER_DESC)) list.sort(c.reversed());
    }
}
