import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SolveEquation{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        // Создание окна
        JFrame frame = new JFrame("Решение уравнений");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание панели для ввода значений
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));

        JLabel labelA = new JLabel("Введите A:");
        JTextField inputA = new JTextField();
        inputPanel.add(labelA);
        inputPanel.add(inputA);

        JLabel labelB = new JLabel("Введите B:");
        JTextField inputB = new JTextField();
        inputPanel.add(labelB);
        inputPanel.add(inputB);

        JLabel labelC = new JLabel("Введите C:");
        JTextField inputC = new JTextField();
        inputPanel.add(labelC);
        inputPanel.add(inputC);

        JLabel labelD = new JLabel("Введите D:");
        JTextField inputD = new JTextField();
        inputPanel.add(labelD);
        inputPanel.add(inputD);

        // Создание панели для вывода результата
        JPanel resultPanel = new JPanel(new FlowLayout());

        JButton calculateButton = new JButton("Решить квадратное уравнение");
        resultPanel.add(calculateButton);

        JButton calculateCubicButton = new JButton("Решить кубическое уравнение");
        resultPanel.add(calculateCubicButton);

        JLabel resultLabel = new JLabel();
        resultPanel.add(resultLabel);

        // Добавление панелей на окно
        frame.getContentPane().setLayout(new BorderLayout());
        frame.getContentPane().add(inputPanel, BorderLayout.CENTER);
        frame.getContentPane().add(resultPanel, BorderLayout.SOUTH);

        // Добавление обработчика кнопки "Рассчитать"
        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Получение значений А, В и С
                    double a = Double.parseDouble(inputA.getText());
                    double b = Double.parseDouble(inputB.getText());
                    double c = Double.parseDouble(inputC.getText());

                    // Вычисление дискриминанта
                    double discriminant = b * b - 4 * a * c;

                    if (discriminant > 0) {
                        // Два действительных корня
                        double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
                        double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
                        resultLabel.setText("Корни: " + root1 + ", " + root2);
                    } else if (discriminant == 0) {
                        // Один действительный корень (корни совпадают)
                        double root = -b / (2 * a);
                        resultLabel.setText("Один корень: " + root);
                    } else {
                        // Корни не являются действительными числами
                        resultLabel.setText("Нет действительных корней");}
                }
                catch (NumberFormatException ex) {
                    resultLabel.setText("Неверные значения коэффициентов");
                }
            }
        });
        calculateCubicButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    double a = Double.parseDouble(inputA.getText());
                    double b = Double.parseDouble(inputB.getText());
                    double c = Double.parseDouble(inputC.getText());
                    double d = Double.parseDouble(inputD.getText());
                    if (a == 0) {
                        resultLabel.setText("Это не кубическое уравнение.");
                        return;
                    }

                    b /= a;
                    c /= a;
                    d /= a;

                    double p = (3 * c - b * b) / 3;
                    double q = (2 * b * b * b - 9 * b * c + 27 * d) / 27;

                    double discriminant = q * q / 4 + p * p * p / 27;

                    if (discriminant > 0) {
                        double delta1 = (-q / 2 + Math.sqrt(discriminant));
                        double delta2 = (-q / 2 - Math.sqrt(discriminant));

                        double x1 = Math.pow(delta1, 1.0 / 3) + Math.pow(delta2, 1.0 / 3) - b / 3;
                        resultLabel.setText("Уравнение имеет один действительный корень:");
                        resultLabel.setText("x = " + x1);
                    } else if (discriminant == 0) {
                        double delta1 = -q / 2;

                        double x1 = Math.pow(delta1, 1.0 / 3) - b / 3;
                        double x2 = -Math.pow(delta1, 1.0 / 3) - b / 3;
                        resultLabel.setText("Уравнение имеет два действительных корня:");
                        resultLabel.setText("x1 = " + x1);
                        resultLabel.setText("x2 = " + x2);
                    } else {
                        q /= 2;
                        double r = Math.sqrt(-Math.pow(p, 3) / 27);

                        double phi = Math.acos(-q / r);
                        double x1 = 2 * Math.cbrt(r) * Math.cos(phi / 3) - b / 3;
                        double x2 = 2 * Math.cbrt(r) * Math.cos((phi + 2 * Math.PI) / 3) - b / 3;
                        double x3 = 2 * Math.cbrt(r) * Math.cos((phi - 2 * Math.PI) / 3) - b / 3;

                        resultLabel.setText("Уравнение имеет три действительных корня:");
                        resultLabel.setText("x1 = " + x1);
                        resultLabel.setText("x2 = " + x2);
                        resultLabel.setText("x3 = " + x3);
                    }
                }
                finally {
                    resultLabel.setText("Корни не являются действительными числами");
                }
            }
        });

        // Установка размеров окна и его отображение
        frame.setBounds(650, 300, 800, 250);
        frame.setVisible(true);
    }
}
