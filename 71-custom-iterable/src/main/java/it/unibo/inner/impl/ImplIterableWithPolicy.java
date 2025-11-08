package it.unibo.inner.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class ImplIterableWithPolicy<T> implements IterableWithPolicy<T>{
    private List<T> elements= new ArrayList<>();
    private Predicate<T> predicate;
    /* FIRST COSTRUCTOR
    public ImplIterableWithPolicy(T[] ListOfElements){
       this.elements=List.of(ListOfElements); //viene creata una lista i cui elementi sono quelli dell'array
    }*/
    public ImplIterableWithPolicy(final T[] ListOfElements){
        this(ListOfElements, new Predicate<T>(){ //creo una classe anonima
            public boolean test(T el){
                return true;
            }
        });
    }
    public ImplIterableWithPolicy(final T[] ListOfElements, final Predicate<T> predicate){
       this.elements=List.of(ListOfElements);
       this.predicate=predicate;
    }
    public void setIterationPolicy(final Predicate<T> filter){
        this.predicate=filter;
    }

    public class implIterator implements Iterator<T>{ //inner class
        /*il T deve essere lo stesso di ImplIterableWithPolicy, 
        quindi non va scritto implIterator <T>, 
        altrimenti è come se stessi sovrascrivendo il tipo generico */
        private int current;

        public implIterator(){
            this.current=0;
        }
        @Override
        public boolean hasNext(){
            while(elements.size()>current){
                if(predicate.test(elements.get(current))){
                    return true;
                }else {
                    current++;
                }
            }
            return false;
        }
        @Override
        public T next(){ //se c'è l'elemento successivo della lista mi sposto e lo prendo
            if(hasNext()){
                    return elements.get(current++);
            }else{
                throw new NoSuchElementException();
            }
        }
    }
    public implIterator iterator(){
        return new implIterator();
    }
}