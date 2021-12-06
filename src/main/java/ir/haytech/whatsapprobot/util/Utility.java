package ir.haytech.whatsapprobot.util;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utility {

    public static int generateRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    public static String convertToTwoDigitsFormat(int value) {
        return String.format("%02d", value);
    }

    public static Date getToday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static long getDifferenceBetweenTwoTimesInSeconds(Date d1, Date d2) {
        return (d2.getTime() - d1.getTime()) / 1000;
    }

    public static long getDelayBeforeNextMessage(int permittedMessages, Date startTime, Date endTime) {
        long totalSeconds = getDifferenceBetweenTwoTimesInSeconds(startTime, endTime);
        long fixedDelay = totalSeconds / permittedMessages;
        long quarterOfFixedDelay = Math.round(fixedDelay * 0.25);
        return ((fixedDelay - quarterOfFixedDelay) + generateRandom(0, ((int) quarterOfFixedDelay) * 2)) * 1000;
    }
}
