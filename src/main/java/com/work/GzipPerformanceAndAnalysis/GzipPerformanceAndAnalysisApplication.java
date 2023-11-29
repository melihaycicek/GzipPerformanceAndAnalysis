package com.work.GzipPerformanceAndAnalysis;

import com.work.GzipPerformanceAndAnalysis.RealTimeGzipDosyaIsleme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class GzipPerformanceAndAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GzipPerformanceAndAnalysisApplication.class, args);

		// RealTimeGzipDosyaIsleme iş parçacığını başlatmak için gereken kodu burada ekleyin.
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

		scheduler.scheduleAtFixedRate((Runnable) new RealTimeGzipDosyaIsleme(), 0, 3, TimeUnit.SECONDS);
	}
}
