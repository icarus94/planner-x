package rs.fon.plannerx.core.task.domain;

public enum TaskListPermission {
    ALL,
    READ,
    READ_UPDATE;

    public String format() {
        String name = "unknown";
        switch (this) {
            case ALL:
                name = "All privileges";
                break;
            case READ:
                name = "Read Only";
                break;
            case READ_UPDATE:
                name = "Read & Update";
                break;
        }
        return name;
    }
}
