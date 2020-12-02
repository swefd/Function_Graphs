package sample;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class Graph extends JFrame {
    private static int DrawGraph = 0;

    private static final int width = 600;                         //Ширина вікна графіку в пікселях
    private static final int height = 600;                        //Висота вікна графіку в пікселях

    private static int CP = 30;                             //Крок масштабування (ціна поділки)

    private static int CalibratorX = 300;                  //Центр вісі Х
    private static int CalibratorY = 300;                  //Центр вісі Y

    private static double Step = 0.01;                    //Крок малювання ліній/точок

    public static void main(String[] args) {
        new sample.Graph();
    }

    static double A = 1;
    static double B = 1;
    static double C = 1;
    private static double K = 1;

    static double M = -10;          // Початок проміжку
    static double N = 10;           // кінець проміжку
    static boolean S = true;      //Шкала

    Graph() {
        setTitle("Графіки Функцій");
        this.setSize(800, 600);    //Розміри вікна
        setResizable(false);                    //Не можна розтягувати
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //Закривати програму при закритті вікна


        //Варіанти в випадаючому списку
        String[] select = new String[]{"0. Виберіть графік", "1. Лінія", "2. Парабола", "3. Гіпербола", "4. Косинус", "5. Синус", "6. Тангенс", "7. Котангенс"};

        JPanel panel = new JPanel();//Панель на яку разміщаються кнопки/поля
        panel.setLayout(null);

        JComboBox<String> box1 = new JComboBox<>(select);//Створення Випаючого списку
        box1.setSize(150, 30);              // розмір
        box1.setLocation(615, 50);                  // Позиція
        panel.add(box1);                                   //Додовання на панель

        JButton bDraw = new JButton();                      //Створення кнопки "Go"
        bDraw.setSize(150, 30);
        bDraw.setLocation(615, 300);
        bDraw.setText("Go");
        panel.add(bDraw);

        JButton bCPplus = new JButton();                    //Створення кнопки +крок
        bCPplus.setSize(150, 30);
        bCPplus.setLocation(615, 460);
        bCPplus.setText("CP +");
        panel.add(bCPplus);

        JButton bCPminus = new JButton();                  //Створення кнопки -крок
        bCPminus.setSize(150, 30);
        bCPminus.setLocation(615, 500);
        bCPminus.setText("Cp -");
        panel.add(bCPminus);


        JButton bCalibratorXp = new JButton();               //Створення кнопки  +Калібратор центру Х абсцис
        bCalibratorXp.setSize(50, 30);
        bCalibratorXp.setLocation(715, 375);
        bCalibratorXp.setText("→");
        panel.add(bCalibratorXp);


        JButton bCalibratorXm = new JButton();
        bCalibratorXm.setSize(50, 30);
        bCalibratorXm.setLocation(615, 375);
        bCalibratorXm.setText("←");
        panel.add(bCalibratorXm);


        JButton bCalibratorYp = new JButton();                 //Створення кнопки +Калібратор Y Ордината
        bCalibratorYp.setSize(50, 30);
        bCalibratorYp.setLocation(665, 345);
        bCalibratorYp.setText("↑");
        panel.add(bCalibratorYp);


        JButton bCalibratorYm = new JButton();
        bCalibratorYm.setSize(50, 30);
        bCalibratorYm.setLocation(665, 405);
        bCalibratorYm.setText("↓");
        panel.add(bCalibratorYm);


        JButton bDefCalibrator = new JButton();              // Кнопка яка скидає калібратор
        bDefCalibrator.setSize(50, 30);
        bDefCalibrator.setLocation(665, 375);
        bDefCalibrator.setText("↺");
        panel.add(bDefCalibrator);

        //Текстві поля
        JTextField v1 = new JTextField();
        v1.setSize(50, 30);
        v1.setLocation(615, 100);
        v1.setText("1");
        panel.add(v1);

        JTextField v2 = new JTextField();
        v2.setSize(50, 30);
        v2.setLocation(615, 150);
        v2.setText("2");
        panel.add(v2);

        JTextField v3 = new JTextField();
        v3.setSize(50, 30);
        v3.setLocation(615, 200);
        v3.setText("3");
        panel.add(v3);

        JTextField v4 = new JTextField();
        v4.setSize(50, 30);
        v4.setLocation(630, 260);
        v4.setText(String.valueOf(M));
        panel.add(v4);

        JTextField v5 = new JTextField();
        v5.setSize(50, 30);
        v5.setLocation(700, 260);
        v5.setText(String.valueOf(N));
        panel.add(v5);


        JTextField v6 = new JTextField();
        v6.setSize(50, 30);
        v6.setLocation(700, 100);
        v6.setText(String.valueOf(Step));
        panel.add(v6);


        //Label текст
        JLabel l1 = new JLabel();
        l1.setSize(50, 30);
        l1.setLocation(615, 75);
        l1.setText("A");
        panel.add(l1);

        JLabel l2 = new JLabel();
        l2.setSize(50, 30);
        l2.setLocation(615, 125);
        l2.setText("B");
        panel.add(l2);

        JLabel l3 = new JLabel();
        l3.setSize(50, 30);
        l3.setLocation(615, 175);
        l3.setText("C");
        panel.add(l3);

        JLabel l4 = new JLabel();
        l4.setSize(100, 30);
        l4.setLocation(660, 230);
        l4.setText("Проміжок");
        panel.add(l4);

        JLabel l5 = new JLabel();
        l5.setSize(100, 30);
        l5.setLocation(700, 75);
        l5.setText("Крок");
        panel.add(l5);

        JLabel l6 = new JLabel();
        l6.setSize(170, 30);
        l6.setLocation(615, 20);
        l6.setText("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-");
        panel.add(l6);

        JCheckBox c1 = new JCheckBox();
        c1.setSize(70, 30);
        c1.setLocation(690, 200);
        c1.setText("Шкала");
        c1.setSelected(true);
        panel.add(c1);


        //Дія після вибору елемента із списку
        box1.addActionListener(e -> {
            DrawGraph = Integer.parseInt(Objects.requireNonNull(box1.getSelectedItem()).toString().substring(0, 1)); // Отримання вибраного елемента із приска, визначається по першому символу

            switch (DrawGraph) {                      // обробка результатів
                case 1:
                    v1.setVisible(true);        // On/Off видимість для текстових полей
                    v2.setVisible(true);
                    v3.setVisible(false);


                    l1.setText("A");
                    l2.setText("B");
                    l6.setText("y = A * x + B");

                    l1.setVisible(true);        // On/Off видимість для Текстових міток
                    l2.setVisible(true);
                    l3.setVisible(false);

                    break;
                case 2:
                    v1.setVisible(true);        // On/Off видимість для текстових полей
                    v2.setVisible(true);
                    v3.setVisible(true);

                    l1.setText("A");
                    l2.setText("B");
                    l3.setText("C");
                    l6.setText("y = A * x^2 + B * x + C");

                    l1.setVisible(true);        // On/Off видимість для Текстових міток
                    l2.setVisible(true);
                    l3.setVisible(true);
                    break;
                case 3:
                    v1.setVisible(true);
                    v2.setVisible(true);
                    v3.setVisible(false);

                    l1.setText("K");
                    l2.setText("A");
                    l6.setText("y = K / A * x");

                    l1.setVisible(true);
                    l2.setVisible(true);
                    l3.setVisible(false);
                    break;
                case 4:
                    l6.setText("y = cos (x)");
                    break;
                case 5:
                    l6.setText("y = sin (x)");
                    break;
                case 6:
                    l6.setText("y = tg (x)");
                    break;
                case 7:
                    l6.setText("y = ctg (x)");
                    break;

            }
        });

        bDraw.addActionListener(e -> {
            switch (DrawGraph) {
                case 1:
                    Graph.A = Double.parseDouble(v1.getText());  //Отримання значень з тектових полей
                    Graph.B = Double.parseDouble(v2.getText());
                case 2:
                    Graph.A = Double.parseDouble(v1.getText());  //Отримання значень з тектових полей
                    Graph.B = Double.parseDouble(v2.getText());
                    Graph.C = Double.parseDouble(v3.getText());
                    break;
                case 3:
                    Graph.A = Double.parseDouble(v1.getText());
                    Graph.K = Double.parseDouble(v2.getText());
            }

            Graph.M = Double.parseDouble(v4.getText());   // Отримання значень початку проміжку
            Graph.N = Double.parseDouble(v5.getText());     // Отримання значень кінця проміжку
            Graph.S = c1.isSelected();                     // Отримання значень чек боксу "Шкала"
            if (Double.parseDouble(v6.getText()) > 0) {
                Graph.Step = Double.parseDouble(v6.getText());
            }
            repaint(); //Виклик методу який все перемальовує
        });

        bCalibratorXp.addActionListener(e -> {
            CalibratorX += CP;
            M--;
            N--;
            repaint();
        });

        bCalibratorXm.addActionListener(e -> {
            CalibratorX -= CP;
            M++;
            N++;

            repaint();
        });

        bCalibratorYp.addActionListener(e -> {
            CalibratorY -= CP;
            repaint();
        });

        bCalibratorYm.addActionListener(e -> {
            CalibratorY += CP;
            repaint();
        });

        bCPplus.addActionListener(e -> {
            switch (CP) {
                case 15:
                    CP = 30;
                    repaint();
                    break;
                case 30:
                    CP = 60;
                    repaint();
                    break;
            }
        });

        bCPminus.addActionListener(e -> {
            switch (CP) {
                case 60:
                    CP = 30;
                    repaint();
                    break;
                case 30:
                    CP = 15;
                    repaint();
                    break;
            }
        });

        bDefCalibrator.addActionListener(e -> {
            CalibratorX = 300;
            CalibratorY = 300;
            CP = 30;
            M = - 10;
            N = 10;

            repaint();
        });


        add(panel);       //Добавлення панелі з кнопками на головне вікно
        setVisible(true); //Зробити головне вікно видимим
        repaint();  //Виклик методу який все перемальовує
    }

    public void paint(Graphics g)       //Функція яка відповідає за малювання (Контекст g)
    {
        super.paint(g);                 //Виклик з батьківського класу JFrame метод Paint
        grid(g);                       //Виклик функції малювання сітки

        if (DrawGraph == 1) {
            Line(g);
        } else if (DrawGraph == 2) {
            Parabola(g);
        } else if (DrawGraph == 3) {
            Hyperbola(g);
        } else if (DrawGraph > 3 && DrawGraph < 8) {
            Func(g);
        }
    }

    private static void grid(Graphics g) {

        for (int x = CalibratorX; x > 0; x = x - CP)                                             //Y ордината лінії
        {
            //Перевірка чи Х не більше ширини вікна графіка
            if (x <= width) {
                g.setColor(Color.gray);
                g.drawLine(x, 0, x, height);
                if (S) {                                                                      //Преевіркка чекбокса "Шкала"
                    g.setColor(Color.black);
                    g.drawLine(x - 1, CalibratorY - 5, x - 1, CalibratorY + 5);
                    g.drawLine(x, CalibratorY - 5, x, CalibratorY + 5);
                }
            }
        }


        for (int x = CalibratorX; x <= height; x = x + CP) {
            g.setColor(Color.gray);
            g.drawLine(x, 0, x, height);
            if (S) {
                g.setColor(Color.black);
                g.drawLine(x - 1, CalibratorY - 5, x - 1, CalibratorY + 5);
                g.drawLine(x, CalibratorY - 5, x, CalibratorY + 5);
            }
        }


        for (int y = CalibratorY; y > 0; y = y - CP)        // X абсцис Лінії
        {
            g.setColor(Color.gray);
            g.drawLine(0, y, width, y);
            if (CalibratorX <= width && S) {
                g.setColor(Color.black);
                g.drawLine(CalibratorX - 5, y, CalibratorX + 5, y);
                g.drawLine(CalibratorX - 5, y - 1, CalibratorX + 5, y - 1);
            }
        }
        for (int y = CalibratorY; y <= width; y = y + CP) {
            g.setColor(Color.gray);
            g.drawLine(0, y, width, y);
            if (CalibratorX <= width && S) {
                g.setColor(Color.black);
                g.drawLine(CalibratorX - 5, y, CalibratorX + 5, y);
                g.drawLine(CalibratorX - 5, y - 1, CalibratorX + 5, y - 1);
            }
        }

        g.setColor(Color.black);

        //x asix
        if (CalibratorX <= width) {
            g.drawLine(CalibratorX, 0, CalibratorX, width);
            g.drawLine(CalibratorX - 1, 0, CalibratorX - 1, width);
        }
        //y asix
        g.drawLine(0, CalibratorY - 1, height, CalibratorY - 1);
        g.drawLine(0, CalibratorY, height, CalibratorY);
    }


    private static void Parabola(Graphics g) {
        g.setColor(Color.red);          //Graphic колір лінії

        for (double x = M; x <= N; x += Step) {
            double y; //Ініціалізация змінної Y
            y = A * x * x + B * x + C;
            //double y = Math.cos(x);
            int xp = 1;
            if (CalibratorX + x * CP <= width) {
                xp = (int) Math.round((CalibratorX + x * CP));
            } //Перевірка чи координати по Х не перевищують 600
            int yp = (int) Math.round(CalibratorY - y * CP);

            g.fillOval(xp - 2, yp - 2, 2, 2);     //Малючання по координатам

        }
    }

    private static void Line(Graphics g) {
        g.setColor(Color.red);          //Graphic колір лінії

        for (double x = M; x <= N; x = x + Step) {
            double y; //Ініціалізация змінної Y
            y = A * x + B;
            int xp = (int) Math.round((CalibratorX + x * CP));

            //} //Перевірка чи координати по Х не перевищують 600    для того щоб не малювати на панелі з кнопками
            int yp = (int) Math.round(CalibratorY - y * CP);

            if (CalibratorX + x * CP <= 600) {
                g.fillOval(xp, yp, 2, 2);  //Малючання по координатам в пікселях
            }
            //g.drawLine(Oxp, Oyp,xp,yp);


        }
    }


    private void Func(Graphics g) {
        g.setColor(Color.red);          //Graphic колір лінії

        for (double x = M; x <= N; x += Step) {
            double y = 1; //Ініціалізация змінної Y
            int xp = 1;
            int yp;

            switch (DrawGraph) {
                case 4:
                    y = Math.cos(x);   //Косинус
                    break;
                case 5:
                    y = Math.sin(x);  //Сінус
                    break;
                case 6:
                    y = Math.tan(x);  //Тангенс
                    break;
                case 7:
                    y = 1 / Math.tan(x);  //Котангенс
                    break;
            }
            if (CalibratorX + x * CP < width) {
                xp = (int) Math.round(CalibratorX + x * CP);
            }  //Перевірка чи координати по Х не перевищують 600
            yp = (int) Math.round(CalibratorY - y * CP);

            g.fillOval(xp - 2, yp - 2, 2, 2); //Малючання по координатам
        }
    }


    private static void Hyperbola(Graphics g) {
        int Oxp = 0, Oyp = 0, Oxp1 = 0, Oyp1 = 0; // Тимчасові змінні збереження попередньої точнки для малювання лінії

        g.setColor(Color.red);              //Graphic колір лінії
        int xp = 0;
        int xp1 = 0;

/*        for (double x = Step; x <= N; x += Step) {
            double y = K / (A * x);


                xp = (int) Math.round(CalibratorX - x * CP);
                xp1 = (int) Math.round(CalibratorX + x * CP);


            int yp = (int) Math.round(CalibratorY + y * CP);

            int yp1 = (int) Math.round(CalibratorY - y * CP);
            System.out.println(y);
            if (x != Step) {
                //Малючання по координатам
                if (CalibratorX - x * CP < width) {
                    g.drawLine(Oxp - 1, Oyp - 1, xp - 1, yp - 1);              // -1 для того щоб лінія було жирною
                    g.drawLine(Oxp, Oyp, xp, yp);             //повторне малювання лінії
                    System.out.println("0xp = " +  Oxp + "0yp = " + Oyp + "xp " + xp);
                }

                if (CalibratorX + x * CP < width) {  //Fixed
                    g.drawLine(Oxp1 - 1, Oyp1 - 1, xp1 - 1, yp1 - 1);
                    g.drawLine(Oxp1, Oyp1, xp1, yp1);
                }
            }

            Oxp = xp;
            Oyp = yp;
            Oxp1 = xp1;
            Oyp1 = yp1;                                //Запис значень в тимчасові змінні
        }*/










        for (double x = Step; x <= N; x += Step) {
            double y = K / (A * x);


            xp = (int) Math.round(CalibratorX - x * CP);
            xp1 = (int) Math.round(CalibratorX + x * CP);


            int yp = (int) Math.round(CalibratorY + y * CP);

            int yp1 = (int) Math.round(CalibratorY - y * CP);
            System.out.println(y);
            if (x != Step) {
                //Малючання по координатам
                if (CalibratorX - x * CP < width) {
                    g.drawLine(Oxp - 1, Oyp - 1, xp - 1, yp - 1);              // -1 для того щоб лінія було жирною
                    g.drawLine(Oxp, Oyp, xp, yp);             //повторне малювання лінії
                    System.out.println("0xp = " +  Oxp + "0yp = " + Oyp + "xp " + xp);
                }

                if (CalibratorX + x * CP < width) {  //Fixed
                    g.drawLine(Oxp1 - 1, Oyp1 - 1, xp1 - 1, yp1 - 1);
                    g.drawLine(Oxp1, Oyp1, xp1, yp1);
                }
            }

            Oxp = xp;
            Oyp = yp;
            Oxp1 = xp1;
            Oyp1 = yp1;                                //Запис значень в тимчасові змінні
        }










    }
}