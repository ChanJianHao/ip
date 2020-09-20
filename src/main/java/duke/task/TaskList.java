package duke.task;

import java.util.ArrayList;

/**
 * TaskList containing all the tasks.
 */
public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void removeTask(int taskNum) {
        list.remove(taskNum);
    }

    public int getTotal() {
        return list.size();
    }
}
