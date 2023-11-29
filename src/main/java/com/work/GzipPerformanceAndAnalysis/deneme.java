import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.File;

public class deneme {
    public static void main(String[] args) {
        String dosyaKlasoru = "dosya_klasoru"; // Dosyaların bulunduğu klasörün yolu
        File klasor = new File(dosyaKlasoru);

        if (klasor.isDirectory()) {
            File[] dosyalar = klasor.listFiles();
            int mmDosyaSayisi = 0;
            ExecutorService executor = new ThreadPoolExecutor(
                    10, // Maksimum 10 iş parçacığı
                    10, // Maksimum 10 iş parçacığı
                    0L,
                    java.util.concurrent.TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>()
            );

            for (File dosya : dosyalar) {
                if (dosya.getName().contains("mm") && mmDosyaSayisi < 10) {
                    executor.execute(new MMIsParcacigi(dosya));
                    mmDosyaSayisi++;
                } else {
                    executor.execute(new DigerIsParcacigi(dosya));
                }
            }

            executor.shutdown();
        }
    }
}

class MMIsParcacigi implements Runnable {
    private File dosya;

    public MMIsParcacigi(File dosya) {
        this.dosya = dosya;
    }

    @Override
    public void run() {
        // MM içeren dosyayı işleme kodu burada
        System.out.println("MM dosyası işleniyor: " + dosya.getName());
    }
}

class DigerIsParcacigi implements Runnable {
    private File dosya;

    public DigerIsParcacigi(File dosya) {
        this.dosya = dosya;
    }

    @Override
    public void run() {
        // Diğer dosyayı işleme kodu burada
        System.out.println("Diğer dosya işleniyor: " + dosya.getName());
    }
}
