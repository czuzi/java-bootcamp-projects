package meetingrooms;

import java.util.List;
import java.util.Scanner;

public class MeetingRoomController {

	Scanner sc = new Scanner(System.in);
	Office office = new Office();

	private boolean toContinue = true;


	public static void main(String[] args) {
		MeetingRoomController meetingRoomController = new MeetingRoomController();
		meetingRoomController.runMenu();
	}

	private void runMenu() {
		System.out.println("Welcome!");
		do {
			if (office.getMeetingRooms().isEmpty()) {
				addMeetingRooms();
			}
			printMenu();
			System.out.print("Enter your choose: ");
			int userChoose = sc.nextInt();
			sc.nextLine();
			handleChoose(userChoose);
		}
		while (toContinue);
	}

	private void addMeetingRooms() {
		System.out.println("Enter the amount of meeting rooms you want to add:");
		int meetingRoomsToAdd = sc.nextInt();
		sc.nextLine();
		for(int i = 0; i < meetingRoomsToAdd; i++) {
			System.out.print("Enter the name of the meeting room: ");
			String name = sc.nextLine();
			System.out.print("Enter the width of the meeting room: ");
			int width = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter the length of the meeting room: ");
			int length = sc.nextInt();
			sc.nextLine();
			office.addMeetingRoom(name, width, length);
			System.out.println("Meeting room added successfully");
		}
	}

	private void printMenu() {
		System.out.println("""
				1. Meeting rooms in order
				2. Meeting rooms in reverse order
				3. Every second meeting room
				4. Meeting rooms areas
				5. Search for the exact name
				6. Search for a part of the name
				7. Search based on area
				8. Add more meeting rooms
				9. Exit""");
	}

	private void handleChoose(int choose) {
		switch (choose) {
			case 1 -> {
				System.out.println("\nList of meeting rooms in order:");
				printMeetingRoomNames(office.getMeetingRooms());
				System.out.println();
			}
			case 2 -> {
				System.out.println("\nList of meeting rooms in reverse order:");
				printMeetingRoomNames(office.getMeetingRoomsInReverseOrder());
				System.out.println();
			}
			case 3 -> {
				System.out.println("\nType 1 to list every odd numbered meeting room, and 2 to every even");
				int evenOrOdd = sc.nextInt();
				sc.nextLine();
				System.out.println("\nList of every second meeting room:");
				printMeetingRoomNames(office.getEverySecondMeetingRoom(evenOrOdd));
				System.out.println();
			}
			case 4 -> {
				System.out.println("\nList of the meeting rooms in details:");
				printMeetingRooms(office.getMeetingRooms());
				System.out.println();
			}
			case 5 -> {
				System.out.println("\nEnter the name of the meeting room you:");
				String nameToSearch = sc.nextLine();
				printMeetingRoom(office.getMeetingRoomWithGivenName(nameToSearch));
				System.out.println();
			}
			case 6 -> {
				System.out.println("\nEnter a part of a name you want to search for:");
				String namePartToSearch = sc.nextLine();
				printMeetingRooms(office.getMeetingRoomsWithGivenNamePart(namePartToSearch));
				System.out.println();
			}
			case 7 -> {
				System.out.println("\nEnter the minimum area to list meeting rooms:");
				int areaToFilter = sc.nextInt();
				sc.nextLine();
				printMeetingRooms(office.getMeetingRoomsWithAreaLargerThan(areaToFilter));
				System.out.println();
			}
			case 8 -> addMeetingRooms();
			case 9 -> {
				System.out.println("auf Wiedersehen");
				toContinue = false;
			}
			default -> System.out.println("Invalid option");
		}
	}

	private void printMeetingRoomNames(List<MeetingRoom> meetingRooms) {
		for (MeetingRoom meetingRoom: meetingRooms) {
			System.out.println(meetingRoom.getName());
		}
	}

	private void printMeetingRooms(List<MeetingRoom> meetingRooms) {
		for (MeetingRoom meetingRoom: meetingRooms) {
			printMeetingRoom(meetingRoom);
		}
	}

	private void printMeetingRoom(MeetingRoom meetingRoom) {
		System.out.println("Name: " + meetingRoom.getName() + "\n" +
				"width: " + meetingRoom.getWidth() + "\n" +
				"length: " + meetingRoom.getLength() + "\n" +
				"area: " + meetingRoom.getArea());
	}
}
