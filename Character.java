public class Character {
    private String name;
    protected Room currentRoom;

    //constructor
    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
    }

    //getters
    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    //setter for room
    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }


}
