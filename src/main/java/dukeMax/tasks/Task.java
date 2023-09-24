package tasks;

import helper.Ui;

public class Task {
    String task;
    boolean isDone;

    public Task(String task){
        this.task = task;
        this.isDone = false;
    }
    
    public String getTask() {
        return this.task;
    }

    public String getStatus() {
        String status = "[" + (isDone ? "Yes" : "No") + "]";
        return status + " " + this.getTask();
    }

    public String getTime() {
        return null;
    }

    public void markItem(Boolean isDone) {
        this.isDone = isDone;
        if (this.isDone) {
            String[] messageList = {("Nice! I've marked this task as done:")
            , (this.getStatus())
            , ("Here's a lollipop.")};
            Ui.print(messageList);
        } else {
            String[] messageList = {("OK, I've marked this task as not done yet:")
            , (this.getStatus()), ("Undone complete.")};
            Ui.print(messageList);
        }
    }

    public String toFile() {
        return task;
    }

    public static Task convertStringToTask(String text) {
        String[] tasks = text.split("\\|");
        String type = tasks[0].trim();
        boolean isCompleted = tasks[1].trim().equals("1");
        String task = tasks[2].trim();

        if (type.equals("T")) {
            ToDo temp = new ToDo(task);
            temp.markItem(isCompleted);
            return temp;
        } else if (type.equals("D")) {
            String ddl = tasks[3].trim();
            Deadline temp =  new Deadline(task, ddl);
            temp.markItem(isCompleted);
            return temp;
        } else if (type.equals("E")) {
            String[] timeDuration = tasks[3].trim().split("-");
            String from = timeDuration[0].trim();
            String to = timeDuration[1].trim();
            Event temp =  new Event(task, from, to);
            temp.markItem(isCompleted);
            return temp;
        } else {
            return null;
        }
    }
}