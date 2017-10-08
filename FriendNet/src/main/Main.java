package main;

import static model.EventType.SCHOOL;

import model.Event;
import model.MyProfile;

public class Main {

  public static void main(String[] args) {
    MyProfile mainProfile = new MyProfile("Johnny Appleseed", 20, "Coquitlam", "SPL");
    MyProfile friend1 = new MyProfile("Paul Carter", 30, "London", "Network Systems");
    MyProfile friend2 = new MyProfile("Alan Turing", 24, "Coquitlam", "A Thinking Ape");
    MyProfile friend3 = new MyProfile("Ada Lovelace", 40, "Coquitlam", "AppNeta");
    MyProfile friend4 = new MyProfile("Alfonzo Church", 50, "New York City", "NYU Research");
    MyProfile friend5 = new MyProfile("Bob Ross", 44, "Chicago", "CBS");

    mainProfile.addFriend(friend1);
    mainProfile.addFriend(friend2);
    mainProfile.addFriend(friend3);
    mainProfile.addFriend(friend4);
    mainProfile.addFriend(friend5);

    mainProfile.unFriend("Paul Carter");

    mainProfile.printFriendNames();
    mainProfile.printFriendsNearMe();

    Event ev1 = new Event("IOT night", "May 20", "Sydney library", SCHOOL);
    Event ev2 = new Event("SYDCSS meetup", "June 20", "Sydney", SCHOOL);

    mainProfile.addEvent(ev1);
    mainProfile.addEvent(ev2);
    mainProfile.printEventSchedule(SCHOOL);
  }
}
