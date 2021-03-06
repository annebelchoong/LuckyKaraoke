package modules;

import java.util.Scanner;

import adt.YongYang.SortedArrayList;
import adt.YongYang.SortedListInterface;
import client.LuckyKaraokeSystem;
import entity.Member;
import entity.Reservation;
import entity.Room;
import entity.TimeSlot;
import utility.Utility;

/**
 *
 * @author yongyangboon
 */
public class ReservationModule {
    public static Scanner scan = new Scanner(System.in);
    private static boolean exit;

    public static RoomModule roomMod = LuckyKaraokeSystem.roomList;

    public SortedListInterface<Room> roomList = roomMod.getRoomList();
    public SortedListInterface<TimeSlot> timeSlots = roomMod.getTimeSlots();
    public SortedListInterface<Reservation> reservationList = new SortedArrayList<>();

    public static adt.Guna.SortedListInterface<Member> memberList = MemberModule.getMemberList();

    public void reservationMenu() {
        Utility.clearScreen();
        do {
            Utility.clearScreen();
            System.out.println(printReservationHeader());
            showMainMenu();
            switch (scan.nextLine()) {
                case "1":
                    Utility.clearScreen();
                    System.out.println(printReservationHeader());
                    System.out.println("Reservation List: ");
                    System.out.println(reservationList);
                    Utility.cont();
                    break;
                case "2":
                    searchReservationMenu();
                    break;
                case "3":
                    Utility.clearScreen();
                    addReservationMenu();
                    Utility.cont();
                    Utility.clearScreen();
                    break;
                case "4":
                    editReservationMenu();
                    break;
                case "5":
                    deleteReservationMenu();
                    break;
                case "0":
                    Utility.clearScreen();
                    return;
                default:
                    Utility.clearScreen();
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        } while (!exit);
    }

    private void searchReservationMenu() {
        Utility.clearScreen();
        System.out.println(printReservationHeader());
        System.out.print("Enter Reservation No: ");
        String rNo = scan.nextLine();
        Reservation reservation = getReservationByID(Integer.parseInt(rNo));
        if (reservation == null) {
            System.out.println("No reservation found with " + rNo);
        } else {
            System.out.println(reservation);
        }
        Utility.cont();
    }

    private void addReservationMenu() {
        int roomNo = 0, timeSlot = 0;
        boolean goGetRoomNo = false, goGetMemberID = false, addDone = false;
        do {
            Utility.clearScreen();
            System.out.println(printReservationHeader());
            roomMod.viewRoomStatus();
            System.out.println("Which room would you like to use? [1] to [5]");
            System.out.print("\nChoose Room: ");
            String room = scan.nextLine();
            switch (room) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                    roomNo = Integer.parseInt(room);
                    goGetRoomNo = true;
                    Utility.clearScreen();
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        } while (!goGetRoomNo);
        do {
            Utility.clearScreen();
            System.out.println(printReservationHeader());
            roomMod.selectTimeSlot(roomNo);
            System.out.println("Which time? [1] to [6]");
            System.out.print("\nChoose TimeSlot: ");
            String time = scan.nextLine();
            switch (time) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                    timeSlot = Integer.parseInt(time);
                    goGetMemberID = true;
                    Utility.clearScreen();
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        } while (!goGetMemberID);
        Utility.clearScreen();
        do {
            System.out.println(printReservationHeader());
            System.out.print("Enter Member ID (e.g 101): ");
            String memberID = scan.nextLine();
            Member member = MemberModule.getMemberByID(Integer.parseInt(memberID));
            if (member != null) {
                Utility.clearScreen();
                System.out.println(printReservationHeader());
                int rNo = 10000 + reservationList.getNumOfEntries() + 1;
                reservationList.add(new Reservation(rNo,
                        roomList.getEntry(roomNo), timeSlots.getEntry(timeSlot), member));
                System.out.println("Reservation Successful. ");
                // System.out.println(reservation);
                System.out.println(reservationList.getEntry(reservationList.getNumOfEntries()));
                addDone = true;
            } else {
                Utility.clearScreen();
                System.out.println("Member ID does not exist. Please try again.");
            }
        } while (!addDone);
    }

    private void editReservationMenu() {
        Utility.clearScreen();
        System.out.println(printReservationHeader());
        System.out.println(reservationList);
        System.out.print("Enter Reservation ID that you want to edit (e.g 10001) : ");
        String rNo = scan.nextLine();
        Reservation reservation = getReservationByID(Integer.parseInt(rNo));
        if (reservation == null) {
            System.out.println("No reservation found with " + rNo);
            return;
        } else {

            System.out.println(reservation);
            System.out.println("\nWhat would you like to change for this reservation?");
            System.out.println("[1] Room");
            System.out.println("[2] Time");
            System.out.print("\nEnter choice: ");
            switch (scan.nextLine()) {
                case "1":
                    int roomNo = 0;
                    boolean getRoomDone = false;
                    do {
                        Utility.clearScreen();
                        System.out.println(printReservationHeader());
                        roomMod.viewRoomStatus();
                        System.out.println("Which room would you like to use? [1] to [5]");
                        System.out.print("\nEnter choice: ");
                        String room = scan.nextLine();
                        switch (room) {
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                            case "5":
                                roomNo = Integer.parseInt(room);
                                Reservation newReservation = new Reservation(
                                        reservation.getReservationNo(),
                                        roomList.getEntry(roomNo),
                                        reservation.getTimeSlot(),
                                        reservation.getMember());
                                if (reservationList.replace(reservationList.getPosition(reservation), newReservation)) {
                                    System.out.println("Edit Succesful.");
                                    System.out.println(newReservation);
                                } else {
                                    System.out.println("Error. Unable to edit reservation. Please try again.");
                                }
                                getRoomDone = true;
                                Utility.cont();
                                break;
                            default:
                                System.out.println("Invalid input, please try again.");
                                break;
                        }
                    } while (!getRoomDone);
                    break;
                case "2":
                    int timeSlot = 0;
                    boolean getTimeDone = false;
                    do {
                        Utility.clearScreen();
                        System.out.println(printReservationHeader());
                        roomMod.selectTimeSlot(2);
                        System.out.println("Which time? [1] to [6]");
                        System.out.print("\nEnter choice: ");
                        String time = scan.nextLine();
                        switch (time) {
                            case "1":
                            case "2":
                            case "3":
                            case "4":
                            case "5":
                            case "6":
                                timeSlot = Integer.parseInt(time);
                                Reservation newReservation = new Reservation(
                                        reservation.getReservationNo(),
                                        roomList.getEntry(2),
                                        timeSlots.getEntry(timeSlot),
                                        reservation.getMember());
                                if (reservationList.replace(reservationList.getPosition(reservation), newReservation)) {
                                    System.out.println("Edit Succesful.");
                                    System.out.println(newReservation);
                                } else {
                                    System.out.println("Error. Unable to edit reservation. Please try again.");
                                }
                                getTimeDone = true;
                                Utility.cont();
                                break;
                            default:
                                System.out.println("Invalid input, please try again.");
                                break;
                        }
                    } while (!getTimeDone);
                    break;
                default:
                    break;
            }
        }
    }

    private void deleteReservationMenu() {
        Utility.clearScreen();
        System.out.println(printReservationHeader());
        System.out.print("Enter Reservation No (e.g 10001): ");
        String rNo = scan.nextLine();
        Reservation reservation = getReservationByID(Integer.parseInt(rNo));
        if (reservation == null) {
            System.out.println("No reservation found with " + rNo);
            return;
        } else {
            System.out.println(reservation);
        }
        System.out.println("\nAre you sure you want to delete this reservation? ");
        System.out.println("[1] yes \n[2] no");
        System.out.print("Enter choice: ");
        switch (scan.nextLine()) {
            case "1":
                if (reservationList.remove(reservation)) {
                    System.out.println("Reservation Deleted.");
                    Utility.cont();
                }
                break;
            case "2":
                System.out.println("Cancelled");
                Utility.cont();
                break;
            default:
                System.out.println("Invalid input. Cancelled");
                Utility.cont();
                break;
        }
    }

    public void showMainMenu() {
        System.out.println("[1] View Reservations");
        System.out.println("[2] Search Reservations");
        System.out.println("[3] Add Reservation");
        System.out.println("[4] Edit Reservation");
        System.out.println("[5] Delete Reservation");
        System.out.println("[0] Return to Main Menu");
        System.out.print("\nEnter choice: ");
    }

    public Reservation getReservationByID(int rNo) {
        for (int i = 1; i <= reservationList.getNumOfEntries(); i++) {
            Reservation reservation = reservationList.getEntry(i);
            if (reservation.getReservationNo() == rNo) {
                return reservation;
            }
        }
        return null;
    }

    public void initReservationData() {
        reservationList.clear();
        reservationList.add(
                new Reservation(
                        10001,
                        roomList.getEntry(1),
                        timeSlots.getEntry(1),
                        memberList.getEntry(4)));
        reservationList.add(
                new Reservation(
                        10002,
                        roomList.getEntry(2),
                        timeSlots.getEntry(4),
                        memberList.getEntry(3)));
        reservationList.add(
                new Reservation(
                        10003,
                        roomList.getEntry(3),
                        timeSlots.getEntry(4),
                        memberList.getEntry(2)));
        reservationList.add(
                new Reservation(
                        10004,
                        roomList.getEntry(4),
                        timeSlots.getEntry(2),
                        memberList.getEntry(1)));
        reservationList.add(
                new Reservation(
                        10005,
                        roomList.getEntry(5),
                        timeSlots.getEntry(5),
                        memberList.getEntry(5)));
        reservationList.add(
                new Reservation(
                        10006,
                        roomList.getEntry(5),
                        timeSlots.getEntry(3),
                        memberList.getEntry(6)));
    }

    private String printReservationHeader() {
        return Utility.printHeaderLines() +
                "\t\tReservation Menu" +
                Utility.printHeaderLines();
    };

}