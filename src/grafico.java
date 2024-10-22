import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class grafico extends JPanel {
    private float a, b;
    private boolean errorFlag = false;
    private JFrame jFrame;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            grafico funcao = new grafico();
            funcao.createInputScreen();
        });
    }

    public grafico() {
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'n' || e.getKeyChar() == 'N') {
                    reset();
                }
            }
        });
    }

    private void createInputScreen() {
        JFrame inputFrame = new JFrame("Insira os valores de A e B");
        inputFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inputFrame.setSize(500, 400);
        inputFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(245, 245, 245));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            }
        };
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setPreferredSize(new Dimension(400, 300));

        JLabel titleLabel = new JLabel("Digite os valores para a função do 1º grau: f(x) = ax + b", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(new Color(0, 51, 102));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2, 15, 15));
        inputPanel.setOpaque(false);

        JLabel labelA = new JLabel("Valor de A:");
        labelA.setFont(new Font("Arial", Font.PLAIN, 14));
        labelA.setHorizontalAlignment(SwingConstants.RIGHT);

        JTextField fieldA = new JTextField();
        fieldA.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldA.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 204, 204), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JLabel labelB = new JLabel("Valor de B:");
        labelB.setFont(new Font("Arial", Font.PLAIN, 14));
        labelB.setHorizontalAlignment(SwingConstants.RIGHT);

        JTextField fieldB = new JTextField();
        fieldB.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldB.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(204, 204, 204), 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        inputPanel.add(labelA);
        inputPanel.add(fieldA);
        inputPanel.add(labelB);
        inputPanel.add(fieldB);

        mainPanel.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton calculateButton = new JButton("Calcular e Gerar Resultado");
        calculateButton.setFont(new Font("Arial", Font.BOLD, 13));
        calculateButton.setBackground(new Color(0, 128, 255));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 102, 204), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        JButton cancelButton = new JButton("Cancelar");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 13));
        cancelButton.setBackground(new Color(220, 53, 69));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(169, 34, 53), 1),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));

        buttonPanel.add(calculateButton);
        buttonPanel.add(cancelButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        inputFrame.getContentPane().setBackground(new Color(230, 230, 230));
        inputFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        inputFrame.setVisible(true);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    a = Float.parseFloat(fieldA.getText());
                    b = Float.parseFloat(fieldB.getText());

                    if (a == 0) {
                        JOptionPane.showMessageDialog(inputFrame, "O valor de A não pode ser zero.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    float x = -b / a;
                    String message = String.format("O valor de x para f(x) = 0 é: %.2f", x);

                    Object[] options = {"Sim", "Não"};
                    int option = JOptionPane.showOptionDialog(inputFrame, message + "\nDeseja ver o gráfico da função?",
                            "Resultado", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    if (option == JOptionPane.YES_OPTION) {
                        inputFrame.dispose();
                        Screen();
                    } else {
                        System.exit(0);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(inputFrame, "Por favor, Insira valores numéricos válidos para A e B.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void Screen() {
        jFrame = new JFrame("Projeto Cálculo da Função do 1º Grau");
        jFrame.add(this);
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (errorFlag) {
            errorFlag = false;
            return;
        }

        drawAxes(g);
        drawFunction(g);
    }

    private void drawAxes(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;

        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(0, centerY, width, centerY);
        g.drawLine(centerX, 0, centerX, height);

        g.setColor(Color.BLACK);
        int scale = 30;

        for (int i = -10; i <= 10; i++) {
            int x = centerX + (i * scale);
            int y = centerY - (i * scale);

            g.drawLine(x, centerY - 5, x, centerY + 5);
            g.drawLine(centerX - 5, y, centerX + 5, y);

            if (i != 0) {
                g.drawString(Integer.toString(i), x - 5, centerY + 15);
                g.drawString(Integer.toString(-i), centerX + 5, y + 5);
            } else {
                g.drawString("0", centerX + 5, centerY + 15);
            }
        }
    }

    private void drawFunction(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(Color.BLUE);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int scale = 30;

        float y1 = (a * -10) + b;
        float y2 = (a * 10) + b;

        int x1 = centerX - (10 * scale);
        int x2 = centerX + (10 * scale);

        int coord1 = Math.round((-y1 * scale) + centerY);
        int coord2 = Math.round((-y2 * scale) + centerY);

        g2.drawLine(x1, coord1, x2, coord2);
    }

    private void reset() {
        jFrame.dispose();
        createInputScreen();
    }
}