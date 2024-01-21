import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main extends JFrame {

    public main() {
        setTitle("Java Uygulaması - Ana Menü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton dersFormu = new JButton("Ders Formu");
        JButton ogrenciFormu = new JButton("Öğrenci Formu");
        JButton ogretimGorevlisiFormu = new JButton("Öğretim Görevlisi Kayıt Formu");
        JButton cikis = new JButton("Çıkış");

        panel.add(dersFormu);
        panel.add(ogrenciFormu);
        panel.add(ogretimGorevlisiFormu);
        panel.add(cikis);

        getContentPane().add(panel);

        // Ders Formu butonuna tıklandığında DersFormu'nu aç
        dersFormu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new Dersform().setVisible(true);
                });
            }
        });

        // Öğrenci Formu butonuna tıklandığında ÖğrenciFormu'nu aç
        ogrenciFormu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new ogrenciform().setVisible(true);
                });
            }
        });

        // Öğretim Görevlisi Formu butonuna tıklandığında OgretimGorevlisiFormu'nu aç
        ogretimGorevlisiFormu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    new Ogretimgörevlisikayıtform().setVisible(true);
                });
            }
        });

        // Çıkış butonuna tıklandığında uygulamadan çık
        cikis.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new main());
    }
}
