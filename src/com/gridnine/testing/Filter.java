package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Filter {


    public static List<Flight> FromCurrentTime(List<Flight> flights, LocalDateTime dateTime) {
        List<Flight> filterFlights = new ArrayList<>();
        LocalDateTime localDateTime;
        for (Flight flt : flights) {
            localDateTime = flt.getSegments().get(0).getDepartureDate();
            if (localDateTime.isAfter(dateTime) || localDateTime.isEqual(dateTime)) {
                filterFlights.add(flt);
            }
        }
        return filterFlights;
    }

    public static List<Flight> CorrectSegment(List<Flight> flights) {
        List<Flight> filterFlights = new ArrayList<>();

        List<Segment> segments;
        LocalDateTime arrivalDate;
        LocalDateTime departureDate;
        boolean correctTime = true;
        for (Flight flt : flights) {
            segments = flt.getSegments();
            for (Segment segment : segments) {
                arrivalDate = segment.getArrivalDate();
                departureDate = segment.getDepartureDate();
                if (departureDate.isAfter(arrivalDate)) {
                    correctTime = false;
                }
            }
            if (correctTime) {
                filterFlights.add(flt);
            }
            correctTime = true;
        }
        return filterFlights;

    }

    public static List<Flight> TimeBetweenSegments(List<Flight> flights, long timeInMls) {
        List<Flight> filterFlights = new ArrayList<>();
        List<Segment> segments;
        LocalDateTime arrivalDate;
        LocalDateTime departureDate;
        for (Flight flt : flights) {
            long waitingTime = 0;
            segments = flt.getSegments();
            for (int i = 0; i < segments.size() - 1; i++) {
                arrivalDate = segments.get(i).getArrivalDate();
                departureDate = segments.get(i + 1).getDepartureDate();
                waitingTime += departureDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() - arrivalDate.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            }
            if (waitingTime <= timeInMls) {
                filterFlights.add(flt);
            }
        }
        return filterFlights;
    }
}
