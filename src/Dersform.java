import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Dersform extends JFrame {
    private JComboBox<String> cmbDersKodu, cmbDersAd, cmbDersDonemi, cmbOgretimGorevlisi;
    private JButton btnKaydet;

    public Dersform() {
        setTitle("Java Uygulaması - Ders Formu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Formun bileşenleri
        JPanel panel = new JPanel(new GridLayout(7, 2));

        panel.add(new JLabel("Ders Kodu:"));
        cmbDersKodu = new JComboBox<>(new String[]{"BIP1012", "BIP1212", "BIP101212"});
        panel.add(cmbDersKodu);

        panel.add(new JLabel("Ders Adı:"));
        cmbDersAd = new JComboBox<>(new String[]{"Nesneye Dayalı Programlama", "Görsel Programlama", "Elektronik"});
        panel.add(cmbDersAd);

        panel.add(new JLabel("Ders Dönemi:"));
        cmbDersDonemi = new JComboBox<>(new String[]{"1", "2"});
        panel.add(cmbDersDonemi);

        panel.add(new JLabel("Öğretim Görevlisi:"));
        cmbOgretimGorevlisi = new JComboBox<>(new String[]{"Aysel Aydın", "Nilay Tual", "Salih As", "Öğretim Görevlisi 4", "Öğretim Görevlisi 5"});
        panel.add(cmbOgretimGorevlisi);

        btnKaydet = new JButton("Kaydet");
        btnKaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsonaKaydet();
                dispose();  // Formu kapat
               main.main(null);  // Ana menüyü aç
            }
        });
        panel.add(btnKaydet);

        // Panelin arka plan rengini siyah yap
        panel.setBackground(Color.BLACK);

        // Paneli frame'e ekle
        add(panel);

        // Frame'in arka plan rengini siyah yap
        getContentPane().setBackground(Color.BLACK);

        // Frame'i görünür kıl
        setVisible(true);
    }

    private void jsonaKaydet() {
        String dersKodu = cmbDersKodu.getSelectedItem().toString();
        String dersAd = cmbDersAd.getSelectedItem().toString();
        String dersDonem = cmbDersDonemi.getSelectedItem().toString();
        String ogretimGorevlisi = cmbOgretimGorevlisi.getSelectedItem().toString();

        // Ders objesini oluştur
        Ders ders = new Ders(dersKodu, dersAd, dersDonem, ogretimGorevlisi);

        // Ders objesini JSON formatına çevir
        String json = "{\"DersKodu\":\"" + ders.DersKodu +
                "\",\"DersAd\":\"" + ders.DersAd +
                "\",\"DersDonem\":\"" + ders.DersDonem +
                "\",\"OgretimGorevlisi\":\"" + ders.OgretimGorevlisi + "\"}";

        // JSON'u dosyaya yaz
        try (FileWriter fileWriter = new FileWriter("ders.json", true)) {
            fileWriter.write(json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Dersform());
    }

    // Ders sınıfını içermeyen bir bildirim hatası almamak için dummy bir Ders sınıfı ekliyoruz.
    public static class Ders {
        String DersKodu;
        String DersAd;
        String DersDonem;
        String OgretimGorevlisi;

        public Ders(String dersKodu, String dersAd, String dersDonem, String ogretimGorevlisi) {
            this.DersKodu = dersKodu;
            this.DersAd = dersAd;
            this.DersDonem = dersDonem;
            this.OgretimGorevlisi = ogretimGorevlisi;
        }
    }
}
