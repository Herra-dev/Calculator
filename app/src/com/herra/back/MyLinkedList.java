package com.herra.back;

import java.util.LinkedList;


/**
 * <h3>MyLinkedList</h3>
 * {@link com.herra.back.MyLinkedList}
 * <p>
 * {@link java.util.LinkedList}, not having method to search an element in a List{@code LinkedList} from a specific index, this class is here for that.
 * 
 * @author Heriniaina
 */
public class MyLinkedList<E> extends LinkedList<E>{

/**
 * This method is used to search a specific element in this {@code List}
 * 
 * @param object {@code the type of element in this List}
 * @param from_index {@code where the search must begin}
 * 
 * @return index of {@code object} from this list, returns -1 if no element was found
 * 
 * @author Heriniaina {@link https://github.com/Herra-dev}
 */
    public int indexOf(E object, int from_index) {
        for(int i = from_index; i < this.size()-1; i++)
            if(this.get(i).equals(object))
                return i;

        return -1;
    }
}