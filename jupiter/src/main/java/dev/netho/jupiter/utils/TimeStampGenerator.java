package dev.netho.jupiter.utils;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class TimeStampGenerator {

    public String generateString(LocalDateTime receive, TimeStampStyle stampStyle) {
        if(stampStyle == TimeStampStyle.SHORT) {
            return this.generateShortStyle(receive);
        }
        return this.generateLongStyle(receive);
    }

    private String generateShortStyle(LocalDateTime receive) {
        LocalDateTime now = LocalDateTime.now();

        String prefix = "Há ";

        if(now.getDayOfMonth() - receive.getDayOfMonth() >= 7) {
            //Calculate in weeks
            int weeks =  (now.getDayOfYear() - receive.getDayOfYear())/7;
            if(weeks == 1) {
                return prefix + weeks + " semana atrás";
            }
            return prefix + weeks + " semanas atrás";

        }else if(now.getDayOfYear() - receive.getDayOfYear() > 0) {

            int days = now.getDayOfYear() - receive.getDayOfYear();
            if(days == 1) {
                return prefix + days + " dia atrás";
            }
            return prefix + days + " dias atrás";

        } else if(now.getHour() - receive.getHour() > 0) {
            int hours = now.getHour() - receive.getHour();
            if(hours == 1) {
                return prefix + hours + " hora atrás";
            }
            return prefix + hours + " horas atrás";

        }else if(now.getMinute() - receive.getMinute() > 0) {
            int minutes = now.getMinute() - receive.getMinute();
            if(minutes == 1) {
                return prefix + minutes + " minuto atrás";
            }
            return prefix + minutes + " minutos atrás";
        }else {
            int seconds = now.getSecond() - receive.getSecond();
            return prefix + seconds + " segundos atrás";
        }
    }

    private String generateLongStyle(LocalDateTime receive) {
        Locale BRAZIL = new Locale("pt", "BR");

        String month = receive.getMonth().getDisplayName(TextStyle.SHORT, BRAZIL);
        String minute;
        String hour;

        if(receive.getMinute() == 0) {
            minute = "00";
        }else {
            minute = receive.getMinute() + "";
        }

        if(receive.getHour() == 0) {
            hour = "00";
        }else {
            hour = receive.getHour() + "";
        }

        String hourMinute = hour + ":" + minute;

        return receive.getDayOfMonth() + " de " + month + " de " + receive.getYear() + " " +
                hourMinute + " (" + this.generateShortStyle(receive) + ")";
    }

}
