package at.er.ytbattle.util.timer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TimerManager {

    private HashMap<Integer, HashSet<Timeable>> timers;

    public TimerManager() {
        this.timers = new HashMap<Integer, HashSet<Timeable>>();
    }

    public void registerTimer(int id, Timeable timer) {
        timer.setTimerManager(this);
        this.timers.get(id).add(timer);
    }

    public void unregisterTimer(Timeable timer) {
        this.timers.remove(timer);
    }

    public void startAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.startTimer();
            }
        }
    }

    public void stopAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.stopTimer();
            }
        }
    }

    public void pauseAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.pauseTimer();
            }
        }
    }

    public void resumeAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.resumeTimer();
            }
        }
    }

    public void removeAllTimers() {
        for (Map.Entry<Integer, HashSet<Timeable>> entry : this.timers.entrySet()) {
            for (Timeable timeable : entry.getValue()) {
                timeable.removeTimer();
            }
        }
    }

    enum TimeScale {
        MILISECONDS(1), GAMETICK(50), REDSTONETICK(100), SECOND(1000), MINUTE(60000);

        private int multiplier;

        TimeScale(int multiplier) {
            this.multiplier = multiplier;
        }

        public int getMultiplier() {
            return this.multiplier;
        }
    }

}