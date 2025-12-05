import java.io.Serializable;

public  class Item extends Thing implements Serializable  {
    private static final long serialVersionUID = 1L;
    public Item(String name, String description) {
        super(name, description);
    }

}



