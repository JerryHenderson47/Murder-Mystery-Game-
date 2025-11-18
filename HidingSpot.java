public class HidingSpot extends Thing{

    private boolean isOccupied;
    private int IDnumber;


    public HidingSpot(String name, String description, int IDnumber){
        super(name, description);
        this.IDnumber = IDnumber;
        this.isOccupied = false;
    }





    // getters and setters

    public int  getIDnumber(){
        return IDnumber;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }


}
