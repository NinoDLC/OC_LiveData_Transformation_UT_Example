package fr.delcey.transformation;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import org.threeten.bp.Clock;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.FormatStyle;

public class MainViewModel extends ViewModel {

    @NonNull
    private final DateRepository dateRepository;
    @NonNull
    private final Clock clock;

    public MainViewModel(@NonNull DateRepository dateRepository, @NonNull Clock clock) {
        this.dateRepository = dateRepository;
        this.clock = clock;
    }

    // Maps a LocalDateTime to a MainUiModel : date is more readable for user,
    // and a special message appears if the randomized day from repo is the same day as today
    public LiveData<MainUiModel> getUiModelLiveData() {
        return Transformations.map(dateRepository.getDateLiveData(), new Function<LocalDateTime, MainUiModel>() {
            @Override
            public MainUiModel apply(LocalDateTime localDateTime) {
                boolean isTodayVisible = localDateTime.toLocalDate().equals(LocalDate.now(clock));

                return new MainUiModel(
                    localDateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).withZone(ZoneId.systemDefault())),
                    isTodayVisible
                );
            }
        });
    }
}
