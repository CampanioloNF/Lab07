package it.polito.tdp.poweroutages.model;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		System.out.println(model.getNercList());
		
		PowerOutages po = new PowerOutages(1, 10000, LocalDateTime.of(2013, 02, 15, 18, 30),  LocalDateTime.of(2013, 02, 17, 20, 50), null, 0);
		
	long ore = Duration.between( po.getDateEventBegan(),po.getDateEventFinished()).toHours();
		System.out.print((int)ore);
	}
}
