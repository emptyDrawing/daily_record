package my.sskim.java8study;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class Java8OptionalAndDate {

    public static void main(String[] args) {

        // List<OnClass> springClass = new ArrayList<>();
        // springClass.add(new OnClass(1,"spring boot",true));
        // springClass.add(new OnClass(2,"spring data jpa",false));
        // springClass.add(new OnClass(3,"spring mvc",false));
        // springClass.add(new OnClass(4,"spring core",false));
        // springClass.add(new OnClass(5,"API Spring boot",false));

        // // 이상태로 바로 시작하면 null 포인트...
        // // OnClass testProgress = springClass.get(0);
        // // System.out.println(testProgress.getProgress().getStudyDuration());

        // Optional<OnClass> checkClass = springClass.stream()
        // .filter( c -> c.getTitle().startsWith("jpa"))
        // .findFirst();

        // System.out.println(checkClass.isPresent());

        // Date oldStyleDate = new Date(); // timeStamp...
        // long time = oldStyleDate.getTime();

        // Calendar osCal = new GregorianCalendar();
        // SimpleDateFormat osSdf = new SimpleDateFormat();

        // EPOCH 시간 / 기계시간
        // Instant instant = Instant.now();
        // System.out.println(instant); // 기준시 UTC, GMT 기준임

        // ZoneId systemDefault = ZoneId.systemDefault();
        // System.out.println(systemDefault);
        // System.out.println(instant.atZone(systemDefault)); // 존아이디로 가능함.

        // 사람시간
        // LocalDateTime now = LocalDateTime.now();
        // System.out.println(now);
        // LocalDateTime birthDay = LocalDateTime.of(1989, Month.JUNE, 17, 5, 10, 0);
        // ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));

        // System.out.println(nowInKorea);
        // System.out.println(birthDay);

        // LocalDate today = LocalDate.now();
        // LocalDate nextYear = LocalDate.of(2023, Month.FEBRUARY, 1);

        // Period period= Period.between(today, nextYear);
        // System.out.printf("%d %d %d
        // \n",period.getYears(),period.getMonths(),period.getDays());

        // Period until = today.until(nextYear);
        // System.out.println(until.get(ChronoUnit.DAYS));

        // System.out.println(ChronoUnit.DAYS.between(today, nextYear));

        // LocalDateTime now = LocalDateTime.now();
        // final DateTimeFormatter MMddyyyy = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        // System.out.println(now.format(MMddyyyy));

        // System.out.println(LocalDate.parse("10/10/2004", MMddyyyy));

        Date date = new Date();
        Instant instant = date.toInstant();
        Date newDate = Date.from(instant);

        ZoneId zoneId = TimeZone.getTimeZone("PST").toZoneId();
        TimeZone legecyId = TimeZone.getTimeZone(zoneId);

    }
}
