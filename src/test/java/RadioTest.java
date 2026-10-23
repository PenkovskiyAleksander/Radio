import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RadioTest {
    private Radio radio;

    @BeforeEach
    public void setUp() {
        radio = new Radio();
    }

    // --- Тесты для радиостанций ---

    @Test
    public void shouldSetValidStation() {
        radio.setCurrentStation(5);
        assertEquals(5, radio.getCurrentStation());
    }

    @Test
    public void shouldNotSetInvalidStation() {
        radio.setCurrentStation(10); // Больше 9
        assertEquals(0, radio.getCurrentStation()); // Осталось дефолтным

        radio.setCurrentStation(-1); // Меньше 0
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchToNextStation() {
        radio.setCurrentStation(5);
        radio.next();
        assertEquals(6, radio.getCurrentStation());
    }

    @Test
    public void shouldWrapAroundNextStation() {
        radio.setCurrentStation(9);
        radio.next();
        assertEquals(0, radio.getCurrentStation()); // С 9 на 0
    }

    @Test
    public void shouldSwitchToPrevStation() {
        radio.setCurrentStation(5);
        radio.prev();
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    public void shouldWrapAroundPrevStation() {
        radio.setCurrentStation(0);
        radio.prev();
        assertEquals(9, radio.getCurrentStation()); // С 0 на 9
    }

    // --- Тесты для громкости ---

    @Test
    public void shouldIncreaseVolume() {
        radio.setCurrentStation(0); // просто инициализация
        // Дефолтная громкость 0, увеличим
        radio.increaseVolume();
        assertEquals(1, radio.getCurrentVolume());
    }

    @Test
    public void shouldNotExceedMaxVolume() {
        // Нам нужно "загнать" громкость в 100
        for (int i = 0; i < 100; i++) {
            radio.increaseVolume();
        }
        assertEquals(100, radio.getCurrentVolume());

        // Пытаемся увеличить дальше
        radio.increaseVolume();
        assertEquals(100, radio.getCurrentVolume()); // Осталось 100
    }

    @Test
    public void shouldDecreaseVolume() {
        radio.increaseVolume(); // Стало 1
        radio.decreaseVolume(); // Стало 0
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    public void shouldNotGoBelowMinVolume() {
        // Дефолтная громкость 0, пытаемся уменьшить
        radio.decreaseVolume();
        assertEquals(0, radio.getCurrentVolume()); // Осталось 0
    }
}