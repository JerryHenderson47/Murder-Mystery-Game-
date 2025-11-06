public abstract class PuzzleWItness extends Witness {
    public String puzzleName;

    public PuzzleWItness(String name, Room currentRoom ,
                         String victimRelationship, String information,
                         int trustLevel, String puzzleName){
        super(name, currentRoom, victimRelationship, information, trustLevel);
        this.puzzleName = puzzleName;
    }


    public abstract void playPuzzle();
}
