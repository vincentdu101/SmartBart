package trainapp;

import java.time.LocalDateTime;
import java.util.List;

public class TrainStationProgress {

    private Integer id;
    private Integer trainId;
    private Integer stationId;
    private boolean active;
    private Direction direction;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public TrainStationProgress(Integer id,
                 Integer trainId,
                 Integer stationId,
                 String directionString,
                 boolean active,
                 LocalDateTime createdAt,
                 LocalDateTime modifiedAt) {
        this.id = id;
        this.trainId = trainId;
        this.stationId = stationId;
        this.direction = Direction.findDirection(directionString);
        this.active = active;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTrainId() {
        return trainId;
    }

    public void setTrainId(Integer trainId) {
        this.trainId = trainId;
    }

    public Integer getStationId() {
        return stationId;
    }

    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
