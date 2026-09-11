class TaskManager {

    private final Map<Integer, Task> tasksById;
    private final TreeSet<Task> tasksByPriority;


    public TaskManager(List<List<Integer>> tasks) {
        tasksById = new HashMap<>();
        tasksByPriority =
            new TreeSet<>(Comparator.comparingInt((Task t) -> t.priority).thenComparingInt(t -> t.taskId));

        for (List<Integer> task : tasks) {
            add(task.get(0), task.get(1), task.get(2));
        }
    }
    
    public void add(int userId, int taskId, int priority) {
        Task task = new Task(userId, taskId, priority);
        tasksById.put(taskId, task);
        tasksByPriority.add(task);
    }
    
    public void edit(int taskId, int newPriority) {
        Task task = tasksById.get(taskId);

        if (task == null) {
            return;
        }

        tasksByPriority.remove(task);

        task.priority = newPriority;

        tasksByPriority.add(task);
    }

    public void rmv(int taskId) {
        Task task = tasksById.get(taskId);
        if (task != null) {
            tasksByPriority.remove(task);
            tasksById.remove(taskId);
        }
    }

    public int execTop() {
        Task task = tasksByPriority.pollLast();
        if (task == null) {
            return -1;
        }

        tasksById.remove(task.taskId);
        return task.userId;
    }

    private static class Task {
        int userId;
        int taskId;
        int priority;

        public Task(int userId, int taskId, int priority) {
            this.userId = userId;
            this.taskId = taskId;
            this.priority = priority;
        }
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */
