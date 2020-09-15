package fr.delcey.transformation;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.threeten.bp.Clock;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DateRepository dateRepository = Mockito.mock(DateRepository.class);

    private MutableLiveData<LocalDateTime> localDateTimeMutableLiveData;

    @Before
    public void setUp() {
        localDateTimeMutableLiveData = new MutableLiveData<>();

        Mockito.doReturn(localDateTimeMutableLiveData).when(dateRepository).getDateLiveData();
    }

    @Test
    public void mapWhenTodayIsRandomizedDay() throws InterruptedException {
        // Given
        // Today IS the same day that is given by repository !
        ZonedDateTime randomizedDate = ZonedDateTime.of(2020, 9, 15, 21, 19, 0, 0, ZoneId.systemDefault());
        ZonedDateTime todayDate = ZonedDateTime.of(2020, 9, 15, 21, 19, 0, 0, ZoneId.systemDefault());
        Clock clock = Clock.fixed(todayDate.toInstant(), ZoneId.systemDefault());

        localDateTimeMutableLiveData.setValue(randomizedDate.toLocalDateTime());

        MainViewModel viewModel = new MainViewModel(dateRepository, clock);

        // When
        MainUiModel result = LiveDataTestUtils.getOrAwaitValue(viewModel.getUiModelLiveData(), 1);

        // Then
        assertEquals(new MainUiModel("mardi 15 septembre 2020 21 h 19 CET", true), result);
    }

    @Test
    public void mapWhenTodayIsNotRandomizedDay() throws InterruptedException {
        // Given
        // Today is NOT the same day that is given by repository !
        ZonedDateTime randomizedDate = ZonedDateTime.of(2020, 9, 16, 21, 19, 0, 0, ZoneId.systemDefault());
        ZonedDateTime todayDate = ZonedDateTime.of(2020, 9, 15, 21, 19, 0, 0, ZoneId.systemDefault());
        Clock clock = Clock.fixed(todayDate.toInstant(), ZoneId.systemDefault());

        localDateTimeMutableLiveData.setValue(randomizedDate.toLocalDateTime());

        MainViewModel viewModel = new MainViewModel(dateRepository, clock);

        // When
        MainUiModel result = LiveDataTestUtils.getOrAwaitValue(viewModel.getUiModelLiveData(), 1);

        // Then
        // Field "isTodayVisible" is false because the randomized day from repo is not today
        assertEquals(new MainUiModel("mercredi 16 septembre 2020 21 h 19 CET", false), result);
    }
}
