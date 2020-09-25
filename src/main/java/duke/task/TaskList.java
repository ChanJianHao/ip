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

    /**
     * Returns the current task array list.
     *
     * @return array list containing tasks.
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Adds task to task list.
     *
     * @param task task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Removes task from task list.
     *
     * @param taskNum task index of task to be removed.
     */
    public void removeTask(int taskNum) {
        list.remove(taskNum);
    }

    /**
     * Gets current total size of task list.
     *
     * @return size of task list.
     */
    public int getTotal() {
        return list.size();
    }
}
