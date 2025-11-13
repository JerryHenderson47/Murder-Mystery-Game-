public abstract class Character {
    private String name;
    protected Room currentRoom;

    public Character() {}

    //constructor
    public Character(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
    }


    public abstract void getDescription();
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
