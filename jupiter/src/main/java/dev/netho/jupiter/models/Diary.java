package dev.netho.jupiter.models;

import java.time.LocalDateTime;

public class Diary {

    private int id;
    private final int moodLevel;
    private final String content;
    private final LocalDateTime receive;

    public Diary(int id, int humorLevel, String content, LocalDateTime receive) {
        this.id = id;
        this.moodLevel = humorLevel;
        this.content = content;
        this.receive = receive;
    }

    public Diary(int moodLevel, String content) {
        this.id = -1;
        this.moodLevel = moodLevel;
        this.content = content;
        this.receive = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMoodLevel() {
        return moodLevel;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getReceive() {
        return receive;
    }

    @Override
    public String toString() {

        return "Há " + generateTime();
    }

    private String generateTime() {
        //Bored

        LocalDateTime now = LocalDateTime.now();

        if(now.getDayOfMonth() - getReceive().getDayOfMonth() >= 7) {
            //Calculate in weeks
            int weeks =  (now.getDayOfYear() - getReceive().getDayOfYear())/7;
            if(weeks == 1) {
                return weeks + " semana atrás";
            }
            return weeks + " semanas atrás";

        }else if(now.getDayOfYear() - getReceive().getDayOfYear() > 0) {

            int days = now.getDayOfYear() - getReceive().getDayOfYear();
            if(days == 1) {
                return days + " dia atrás";
            }
            return days + " dias atrás";

        } else if(now.getHour() - getReceive().getHour() > 0) {
            int hours = now.getHour() - getReceive().getHour();
            if(hours == 1) {
                return hours + " hora atrás";
            }
            return hours + " horas atrás";

        }else if(now.getMinute() - getReceive().getMinute() > 0) {
            int minutes = now.getMinute() - getReceive().getMinute();
            if(minutes == 1) {
                return minutes + " minuto atrás";
            }
            return minutes + " minutos atrás";
        }else {
            int seconds = now.getSecond() - getReceive().getSecond();
            return seconds + " segundos atrás";
        }
    }

}
