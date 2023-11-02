package com.work.GzipPerformanceAndAnalysis;

import com.work.GzipPerformanceAndAnalysis.RealTimeGzipDosyaIsleme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GzipPerformanceAndAnalysisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GzipPerformanceAndAnalysisApplication.class, args);

		// RealTimeGzipDosyaIsleme iş parçacığını başlatmak için gereken kodu burada ekleyin.
		RealTimeGzipDosyaIsleme gzipIslemeThread = new RealTimeGzipDosyaIsleme();
		gzipIslemeThread.start();
	}
}
