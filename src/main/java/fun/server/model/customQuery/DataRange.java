package fun.server.model.customQuery;

public class DataRange {
    private int group;
    private int subGroup;

    public DataRange(String value) {
        String[] parts = value.split("-");
        this.group = Integer.parseInt(parts[0]);
        this.subGroup = Integer.parseInt(parts[1]);
    }

    public int getGroup() {
        return group;
    }

    public int getSubGroup() {
        return subGroup;
    }

    @Override
    public String toString() {
        return group + "-" + subGroup;
    }
}
