package jmaster.io.evnloyalty.model;

public class Search {
    private Integer start;
    private Integer length;

    public Search() {
        start = 0;
        length = 20;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

}