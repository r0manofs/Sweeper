package sweeper;

import java.util.ArrayList;
import java.util.Random;
/*
 * Size of Field
 */
public class Ranges {
    private static Coord size;
    private static ArrayList<Coord> allCords;
    private static Random random = new Random();

    public static Coord getSize() {
        return size;
    }

    //не понял что за нижнее подчеркивание
    public static void setSize(Coord _size) {
        size = _size;
        allCords = new ArrayList<Coord>();
        for (int y = 0; y < size.y; y++) {
            for (int x = 0; x < size.x; x++) {
                allCords.add(new Coord(x, y));
            }
        }
    }

    public static ArrayList<Coord> getAllCoords() {
        return allCords;
    }

    static boolean inRange(Coord coord) {
        return coord.x >= 0 && coord.x < size.x &&
                coord.y >= 0 && coord.y < size.y;
    }

    static Coord getRandomCoord() {
        return new Coord(random.nextInt(size.x),
                random.nextInt(size.y));
    }

    static ArrayList<Coord> getCoordsAround(Coord coord) {
        Coord around;
        ArrayList<Coord> list = new ArrayList<>();
        //перебираем все клетки вокруг бомб
        for (int x = coord.x - 1; x <= coord.x + 1; x++)
            for (int y = coord.y - 1; y <= coord.y + 1; y++)
                if (inRange(around = new Coord(x, y)))
                    if (!around.equals(coord))
                        list.add(around);
        return list;
    }
}
