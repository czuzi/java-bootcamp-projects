package meetingrooms;

import java.util.ArrayList;
import java.util.List;

public class Office {

	private List<MeetingRoom> meetingRooms = new ArrayList<>();

	public void addMeetingRoom(String name, int width, int length) {
		meetingRooms.add(new MeetingRoom(name, width, length));
	}

	public List<MeetingRoom> getMeetingRooms() {
		return meetingRooms;
	}

	public List<MeetingRoom> getMeetingRoomsInReverseOrder() {
		List<MeetingRoom> res = new ArrayList<>();
		for (int i = (meetingRooms.size() - 1); i >= 0; i--) {
			res.add(meetingRooms.get(i));
		}
		return res;
	}

	public List<MeetingRoom> getEverySecondMeetingRoom(int number) {
		List<MeetingRoom> res = new ArrayList<>();
		if (number == 1) {
			for (int i = 0; i < meetingRooms.size(); i += 2) {
				res.add(meetingRooms.get(i));
			}
		}
		if (number == 2) {
			for (int i = 1; i < meetingRooms.size(); i += 2) {
				res.add(meetingRooms.get(i));
			}
		}
		return res;
	}

	public MeetingRoom getMeetingRoomWithGivenName(String name) {
		for (MeetingRoom meetingRoom: meetingRooms) {
			if (meetingRoom.getName().equals(name)) {
				return meetingRoom;
			}
		}
		return null;
	}
	public List<MeetingRoom> getMeetingRoomsWithGivenNamePart(String name) {
		List<MeetingRoom> res = new ArrayList<>();
		for(MeetingRoom meetingRoom: meetingRooms) {
			if (meetingRoom.getName().indexOf(name) > -1) {
				res.add(meetingRoom);
			}
		}
		return res;
	}

	public List<MeetingRoom> getMeetingRoomsWithAreaLargerThan(int area) {
		List<MeetingRoom> res = new ArrayList<>();
		for (MeetingRoom meetingRoom: meetingRooms) {
			if (meetingRoom.getArea() > area) {
				res.add(meetingRoom);
			}
		}
		return res;
	}
}
