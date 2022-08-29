package meetingrooms;

import java.util.List;
import java.util.Scanner;

public class MeetingRoomController {

	Scanner sc = new Scanner(System.in);
	Office office = new Office();


	public static void main(String[] args) {
		MeetingRoomController meetingRoomController = new MeetingRoomController();
		meetingRoomController.runMenu();
	}

	private void runMenu() {
		System.out.print("Welcome! Enter the number of meeting rooms you want to add: ");
		int meetingRoomsToAdd = sc.nextInt();
		sc.nextLine();
		addMeetingRooms(meetingRoomsToAdd);
		printMenu();
		System.out.print("Enter your choose: ");
		int userChoose = sc.nextInt();
		sc.nextLine();
		handleChoose(userChoose);
	}

	private void addMeetingRooms(int meetingRoomsToAdd) {
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
				8. Exit""");
	}

	private void handleChoose(int choose) {
		switch (choose) {
			case 1:
				printMeetingRoomNames(office.getMeetingRooms());
				break;
			case 2:
				printMeetingRoomNames(office.getMeetingRoomsInReverseOrder());
				break;
			case 3:
				System.out.println("Type 1 to list every odd numbered meeting room, and 2 to every even");
				int evenOrOdd = sc.nextInt();
				sc.nextLine();
				printMeetingRoomNames(office.getEverySecondMeetingRoom(evenOrOdd));
				break;
			case 4:
				printMeetingRooms(office.getMeetingRooms());
				break;
			case 5:
				System.out.println("Enter the name of the meeting room you:");
				String nameToSearch = sc.nextLine();
				printMeetingRoom(office.getMeetingRoomWithGivenName(nameToSearch));
				break;
			case 6:
				System.out.println("Enter a part of a name you want to search for:");
				String namePartToSearch = sc.nextLine();
				printMeetingRooms(office.getMeetingRoomsWithGivenNamePart(namePartToSearch));
				break;
			case 7:
				System.out.println("Enter the minimum area to list meeting rooms:");
				int areaToFilter = sc.nextInt();
				sc.nextLine();
				printMeetingRooms(office.getMeetingRoomsWithAreaLargerThan(areaToFilter));
				break;
			case 8:
				System.out.println("auf Wiedersehen");
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
		System.out.println("Name: " + meetingRoom.getName() +
				"width: " + meetingRoom.getWidth() +
				"length: " + meetingRoom.getLength() +
				"area: " + meetingRoom.getArea());
	}
}
