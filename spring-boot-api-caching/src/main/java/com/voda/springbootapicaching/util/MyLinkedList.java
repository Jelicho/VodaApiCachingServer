package com.voda.springbootapicaching.util;

public class MyLinkedList<E> {
    int size = 0;
    MyLinkedList.Node<E> first;
    MyLinkedList.Node<E> last;

    public MyLinkedList(){};

    public static class Node<E> {
        E item;
        MyLinkedList.Node<E> next;
        MyLinkedList.Node<E> prev;

        Node(MyLinkedList.Node<E> prev, E element, MyLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }

        public E getItem(){
            return item;
        }
    }

    public int size(){return size;}

    public Node addLast(E key){
        final Node<E> l=last;
        Node newNode = new Node<>(l, key, null);
        if(first==null){
            first = newNode;
        }
        last = newNode;
        size++;
        return newNode;
    }
    public Node addLast(Node<E> node){
        final Node<E> l=last;
        if(first==null){
            first = node;
        }
        last = node;
        size++;
        return node;
    }
    public void delete(Node<E> node){
        final Node prev = node.prev;
        final Node next = node.next;
        node.prev = null;
        node.next = null;

        if(prev!=null && next!=null){
            prev.next=next;
            next.prev=prev;
        }else if(prev!=null){
            prev.next=null;
            last = prev;
        }else if(next!=null){
            next.prev=null;
            first = next;
        }else{
            first = null;
            last = null;
        }
        size--;
    }
    public void clear(){
        for (MyLinkedList.Node<E> x = first; x != null; ) {
            MyLinkedList.Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = last = null;
        size = 0;
    }
}
