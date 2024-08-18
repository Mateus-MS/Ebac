import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setSize(300, 400);
        frame.setVisible(true);

        JButton button = new JButton("Clique em mim");
        button.setBounds(100, 150, 100, 50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                System.out.println("Fui apertado");
            }
        });

        frame.setLayout(null);
        frame.add(button);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}