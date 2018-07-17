package trainapp;

/**
 * Created by vincentdu on 4/30/17.
 */
public enum StationState {

    TRAIN_ARRIVED("Train arrived"),
    TRAIN_LEFT("Train just left"),
    CLOSED("Closed");

    String state;

    StationState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

}
