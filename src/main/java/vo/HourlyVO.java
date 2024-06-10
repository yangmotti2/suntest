package vo;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;

public class HourlyVO {
    private List<String> time;

    // Getter와 Setter
    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
