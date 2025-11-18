public abstract class PuzzleWItness extends Witness {
    public String puzzleName;

    public PuzzleWItness(String name, String victimRelationship,
                         String information, int trustLevel, String description,
                         String puzzleName) {

        super(name, victimRelationship,information,trustLevel, description);

        this.puzzleName = puzzleName;
    }


    public abstract void interact();
    public abstract void play();



    // getters and setters
    public String getPuzzleName(){
        return puzzleName;
    }

    public void setPuzzleName(String puzzleName){
        this.puzzleName = puzzleName;
    }

}
