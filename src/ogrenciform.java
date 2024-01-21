
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.io.FileWriter;
        import java.io.IOException;

public class ogrenciform extends JFrame {
    private JTextField txtOgrenciNo, txtOgrenciAd, txtOgrenciSoyad;
    private JComboBox<String> cmbOgrenciBolum, cmbOgrenciDersler;
    private JButton btnKaydet;

    public ogrenciform() {
        setTitle("Java Uygulaması - Öğrenci Formu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Formun bileşenleri
        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("Öğrenci No:"));
        txtOgrenciNo = new JTextField();
        panel.add(txtOgrenciNo);

        panel.add(new JLabel("Öğrenci Adı:"));
        txtOgrenciAd = new JTextField();
        panel.add(txtOgrenciAd);

        panel.add(new JLabel("Öğrenci Soyadı:"));
        txtOgrenciSoyad = new JTextField();
        panel.add(txtOgrenciSoyad);

        panel.add(new JLabel("Öğrenci Bölümü:"));
        cmbOgrenciBolum = new JComboBox<>(new String[]{"Bilgisayar Mühendisliği", "Bilgisayar Programcılığı", "Mobil Programlama"});
        panel.add(cmbOgrenciBolum);

        panel.add(new JLabel("Dersler:"));
        cmbOgrenciDersler = new JComboBox<>(getDersler());
        panel.add(cmbOgrenciDersler);

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

        return new String[]{"Görsel Programlama", "Nesneye Dayalı Programlama", "Elektronik"};
    }

    private void jsonaKaydet() {
        String ogrenciNo = txtOgrenciNo.getText();
        String ogrenciAd = txtOgrenciAd.getText();
        String ogrenciSoyad = txtOgrenciSoyad.getText();
        String ogrenciBolum = cmbOgrenciBolum.getSelectedItem().toString();
        String ogrenciDers = cmbOgrenciDersler.getSelectedItem().toString();

        // Öğrenci objesini oluştur
        Ogrenci ogrenci = new Ogrenci(ogrenciNo, ogrenciAd, ogrenciSoyad, ogrenciBolum, ogrenciDers);

        // Öğrenci objesini JSON formatına çevir
        String json = "{\"OgrenciNo\":\"" + ogrenci.OgrenciNo +
                "\",\"OgrenciAd\":\"" + ogrenci.OgrenciAd +
                "\",\"OgrenciSoyad\":\"" + ogrenci.OgrenciSoyad +
                "\",\"OgrenciBolum\":\"" + ogrenci.OgrenciBolum +
                "\",\"OgrenciDers\":\"" + ogrenci.OgrenciDers + "\"}";

        // JSON'u dosyaya yaz
        try (FileWriter fileWriter = new FileWriter("ogrenci.json", true)) {
            fileWriter.write(json + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static class Ogrenci {
        String OgrenciNo;
        String OgrenciAd;
        String OgrenciSoyad;
        String OgrenciBolum;
        String OgrenciDers;

        public Ogrenci(String ogrenciNo, String ogrenciAd, String ogrenciSoyad, String ogrenciBolum, String ogrenciDers) {
            this.OgrenciNo = ogrenciNo;
            this.OgrenciAd = ogrenciAd;
            this.OgrenciSoyad = ogrenciSoyad;
            this.OgrenciBolum = ogrenciBolum;
            this.OgrenciDers = ogrenciDers;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ogrenciform());
    }
}
