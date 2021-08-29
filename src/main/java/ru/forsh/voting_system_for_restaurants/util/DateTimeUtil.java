package ru.forsh.voting_system_for_restaurants.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    public static final LocalTime VOTE_TIME_LIMIT = LocalTime.of(11, 0);

    private DateTimeUtil() {
    }

    public static boolean canReVote(LocalTime time) {
        return time.isBefore(VOTE_TIME_LIMIT);
    }

    public static String toString(LocalDateTime ldt) {
        return ldt == null ? "" : ldt.format(DATE_TIME_FORMATTER);
    }

    public static @Nullable
    LocalDate parseLocalDate(@Nullable String str) {
        return StringUtils.hasText(str) ? LocalDate.parse(str) : null;
    }
}