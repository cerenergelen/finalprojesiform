
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class Ogretimgörevlisikayıtform extends JFrame {
    private JTextField txtOgretmenNo, txtAd, txtSoyad;
    private JComboBox<String> cmbBolum, cmbVerdigiDersler;
    private JButton btnKaydet;

    public Ogretimgörevlisikayıtform() {
        setTitle("Java Uygulaması - Öğretim Görevlisi Formu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Formun bileşenleri
        JPanel panel = new JPanel(new GridLayout(7, 2));

        panel.add(new JLabel("Öğretmen No:"));
        txtOgretmenNo = new JTextField();
        panel.add(txtOgretmenNo);

        panel.add(new JLabel("Ad:"));
        txtAd = new JTextField();
        panel.add(txtAd);

        panel.add(new JLabel("Soyad:"));
        txtSoyad = new JTextField();
        panel.add(txtSoyad);

        panel.add(new JLabel("Bölüm:"));
        cmbBolum = new JComboBox<>(new String[]{"Bilgisayar Mühendisliği", "Bilgisayar Programcılığı", "Mobil Programlama"});
        panel.add(cmbBolum);

        panel.add(new JLabel("Verdiği Dersler:"));
        cmbVerdigiDersler = new JComboBox<>(getDersler());
        panel.add(cmbVerdigiDersler);

        btnKaydet = new JButton("Kaydet");
        btnKaydet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsonaKaydet();
                dispose();  // Formu kapat
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

    private String[] getDersler() {
        // Derslerin listesini döndüren bir metot

        return new String[]{"Görsel Programlama", "Nesneye Dayalı Programlama", "Elektronik"};
    }

    private void jsonaKaydet() {
        String ogretmenNo = txtOgretmenNo.getText();
        String ad = txtAd.getText();
        String soyad = txtSoyad.getText();
        String bolum = cmbBolum.getSelectedItem().toString();
        String verdigiDers = cmbVerdigiDersler.getSelectedItem().toString();

        // OgretimGorevlisi objesini oluştur
        OgretimGorevlisi ogretimGorevlisi = new OgretimGorevlisi(ogretmenNo, ad, soyad, bolum, verdigiDers);

        // OgretimGorevlisi objesini JSON formatına çevir
        String json = "{\"OgretmenNo\":\"" + ogretimGorevlisi.OgretmenNo +
                "\",\"Ad\":\"" + ogretimGorevlisi.Ad +
                "\",\"Soyad\":\"" + ogretimGorevlisi.Soyad +
                "\",\"Bolum\":\"" + ogretimGorevlisi.Bolum +
                "\",\"VerdigiDers\":\"" + ogretimGorevlisi.VerdigiDers + "\"}";

        // JSON'u dosyaya yaz
        try (FileWriter fileWriter = new FileWriter("ogretimGorevlisi.json", true)) {
            fileWriter.write(json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class OgretimGorevlisi {
        String OgretmenNo;
        String Ad;
        String Soyad;
        String Bolum;
        String VerdigiDers;

        public OgretimGorevlisi(String ogretmenNo, String ad, String soyad, String bolum, String verdigiDers) {
            this.OgretmenNo = ogretmenNo;
            this.Ad = ad;
            this.Soyad = soyad;
            this.Bolum = bolum;
            this.VerdigiDers = verdigiDers;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ogretimgörevlisikayıtform());
    }
}

