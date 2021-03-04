package chessfigureproductor.models.table;

public class FigureStepData {
    private Integer number;
    private Integer current;
    private Integer destination;

    public FigureStepData(Integer number, Integer current, Integer destination) {
        this.number = number;
        this.current = current;
        this.destination = destination;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getDestination() {
        return destination;
    }

    public void setDestination(Integer destination) {
        this.destination = destination;
    }
}
