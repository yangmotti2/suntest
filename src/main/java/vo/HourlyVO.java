package vo;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;

public class HourlyVO {
    private List<String> time;
    private List<String> direct_radiation;

    public List<String> getDirect_radiation() {
		return direct_radiation;
	}

	public void setDirect_radiation(List<String> direct_radiation) {
		this.direct_radiation = direct_radiation;
	}

	// Getter와 Setter
    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }
}
