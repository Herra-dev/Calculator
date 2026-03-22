package com.herra.back;

import java.util.LinkedList;

public class MyLinkedList<E> extends LinkedList<E>{
    public int indexOf(E object, int from_index) {
        for(int i = from_index; i < this.size()-1; i++)
            if(this.get(i).equals(object))
                return i;

        return -1;
    }
}