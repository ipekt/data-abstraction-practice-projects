package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyProfile {

  private String name;
  private int age;
  private String currentLocation;
  private String workPlace;
  private List<MyProfile> friendsList;
  private List<Event> upcomingEvents;

  public MyProfile(String nm, int age, String locn, String work) {
    this.name = nm;
    this.age = age;
    this.currentLocation = locn;
    this.workPlace = work;
    this.friendsList = new ArrayList<>();
    this.upcomingEvents = new ArrayList<>();
  }

  // getters
  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public String getCurrentLocation() {
    return currentLocation;
  }

  public String getWorkPlace() {
    return workPlace;

  }

  public List<MyProfile> getFriendsList() {
    return friendsList;
  }

  public List<Event> getEventList() {
    return upcomingEvents;
  }

  public int upcomingEventNum() {
    return upcomingEvents.size();

  }

  // REQUIRES: f is not already in friendsList
  // MODIFIES: this
  // EFFECTS: consumes a MyProfile object, a friend f, and adds it to the friendsList
  public void addFriend(MyProfile f) {
    friendsList.add(f);
  }

  private boolean isFriendInList(String nm) {
    return friendsList
        .stream()
        .anyMatch(friend -> friend.name.equals(nm));
  }

  private boolean isEventInList(String nm) {
    return upcomingEvents
        .stream()
        .anyMatch(event -> event.getName().equals(nm));
  }

  private List<Event> getEventsNearMe() {
    return this.upcomingEvents
        .stream()
        .filter(event -> event.getLocation().equals(currentLocation))
        .collect(Collectors.toList());
  }

  // MODIFIES: this
  // EFFECTS: removes a friend with the given name from this. If removal is successful, return true, else
  //          return false
  public boolean unFriend(String nm) {
    if (isFriendInList(nm)) {
      friendsList.removeIf(friend -> friend.name.equals(nm));
      return true;
    }
    return false;
  }

  // REQUIRES: ev is not in upcomingEvents
  // MODIFIES: this
  // EFFECTS: adds the given event to the list of upcoming events
  public void addEvent(Event ev) {
    upcomingEvents.add(ev);
  }

  // MODIFIES: this
  // EFFECTS: removes an event with the given name. If removal is successful, return true, else return false
  public boolean removeEvent(String nm) {
    if (isEventInList(nm)) {
      upcomingEvents.removeIf(event -> event.getName().equals(nm));
      return true;
    }
    return false;
  }


  // EFFECTS: returns the number of events that are at the current location of this person
  public int eventNumNearMe() {
    return getEventsNearMe().size();
  }

  private List<Event> getSpecificEvent(EventType et) {
    return upcomingEvents
        .stream()
        .filter(event -> event.getEventType().equals(et))
        .collect(Collectors.toList());
  }

  // EFFECTS: returns the number of events of the given type et
  public int specificEventType(EventType et) {
    return getSpecificEvent(et).size();
  }

  // EFFECTS: prints events of  type et to the console
  //          Hint: is there a method you have already written that you can use?
  public void printEventSchedule(EventType et) {
    System.out.println(getSpecificEvent(et).toString());
  }

  // EFFECTS: prints out the list of friends that this MyProfile has
  public void printFriendNames() {
    List<String> nameList = friendsList
        .stream()
        .map(friend -> friend.name)
        .collect(Collectors.toList());
    System.out.println(nameList.toString());
  }

  // EFFECTS: prints out the names of friends who live at the same location associated with this profile
  public void printFriendsNearMe() {
    List<MyProfile> friendsNearMe = friendsList
        .stream()
        .filter(friend -> friend.currentLocation.equals(currentLocation))
        .collect(Collectors.toList());
    System.out.println(friendsNearMe.toString());
  }

  private boolean funcForProfile(String friendName, MyProfile profile) {
    if (profile.name.equals(friendName)) {
      return true;
    } else if (profile.friendsList.size() > 0) {
      List<MyProfile> clonedFriendList  = new ArrayList<>(profile.friendsList);
      return funcForList(friendName, clonedFriendList);
    } else {
      return false;
    }
  }

  private boolean funcForList(String friendName, List<MyProfile> clonedList) {
    if (clonedList.isEmpty()) {
      return false;
    } else {
      MyProfile firstItem = clonedList.get(0);
      if (funcForProfile(friendName, firstItem)) {
        return true;
      } else {
        clonedList.remove(firstItem);
        return funcForList(friendName, clonedList);
      }
    }
  }

  // EFFECTS: produces true if this profile has a friend with the given name,
  //          OR if any of this profile's friends has a friend with the given name
  //          Hint: use recursion!
  public boolean canFindPerson(String name) {
    List<MyProfile> clonedFriendList  = new ArrayList<>(friendsList);
    return funcForList(name, clonedFriendList);
  }
}
