public class Suspect extends Character{

    private String job;
    private int age;
    private boolean criminalRecord;
    private String criminalRecordDetails; //must be gotten from completing some sort of side quest
    private String alibi;
    private String motive;
    private int suspectID;
    private String clothing; // May use in the future

    public Suspect(String name, Room startingRoom,
                   String job, int age,
                   boolean criminalRecord, String criminalRecordDetails,
                   String alibi, String motive,
                   int suspectID) {

        super(name, startingRoom);

        this.job = job;
        this.age = age;
        this.criminalRecord =criminalRecord;
        this.criminalRecordDetails = criminalRecordDetails;
        this.alibi = alibi;
        this.motive = motive;
        this.suspectID = suspectID;
    }

    @Override
    public void getDescription(){
        System.out.println("Description of Suspect");
    }
}



