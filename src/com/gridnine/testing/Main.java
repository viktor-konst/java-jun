package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();

        //        Фильтр возвращает все полеты после текущего момента времени
        List<Flight> filterFlights = Filter.FromCurrentTime(flights, LocalDateTime.now());
        System.out.println();
        for (Flight flight : filterFlights) {
            System.out.println(flight);
        }
        System.out.println();
        //         Фильтр возвращает все сегменты с датой прилета после даты вылета
        filterFlights = Filter.CorrectSegment(flights);
        for (Flight flight : filterFlights) {
            System.out.println(flight);
        }
        System.out.println();

        long timeBetweenSegment = 2 * 3600 * 1000; // 2 hours in milliseconds

        //         Фильтр возвращает все полеты с общим временем на земле меньше двух часов
        filterFlights = Filter.TimeBetweenSegments(flights, timeBetweenSegment);
        for (Flight flight : filterFlights) {
            System.out.println(flight);
        }
    }
}
