import java.io.Serializable;

public interface Item extends Serializable {

    public abstract String getDescription();

    public abstract void setDescription(String description);

    public abstract String getName();

    public abstract void setName(String name);

}
