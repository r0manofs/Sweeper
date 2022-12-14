
import sweeper.Box;
import sweeper.Coord;
import sweeper.Game;
import sweeper.Ranges;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Sweeper extends JFrame {
    private Game game;
    private JLabel label;
    private JPanel panel;
    private final int COLS = 9;
    private final int ROWS = 9;
    private final int BOMBS = 10;
    private final int IMG_SIZE = 50;

    public static void main(String[] args) {
        new Sweeper();
    }

    private Sweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel(" Welcome!");
        add(label, BorderLayout.SOUTH);
    }

    private void initPanel() {
        panel = new JPanel() {
            //тут можно переделать в лямбду наверное
            //ordinal() сдвигает вправо, на размер картинки
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image,
                            coord.x * IMG_SIZE,
                            coord.y * IMG_SIZE, this);
                }
            }
        };
        //управление мышью
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //координаты нажатия
                int x = e.getX() / IMG_SIZE;
                int y = e.getY() / IMG_SIZE;
                Coord coord = new Coord(x, y);
                //открыть клетку
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                //флажки
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                //перезапуск
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMG_SIZE,
                Ranges.getSize().y * IMG_SIZE)); //размер окна
        add(panel);
    }

    private String getMessage() {
        return switch (game.getState()) {
            case PLAYED -> "Think twice!";
            case BOMBED -> "You loose...";
            case WINNER -> "CONGRATS, you win!!!";
        };
    }

    private void initFrame() {
        //Docs
        /*
         * Инициализация рамки:
         * Чтобы крестик закрывал программу
         * Заголовок окна
         * Не изменяемый размер
         * Видимость
         * Иконка в Проводнике/файндере
         * Окно открывается по центру
         * */
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();
        setLocationRelativeTo(null);
    }

    public void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name());
        }
    }

    private Image getImage(String name) {
        //Docs
        /*
         * Take images from res package
         * */
        String fileName = name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(fileName)));

        return icon.getImage();
    }
}
