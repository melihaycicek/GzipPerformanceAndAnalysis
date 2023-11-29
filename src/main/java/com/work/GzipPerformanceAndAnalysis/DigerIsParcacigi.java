package com.work.GzipPerformanceAndAnalysis;


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

class DigerIsParcacigi implements Runnable {
    private File dosya;
    private String hedefDizin;
    private Logger logger;

    public DigerIsParcacigi(File dosya, String hedefDizin, Logger logger) {
        this.dosya = dosya;
        this.hedefDizin = hedefDizin;
        this.logger = logger;
    }

    @Override
    public void run() {
        // DigerIsParcacigi işlemleri burada yapılır
        String dosyaAdi = dosya.getName();
        // ...

        dosya.delete();
    }
}
