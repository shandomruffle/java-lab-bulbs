import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Window extends JFrame {
    private String cur_path;
    private String cur_abs_path;
    private ArrayList<LightingBulb> list;
    private int type;
    Window() {
        super("Lighting Bulb Analyzer");

        String cur_file_label = "Current File: ";
        String nofile_label = "no file chosen";

        JLabel cur = new JLabel(nofile_label);
        add(cur);

        JButton ch = new JButton("Choose file");
        ch.addActionListener(a -> {
            var d = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            int res = d.showOpenDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                cur_path = d.getSelectedFile().getPath();
                cur_abs_path = d.getSelectedFile().getAbsolutePath();
                cur.setText(cur_file_label + cur_path);
            } else {
                cur.setText(nofile_label);
            }
        });
        add(ch);

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));
        JButton o1 = new JButton("Open type 1");
        o1.addActionListener(a -> {
            type = 1;
        });
        JButton o2 = new JButton("Open type 2");
        o2.addActionListener(a -> {
            type = 2;
        });
        p.add(o1); p.add(o2);
        add(p);

        JButton srt = new JButton("Sort by cost");
        srt.addActionListener(a -> {
            parse();
            var res = CollectionProcessor.sortByCost(list);
            StringBuilder o = new StringBuilder();
            res.stream().forEach(l -> {
                o.append(l.toString() + "\n");
            });
            output(o.toString());
        });
        add(srt);

        JButton srt2 = new JButton("Sort by cost/power descending");
        srt2.addActionListener(a -> {
            parse();
            var res = CollectionProcessor.sortByCostOverPowerDescending(list);
            StringBuilder o = new StringBuilder();
            res.stream().forEach(l -> {
                o.append(l.toString() + "\n");
            });
            output(o.toString());
        });
        add(srt2);

        JButton c = new JButton("Producers Starting with \"C\"");
        c.addActionListener(a -> {
            parse();
            var res = CollectionProcessor.distinctProducersStartingWithC(list);
            StringBuilder o = new StringBuilder();
            res.stream().forEach(l -> {
                o.append(l.toString() + "\n");
            });
            output(o.toString());
        });
        add(c);

        JButton avg = new JButton("Average cost of producer");
        avg.addActionListener(a -> {
            parse();
            String pr = JOptionPane.showInputDialog("producer");
            var res = CollectionProcessor.averageCostByProducer(list, pr);
            output(String.valueOf(res));
        });
        add(avg);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 1));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void parse() {
        if (type == 0) {
            return;
        }
        if (type == 1) parseType1();
        else parseType2();
    }

    private void parseType1() {
        try {
            var b = new BufferedReader(new FileReader(cur_abs_path));
            list = new ArrayList<LightingBulb>();
            b.lines().forEach(l -> {
                var s = l.split(" ");
                list.add(new IncandescentLamp(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2])));
            });
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Failed to open");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void parseType2() {
        try {
            var b = new BufferedReader(new FileReader(cur_abs_path));
            list = new ArrayList<LightingBulb>();
            b.lines().forEach(l -> {
                var s = l.split(" ");
                list.add(new LightEmittingDiodeLamp(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2])));
            });
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Failed to open");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void output(String s) {
        var j = new JFrame("Result");
        var tf = new JTextArea();
        tf.setText(s);
        j.setDefaultCloseOperation(EXIT_ON_CLOSE);
        j.setSize(600, 400);
        j.setVisible(true);
    }
}
