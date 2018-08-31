package cn.data.collect.task;

public enum TaskState {
    RUNNING("R"),
    PAUSED("P"),
    PAUSING("S");

    private String state;

    TaskState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
