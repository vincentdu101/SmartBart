package trainapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vincentdu on 5/7/17.
 */
public class TrainIterator implements Iterator {

    List<Train> trainList;
    int position;

    public TrainIterator() {
        trainList = new ArrayList<>();
    }

    @Override
    public boolean hasNext() {
        return position < trainList.size();
    }

    @Override
    public Train next() {
        Train item = trainList.remove(position);
        trainList.add(position, item);
        position++;
        return item;
    }

    @Override
    public Train remove() {
        Train train = trainList.remove(position);
        position--;
        return train;
    }

    @Override
    public void add(Train item) {
        trainList.add(position, item);
        position++;
    }

}
