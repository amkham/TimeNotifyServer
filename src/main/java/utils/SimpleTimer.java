package utils;

public class SimpleTimer extends Thread{

    public interface TimerTickListener{

        void tick(Time time);
    }

    private TimerTickListener __timerTick;

    private int hour, min, sec;

    private boolean __stop;


    public void setTimerTick(TimerTickListener timerTick) {
        __timerTick = timerTick;
    }

    @Override
    public void run() {

        while (!__stop){
            try {
                Thread.sleep(1000);
                tickSec();
                __timerTick.tick(getTime());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void end(){

        __stop = true;
        hour = min = sec = 0;
    }

    public Time getTime(){

        return new Time(getHour(), getMin(), getSec());
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    private void tickSec(){
        sec++;
        if (sec ==60){
            tickMin();
            sec = 0;
        }
    }

    private void tickMin(){
        min++;
        if (min == 60){
            tickHour();
            min = 0;
        }
    }

    private void tickHour(){
        hour++;
    }




}
