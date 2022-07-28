package ru.geekbrainsstudent.calculator.model;

import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import ru.geekbrainsstudent.calculator.R;

public enum Theme {
    ONE(R.style.Theme_Calculator,R.string.theme_1, "themeone"),
    TWO(R.style.Theme_Calculator_V2,R.string.theme_2, "themetwo"),
    THREE(R.style.Theme_Calculator_V3,R.string.theme_3, "themethree");

    @StyleRes
    private int themeRes;
    @StringRes
    private int title;
    private String key;

    Theme(int themeRes, int title, String key) {
        this.themeRes = themeRes;
        this.title = title;
        this.key = key;
    }

    public int getThemeRes() {
        return themeRes;
    }

    public int getTitle() {
        return title;
    }

    public String getKey() {
        return key;
    }
}
