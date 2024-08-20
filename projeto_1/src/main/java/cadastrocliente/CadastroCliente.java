package cadastrocliente;

import javax.swing.*;
import java.awt.Color;

public class CadastroCliente {

    public static void main(String[] args) {
        
        TelaPrincipal mainTela = new TelaPrincipal();
        mainTela.setVisible(true);
        mainTela.setName("Controle de cadastros");
        mainTela.setLocationRelativeTo(null);
        mainTela.setBackground(Color.decode("#3B3B3B"));
        mainTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
}
