public class Radio {
    private int currentStation;
    private int currentVolume;
    private int stationsCount;

    // Конструктор по умолчанию (10 станций)
    public Radio() {
        this(10);
    }

    // Конструктор с параметром
    public Radio(int stationsCount) {
        if (stationsCount > 0) {
            this.stationsCount = stationsCount;
        } else {
            this.stationsCount = 10; // Защита от некорректного ввода
        }
    }

    public int getCurrentStation() {
        return currentStation;
    }

    public int getCurrentVolume() {
        return currentVolume;
    }

    public void setCurrentStation(int station) {
        // Номер станции от 0 до stationsCount - 1
        if (station >= 0 && station < stationsCount) {
            this.currentStation = station;
        }
    }

    public void next() {
        // Если текущая максимальная (stationsCount - 1), то переключаем на 0
        if (currentStation == stationsCount - 1) {
            currentStation = 0;
        } else {
            currentStation++;
        }
    }

    public void prev() {
        // Если текущая 0, то переключаем на максимальную (stationsCount - 1)
        if (currentStation == 0) {
            currentStation = stationsCount - 1;
        } else {
            currentStation--;
        }
    }

    public void increaseVolume() {
        if (currentVolume < 100) {
            currentVolume++;
        }
    }

    public void decreaseVolume() {
        if (currentVolume > 0) {
            currentVolume--;
        }
    }
}