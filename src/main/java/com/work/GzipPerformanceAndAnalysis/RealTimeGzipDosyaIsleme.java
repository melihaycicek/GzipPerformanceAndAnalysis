package com.work.GzipPerformanceAndAnalysis;import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.GZIPInputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RealTimeGzipDosyaIsleme {
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                String kaynakDizin = "C:\\Users\\melih.aycicek\\Documents\\Projects\\Company\\kaynak";
                String hedefDizin = "C:\\Users\\melih.aycicek\\Documents\\Projects\\Company\\hedef";

                Logger logger = Logger.getLogger("DosyaIsleme");
                FileHandler fileHandler;

                try {
                    fileHandler = new FileHandler("dosya_isleme.log");
                    logger.addHandler(fileHandler);
                    SimpleFormatter formatter = new SimpleFormatter();
                    fileHandler.setFormatter(formatter);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                ExecutorService mmExecutor = Executors.newFixedThreadPool(5); // Maksimum 5 MMIsParcacigi iş parçacığı
                ExecutorService digerExecutor = Executors.newCachedThreadPool(); // DigerIsParcacigi iş parçacığı için

                File kaynakDizinFile = new File(kaynakDizin);
                File hedefDizinFile = new File(hedefDizin);

                if (kaynakDizinFile.exists() && kaynakDizinFile.isDirectory()) {
                    File[] dosyalar = kaynakDizinFile.listFiles();

                    for (File dosya : dosyalar) {
                        if (dosya.isFile() && dosya.getName().endsWith(".gz")) {
                            String dosyaAdi = dosya.getName();

                            if (dosyaAdi.contains("MM")) {
                                mmExecutor.execute(new MMIsParcacigi(dosya, hedefDizin, logger));
                            } else {
                                digerExecutor.execute(new DigerIsParcacigi(dosya, hedefDizin, logger));
                            }
                        }
                    }
                }
            }
        }, 0, 3, TimeUnit.SECONDS);
    }
    
}
