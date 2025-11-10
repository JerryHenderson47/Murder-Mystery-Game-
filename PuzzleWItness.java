public abstract class PuzzleWItness extends Witness {
    public String puzzleName;

    public PuzzleWItness(String name,
                         String victimRelationship,
                         int trustLevel, String puzzleName){
        super(name,  victimRelationship,  trustLevel);


        this.puzzleName = puzzleName;
    }


    public abstract void playPuzzle();
}
