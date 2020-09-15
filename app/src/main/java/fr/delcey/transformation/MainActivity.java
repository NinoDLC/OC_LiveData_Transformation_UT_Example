package fr.delcey.transformation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final MainViewModel mainViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(MainViewModel.class);

        final TextView dateTextView = findViewById(R.id.textViewDate);
        final TextView dateIsTodayTextView = findViewById(R.id.textViewToday);

        mainViewModel.getUiModelLiveData().observe(this, new Observer<MainUiModel>() {
            @Override
            public void onChanged(MainUiModel mainUiModel) {
                dateTextView.setText(mainUiModel.getDate());

                if (mainUiModel.isTodayVisible()) {
                    dateIsTodayTextView.setVisibility(View.VISIBLE);
                } else {
                    dateIsTodayTextView.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}