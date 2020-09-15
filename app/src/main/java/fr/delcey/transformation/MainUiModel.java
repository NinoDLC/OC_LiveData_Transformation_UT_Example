package fr.delcey.transformation;

import java.util.Objects;

public class MainUiModel {

    private final String date;
    private final boolean isTodayVisible;

    public MainUiModel(String date, boolean isTodayVisible) {
        this.date = date;
        this.isTodayVisible = isTodayVisible;
    }

    public String getDate() {
        return date;
    }

    public boolean isTodayVisible() {
        return isTodayVisible;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MainUiModel that = (MainUiModel) o;
        return isTodayVisible == that.isTodayVisible &&
            Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, isTodayVisible);
    }

    @Override
    public String toString() {
        return "MainUiModel{" +
            "date='" + date + '\'' +
            ", isTodayVisible=" + isTodayVisible +
            '}';
    }
}
