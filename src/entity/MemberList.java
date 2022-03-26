
package entity;

import adt.Guna.*;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Gunaseelan
 */
public class MemberList extends Member {

    
    
    Scanner scanner = new Scanner(System.in);
    SortedListInterface<Member> memList = new SortedLinkedList<>();

    public MemberList(int MemberId, String MemberName, String Email, int numberOfPoints) {
        super(MemberId, MemberName, Email, numberOfPoints);
    }

    public MemberList() {
        
    }
    
    public void errorMessage(){
        System.out.println("Please enter between Option 1 until Option 6");
    }
    
    
    public void addMember() {
       System.out.println("Enter the details of the member to add.");
        int id = 106;
        id += 1;
                
        System.out.print("Member Name: ");
        String name = scanner.nextLine();
        
                
        System.out.print("Member Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Member Points: ");
        int points = scanner.nextInt();

        memList.add(new Member(id, name, email, points)); 
    }

    public void deleteMember() {
        System.out.println("Enter the member id to remove.");
                
        System.out.print("Member Id: ");
        int id = scanner.nextInt();
        
        Iterator<Member> m = memList.getIterator();
      
        while(m.hasNext()){
            Member member = m.next();
            
            if(member.getMemberId() == id){
                memList.remove(member);
            }    
        }
    }
    
    public void chooseMember(){
        System.out.println("Enter the member id to select.");
                
        System.out.print("Member Id: ");
        int id = scanner.nextInt();
        
        Iterator<Member> m = memList.getIterator();
        
        while(m.hasNext()){
            Member member = m.next();
            
            if(member.getMemberId() == id){
                System.out.print("\n");
                System.out.println("---------Member---------");
                System.out.println("Member ID: " + member.getMemberId());
                System.out.println("Member Name: " + member.getMemberName());
                System.out.println("Member Email: " + member.getEmail());
                System.out.println("Member Points: " + member.getPoints());
            }
        }
    }
    
    public void highestPoints(){
        
        Iterator<Member> m = memList.getIterator();
        int max = 0;
        int maxid = 0;
        String maxName = "";
        String maxEmail = "";
        
        
        while(m.hasNext()){
            Member member = m.next();
            
            if(member.getPoints() > max){
                max = member.getPoints();
                maxid = member.getMemberId();
                maxName = member.getMemberName();
                maxEmail = member.getEmail();
            } 
        }
        
        System.out.println("---------Highest points---------");
        System.out.println("Member ID: " + maxid);
        System.out.println("Member Name: " + maxName);
        System.out.println("Member Email: " + maxEmail);
        System.out.println("Member Points: " + max);
    }
    
    public void lowestPoints(){
        
        Iterator<Member> m = memList.getIterator();
        int min = 9999;
        int minid = 0;
        String minName = "";
        String minEmail = "";
        
        
        while(m.hasNext()){
            Member member = m.next();
            
            if(member.getPoints() < min){
                min = member.getPoints();
                minid = member.getMemberId();
                minName = member.getMemberName();
                minEmail = member.getEmail();
            }
        }
        
        System.out.println("---------Lowest points---------");
        System.out.println("Member ID: " + minid);
        System.out.println("Member Name: " + minName);
        System.out.println("Member Email: " + minEmail);
        System.out.println("Member Points: " + min);
        System.out.print("\n");
    }
    
    public void rawData(){
        memList.add(new Member(101, "John", "john@gmail.com", 1000));
        memList.add(new Member(102, "Joseph", "joseph@gmail.com", 1200));
        memList.add(new Member(103, "Joshua", "joshua@gmail.com", 500));
        memList.add(new Member(104, "Jack", "jack@gmail.com", 900));
        memList.add(new Member(105, "Jill", "jill@gmail.com", 2000));
        memList.add(new Member(106, "Jim", "jim@gmail.com", 2500));
    }
    

    public void viewMember() {
        
        Iterator<Member> ite = memList.getIterator();
        System.out.println("Member ID\tMember Name\tMember Email\t\tMember Points");
        System.out.println("---------------------------------------------------------------------");
        while (ite.hasNext()){
            Member mem = ite.next();
            System.out.printf("%-15d %-15s %-23s %-15d\n", mem.getMemberId(), mem.getMemberName(), mem.getEmail(),mem.getPoints());
        }
        
        System.out.println("\nTotal Members: " + memList.getNumberOfEntries());
    }
    
    
    
    
    
    
    
    
    
}
