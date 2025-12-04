import java.io.Serializable;

public class Thing implements Serializable {
    private static final long serialVersionUID = 1L;
    private String description;
    private String name;

    public Thing(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;

    }
}
