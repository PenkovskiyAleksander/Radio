import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RadioTest {

    @Test
    public void shouldCreateRadioWithDefault10Stations() {
        Radio radio = new Radio();
        radio.setCurrentStation(9);
        assertEquals(9, radio.getCurrentStation());
        radio.next();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    public void shouldCreateRadioWithCustomStationsCount() {
        Radio radio = new Radio(20);
        radio.setCurrentStation(19);
        assertEquals(19, radio.getCurrentStation());
        radio.next();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    public void shouldFallbackTo10StationsIfInvalidCount() {
        Radio radio = new Radio(0); //
        radio.setCurrentStation(9);
        assertEquals(9, radio.getCurrentStation());
    }

    @Test
    public void shouldSetValidStation() {
        Radio radio = new Radio(5);
        radio.setCurrentStation(4);
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    public void shouldNotSetInvalidStation() {
        Radio radio = new Radio(5);
        radio.setCurrentStation(5);
        assertEquals(0, radio.getCurrentStation());

        radio.setCurrentStation(-1); // Меньше 0
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchToNextStation() {
        Radio radio = new Radio(5);
        radio.setCurrentStation(2);
        radio.next();
        assertEquals(3, radio.getCurrentStation());
    }

    @Test
    public void shouldWrapAroundNextStation() {
        Radio radio = new Radio(5);
        radio.setCurrentStation(4);
        radio.next();
        assertEquals(0, radio.getCurrentStation());
    }

    @Test
    public void shouldSwitchToPrevStation() {
        Radio radio = new Radio(5);
        radio.setCurrentStation(3);
        radio.prev();
        assertEquals(2, radio.getCurrentStation());
    }

    @Test
    public void shouldWrapAroundPrevStation() {
        Radio radio = new Radio(5);
        radio.setCurrentStation(0);
        radio.prev();
        assertEquals(4, radio.getCurrentStation());
    }

    @Test
    public void shouldIncreaseVolume() {
        Radio radio = new Radio();
        radio.increaseVolume();
        assertEquals(1, radio.getCurrentVolume());
    }

    @Test
    public void shouldNotExceedMaxVolume() {
        Radio radio = new Radio();
        for (int i = 0; i < 100; i++) {
            radio.increaseVolume();
        }
        assertEquals(100, radio.getCurrentVolume());
        radio.increaseVolume();
        assertEquals(100, radio.getCurrentVolume());
    }

    @Test
    public void shouldDecreaseVolume() {
        Radio radio = new Radio();
        radio.increaseVolume();
        radio.decreaseVolume();
        assertEquals(0, radio.getCurrentVolume());
    }

    @Test
    public void shouldNotGoBelowMinVolume() {
        Radio radio = new Radio();
        radio.decreaseVolume();
        assertEquals(0, radio.getCurrentVolume());
    }
}