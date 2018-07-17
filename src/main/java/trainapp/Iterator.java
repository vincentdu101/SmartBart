package trainapp;

/**
 * Created by vincentdu on 5/7/17.
 */
public interface Iterator {

    public boolean hasNext();

    public Train next();

    public Train remove();

    public void add(Train train);

}

