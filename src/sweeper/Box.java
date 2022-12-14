package sweeper;

public enum Box {
    /*
     * Buried layer(то что происхоит после нажатия на квадратик)
     * 1)Numbers
     * Up layer(то что видимо до нажатия)
     * 1)Empty blocks
     * 2)Flag
     * 3)Bomb
     * */
    ZERO,
    NUM1,
    NUM2,
    NUM3,
    NUM4,
    NUM5,
    NUM6,
    NUM7,
    NUM8,
    BOMB,
    OPENED,
    CLOSED,
    FLAGED,
    BOMBED,
    NOBOMB;

    public Object image;
    //getNextNumberBox() возвращает следующий бокс NUM1->NUM2
    Box getNextNumberBox() {
        return Box.values()[this.ordinal() + 1];
    }

     int getNumber() {
        return this.ordinal();
    }
}
