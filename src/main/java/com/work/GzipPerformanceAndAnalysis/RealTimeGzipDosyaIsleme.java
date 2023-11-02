package com.work.GzipPerformanceAndAnalysis;

import java.io.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.zip.GZIPInputStream;

public class RealTimeGzipDosyaIsleme extends Thread {
    @Override
    public void run() {
        String kaynakDizin = "C:\\Users\\melih.aycicek\\Documents\\Projects\\Company\\kaynak";   // Gzipten çıkartılacak dosyaların dizini
        String hedefDizin = "C:\\Users\\melih.aycicek\\Documents\\Projects\\Company\\hedef";    // İşlendikten sonra taşınacak dizin

        Logger logger = Logger.getLogger("DosyaIsleme");
        FileHandler fileHandler;

        try {
            // Log dosyasını oluştur
            fileHandler = new FileHandler("dosya_isleme.log");
            logger.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);

        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            File kaynakDizinFile = new File(kaynakDizin);
            File hedefDizinFile = new File(hedefDizin);

            if (kaynakDizinFile.exists() && kaynakDizinFile.isDirectory()) {
                File[] dosyalar = kaynakDizinFile.listFiles();

                for (File dosya : dosyalar) {
                    if (dosya.isFile() && dosya.getName().endsWith(".gz")) {
                        try {
                            long baslangicZamani = System.currentTimeMillis();
                            String dosyaAdi = dosya.getName();
                            FileInputStream fis = new FileInputStream(dosya);
                            GZIPInputStream gis = new GZIPInputStream(fis);

                            // Dosyanın sonundaki .gz uzantısını kaldır ve .txt ekleyerek yeni adı oluştur
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

                            long bitisZamani = System.currentTimeMillis();
                            long islemSuresi = bitisZamani - baslangicZamani;

                            logger.log(Level.INFO, "Dosya çıkartma ve işleme başarılı: " + dosyaAdi);
                            logger.log(Level.INFO, "İşlem Süresi: " + islemSuresi + " ms");

                            dosya.delete(); // Kaynak dizinden dosyayı sil
                        } catch (IOException e) {
                            e.printStackTrace();
                            logger.log(Level.WARNING, "Dosya işleme sırasında hata oluştu: " + e.getMessage());
                        }
                    }
                }
            }

            try {
                Thread.sleep(3000); // 3 saniye bekle
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        RealTimeGzipDosyaIsleme gzipIsleme = new RealTimeGzipDosyaIsleme();
        gzipIsleme.start(); // İş parçacığını başlat
    }
}
