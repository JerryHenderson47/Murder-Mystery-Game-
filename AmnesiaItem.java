public class AmnesiaItem implements Item{
    private String attachedMemory;
    private int orderNum;
    private String description;
    private String name;

    public AmnesiaItem(String name, String description ,
                       String attachedMemory,int orderNum){

        this.name = name;
        this.description = description;
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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


}
