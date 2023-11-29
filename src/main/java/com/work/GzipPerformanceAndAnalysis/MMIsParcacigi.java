package com.work.GzipPerformanceAndAnalysis;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

class MMIsParcacigi implements Runnable {
    private File dosya;
    private String hedefDizin;
    private Logger logger;

    public MMIsParcacigi(File dosya, String hedefDizin, Logger logger) {
        this.dosya = dosya;
        this.hedefDizin = hedefDizin;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            String dosyaAdi = dosya.getName();
            FileInputStream fis = new FileInputStream(dosya);
            GZIPInputStream gis = new GZIPInputStream(fis);

            String yeniDosyaAdi = dosyaAdi.substring(0, dosyaAdi.length() - 3) + ".txt";
            FileOutputStream fos = new FileOutputStream(hedefDizin + File.separator + yeniDosyaAdi);

            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

            gis.close();
            fis.close();
            fos.close();

            dosya.delete();
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.WARNING, "MM dosya işleme sırasında hata oluştu: " + e.getMessage());
        }
    }
}
