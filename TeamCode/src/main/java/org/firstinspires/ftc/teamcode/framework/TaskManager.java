package org.firstinspires.ftc.teamcode.framework;

import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.LinkedList;
import java.util.Queue;

public class TaskManager {
    public IRobot robot;
    public boolean done = false;

    Queue<RobotTask> tasks = new LinkedList<RobotTask>();
    RobotTask currentTask = null;

    private ElapsedTime runtime = new ElapsedTime();

    public TaskManager(IRobot _robot) {
        robot = _robot;
    }

    public void submitTask(RobotTask task) {
        tasks.add(task);
    }

    public void rotate90() {
        submitTask(new RobotTask(new Vector2D(0), (float) Math.PI / 2));
    }

    public void rotate30() {
        submitTask(new RobotTask(new Vector2D(0), (float) Math.PI / 6));
    }

    public void updateTelemetryDisplay(Telemetry telemetry) {
        robot.updateTelemetryDisplay(telemetry);

        telemetry.addData("Tasks in queue: ", "");

        for (RobotTask s : tasks) {
            telemetry.addData("Task i: ", s.toString());
        }
        if (tasks.isEmpty()) {
            telemetry.addData("None", "");
        }


    }

    void taskCompleted() {
        if (robot.GetState() == Robot.State.Idle) {
            currentTask = null;
        }
    }

    void taskExecutor(RobotTask task) {
        runtime.reset();
        if (robot.GetState() == Robot.State.Idle) robot.Rotate(task.rotation, 1);

        runtime.reset();
        if (robot.GetState() == Robot.State.DoneRotating) robot.Move(task.relativePosition, 1);
    }

    public void update() {
        taskCompleted();

        if (tasks.isEmpty()) {
            done = true;
            return;
        }

        if (currentTask == null) {
            currentTask = tasks.remove();
        }

        taskExecutor(currentTask);
    }
}
