/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adt.Annebel;

/**
 *
 * @author annebelchoong
 * @param <T>
 */
public class PriorityQueue<T extends Comparable<T>> implements QueueInterface<T> {
    
   private PriorityNode<T> firstNode;
   private PriorityNode<T> lastNode;
   //private int numberOfEntries;

    public PriorityQueue() {
        firstNode = null;
        lastNode = null;
        //numberOfEntries = 0;
    }
   
    @Override
    public void insert(T newEntry) {
        PriorityNode newNode =new PriorityNode(0, newEntry, null);
        int prePriority = 0; 
        
        if(isEmpty()){
            firstNode = newNode;
            lastNode = newNode;
            firstNode.priority = 1;
        } else{
            prePriority = lastNode.priority;
            lastNode.next = newNode;
        }
            lastNode = newNode;
            lastNode.priority = prePriority + 1;
//            System.out.print(lastNode.priority);
//            System.out.print(lastNode.data);
    }

    @Override
    public void makeFirst(T anEntry) {
        PriorityNode nodeBefore = null;
        PriorityNode currentNode = firstNode;
        while(currentNode!= null && anEntry.compareTo((T)currentNode.data)!= 0){
        currentNode.priority++;
            nodeBefore = currentNode;
            currentNode = currentNode.next;
            if(currentNode!= null && anEntry.compareTo((T)currentNode.data)== 0){
                    currentNode.priority = 1;
                if(currentNode == firstNode){
                    insert(anEntry);
                } else{
                    nodeBefore.next = currentNode.next;
                    currentNode.next = firstNode;
                    firstNode = currentNode;
//                        currentNode.next.priority ++;
                }
            }      
        }
        
    }

    
    @Override
    public void shiftPriority() {
        PriorityNode currentNode = firstNode;
        while (!isEmpty()) {
            currentNode = currentNode.next;
            currentNode.priority++; 
        }
    }
    
    @Override
    public T removeMin() {
        T front = null;
        
        if(!isEmpty()){
            front = firstNode.data;
            firstNode = firstNode.next;
            firstNode.priority--;
            
            if (firstNode == null){ // there is no more next value after first Node
                lastNode = null;    // it means it is the end of the queue
            }
        }
        return front;
    }

    @Override
    public T peekMin() {
                T front = null;
        
        if (!isEmpty()){
            front = firstNode.data;
        }
        return front;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        PriorityNode tempNode = firstNode;

        while (!found && (tempNode != null)) {
            if (anEntry.compareTo((T) tempNode.data) <= 0) {
                found = true;
            } else {
                tempNode = tempNode.next;
            }
        }
        if (tempNode != null && tempNode.data.equals(anEntry)) {
          return true;
        } else {
          return false;
        }
    }
    
    @Override
    public boolean isEmpty() {
        return (firstNode == null && lastNode == null);
    }


    @Override
    public void clear() {
        firstNode = null;
        lastNode = null;
    }
    
    public String toString() {
        String outputStr = "";
        PriorityNode currentNode = firstNode;
        while (currentNode != null){
            outputStr += currentNode.data + "\n";
            currentNode = currentNode.next;
        }
        return outputStr;
    }
    
    public void transverse(T anEntry){
        PriorityNode currentNode = firstNode;
        while (currentNode != null){
            anEntry.equals(currentNode.data);
            System.out.print(currentNode.data);
            currentNode = currentNode.next;
        }
    }
    
    
//    public int getPriority(T anEntry){
//        
//    }

   





        
}


   
   
    