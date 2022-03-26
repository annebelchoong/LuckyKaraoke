package client;

import entity.MemberModule;
import entity.ReservationModule;
import entity.SongModule;
import utility.Utility;

import java.util.Scanner;

/**
 *
 * @author annebelchoong
 */
public class LuckyKaraokeSystem {

    public static Scanner input = new Scanner(System.in);
    public static SongModule songList = new SongModule();
    public static MemberModule memberList = new MemberModule();
    public static ReservationModule reservationList = new ReservationModule();

    public static void main(String[] args) {
        Utility.clearScreen();
        welcomeScreen();
        initData();
        initialize();
    }

    public static void initData() {
        songList.initSongData();
        memberList.initMemberData();
        reservationList.initReservationData();
    }

    public static void welcomeScreen() {
        System.out.println("----------------------------------------------");
        System.out.println("|                                            |");
        System.out.println("|     Welcome to Lucky Karaoke System!       |");
        System.out.println("|                                            |");
        System.out.println("----------------------------------------------");
        System.out.println();
        System.out.println();
    }

    public static void homeMenu() {
        System.out.println(" MENU");
        System.out.println("=======");
        System.out.println("[1] Song Details");
        System.out.println("[2] Member Details");
        System.out.println("[3] Reservation Details");
        System.out.println("[0] Exit System \n\n");
        System.out.print("Enter your choice: ");
    }

    public static void initialize() {
        String menuChoice;
        boolean isQuit = false;

        do {
            homeMenu();
            menuChoice = input.nextLine();
            System.out.println();
            System.out.println();
            switch (menuChoice) {
                case "1":
                    songList.songLibraryMenu();
                    break;
                case "2":
                    memberList.memberMenu();
                    break;
                case "3":
                    reservationList.reservationMenu();
                    break;
                case "0":
                    System.out.println("Exiting System........");
                    isQuit = true;
                    break;
                default:
                    Utility.clearScreen();
                    System.out.println("Invalid input. Please try again. \n\n");
                    break;

            }

        } while (!isQuit);
    }

}
