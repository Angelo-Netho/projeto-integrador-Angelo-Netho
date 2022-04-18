package dev.netho.jupiter.models;

import java.time.LocalDateTime;

public class Diary {

    private final int id;
    private final int humorLevel;
    private final String content;
    private final LocalDateTime receive;

    public Diary(int id, int humorLevel, String content, LocalDateTime receive) {
        this.id = id;
        this.humorLevel = humorLevel;
        this.content = content;
        this.receive = receive;
    }

    public int getId() {
        return id;
    }

    public int getHumorLevel() {
        return humorLevel;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getReceive() {
        return receive;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", humorLevel=" + humorLevel +
                ", content='" + content + '\'' +
                ", receive=" + receive +
                '}';
    }
}
