public class AmnesiaItem extends Item {
    private String attachedMemory;
    private int orderNum;


    public AmnesiaItem(String name, String description ,
                       String attachedMemory,int orderNum){

        super(name, description);
        this.attachedMemory = attachedMemory;
        this.orderNum = orderNum;
    }




    // getters and setters
    public String getAttachedMemory() {
        return attachedMemory;
    }

    public int getOrderNum() {
        return orderNum;
    }





}
