/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class ConfigModel {

    int idTimer, Timer, breaktime, NumberQuestion;
    public ConfigModel() {
    }

    public ConfigModel(int idTimer, int Timer, int breaktime, int NumberQuestion) {
        this.idTimer = idTimer;
        this.Timer = Timer;
        this.breaktime = breaktime;
        this.NumberQuestion = NumberQuestion;
    }

    public int getIdTimer() {
        return idTimer;
    }

    public void setIdTimer(int idTimer) {
        this.idTimer = idTimer;
    }

    public int getTimer() {
        return Timer;
    }

    public void setTimer(int Timer) {
        this.Timer = Timer;
    }

    public int getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(int breaktime) {
        this.breaktime = breaktime;
    }

    public int getNumberQuestion() {
        return NumberQuestion;
    }

    public void setNumberQuestion(int NumberQuestion) {
        this.NumberQuestion = NumberQuestion;
    }

   

}
    