/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.Annebel;

/**
 *
 * @author annebelchoong
 */
public interface QueueInterface<T extends Comparable<T>> {
    public void insert(T newEntry);
    public void makeFirst(T anEntry);
    public void shiftPriority();
    public T removeMin();
    public T peekMin();
    public boolean contains(T anEntry);
    public boolean isEmpty();
    public void clear();
}