package javathuchanh;
import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
        private JTextArea textArea;
        private JFileChooser fileChooser;

        public Notepad() {
            setTitle("Notepad");
            setSize(500, 500);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

            textArea = new JTextArea();
            JScrollPane scrollPane = new JScrollPane(textArea);
            add(scrollPane, BorderLayout.CENTER);

            JToolBar toolBar = new JToolBar();
            JButton openButton = new JButton("Open");
            JButton saveButton = new JButton("Save");
            openButton.addActionListener(this);
            saveButton.addActionListener(this);
            toolBar.add(openButton);
            toolBar.add(saveButton);
            add(toolBar, BorderLayout.SOUTH);

            fileChooser = new JFileChooser();

            setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Open")) {
                int returnValue = fileChooser.showOpenDialog(this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            content.append(line).append("\n");
                        }
                        textArea.setText(content.toString());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            } else if (e.getActionCommand().equals("Save")) {
                int returnValue = fileChooser.showSaveDialog(this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        writer.write(textArea.getText());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(Notepad::new);
        }
    }
