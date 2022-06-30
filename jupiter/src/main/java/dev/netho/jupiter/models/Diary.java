package dev.netho.jupiter.models;

import dev.netho.jupiter.utils.TimeStampGenerator;
import dev.netho.jupiter.utils.TimeStampStyle;

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

        return new TimeStampGenerator().generateString(this.getReceive(), TimeStampStyle.SHORT);
    }

}
