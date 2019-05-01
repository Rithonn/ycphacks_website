package controller;

import java.util.Comparator;

import model.Event;

public class SortScheduleByDate implements Comparator<Event>{

	@Override
	public int compare(Event o1, Event o2) {
		return o1.getDate().compareTo(o2.getDate());
	}
}