package com.test.constant;

/**
 * Created by rmiao on 2/24/2017.
 */
public enum Week {
    MONGDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), STATURDAY(6), SUNDAY(7);

    private int num;

    Week(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
