package sweeper;

class Bomb {
    private Matrix bombMap;
    private int totalBombs;

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    //start initialization
    void start() {
        bombMap = new Matrix(Box.ZERO);
        for (int i = 0; i < totalBombs; i++) {
            placeBomb();
        }
    }

    Box get(Coord coord) {
        return bombMap.get(coord);
    }

    private void fixBombsCount() {
        //проверка что бомб не больше чем половина
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs>maxBombs)
            totalBombs=maxBombs;
    }

    private void placeBomb() {
        //while для того чтобы бомбы не попадали дважды в одну клетку
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            //условие проверки
            if (Box.BOMB == bombMap.get(coord))
                continue;
            bombMap.set(coord, Box.BOMB);
            //проходимся циклом и ставим 1 вокруг бомб
            incNumbersAroundBomb(coord);
            break;
        }
    }

    private void incNumbersAroundBomb(Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord))
            if (Box.BOMB != bombMap.get(around))
                bombMap.set(around, bombMap.get(around).getNextNumberBox());
    }

    int getTotalBombs() {
        return totalBombs;
    }
}
