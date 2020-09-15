package fr.delcey.transformation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.threeten.bp.LocalDateTime;

import java.util.Random;

public class DateRepository {

    public LiveData<LocalDateTime> getDateLiveData() {
        MutableLiveData<LocalDateTime> mutableLiveData = new MutableLiveData<>();

        int randomizedDay = new Random().nextInt(3); // Random a number from 0 to 2 : 0 or 1 or 2
        mutableLiveData.setValue(LocalDateTime.now().plusDays(randomizedDay)); // Set a random day : either today, tomorrow or the day after tomorrow

        return mutableLiveData;
    }
}
