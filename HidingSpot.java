public class HidingSpot implements Item{

    private String name;
    private String description;
    private boolean isOccupied;
    private int IDnum;

    public HidingSpot(String name, String description, int IDnum){
        this.name = name;
        this.description = description;
        this.IDnum = IDnum;
        this.isOccupied = false;
    }





    // getters and setters
    public void setIDnum(int IDnum){
        this.IDnum = IDnum;
    }

    public int  getIDnum(){
        return IDnum;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }
    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
