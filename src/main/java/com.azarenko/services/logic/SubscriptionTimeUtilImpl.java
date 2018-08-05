package com.azarenko.services.logic;

import com.azarenko.model.Periodical;
import com.azarenko.services.SubscriptionTimeUtil;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class SubscriptionTimeUtilImpl implements SubscriptionTimeUtil {
    private final int FIRST_HALF_YEAR = 0;
    private final int SECOND_HALF_YEAR = 1;
    private final int YEAR = 2;

    /**
     * This method returns 0 if  first half of the  year now, or 1 if the secon half of the year now
     *
     * @return
     */
    @Override
    public int getHalfYear() {
        int SECOND_HALF_YEAR = 1;
        if ((getMonth() < 5) || (getMonth() == 5 && getDay() < 21)) {
            return FIRST_HALF_YEAR;
        } else
            return SECOND_HALF_YEAR;
    }

    /**
     * This method returns number of exist this periodical in this half year
     *
     * @param periodical
     * @return
     */
    @Override
    public int getNumberOfExist(Periodical periodical) {
        int days = getCountDaysInHalfYEar(getHalfYear());
        return periodical.getOutputFrequency() * days / 360;
    }

    @Override
    public int getCountDaysInHalfYEar(int halfYear) {
        int days = 0;
        if (halfYear == FIRST_HALF_YEAR) {
            days = (6 - getMonth() - 1) * 30 + (30 - getDay());
        } else {
            days = (12 - getMonth() - 1) * 30 + (30 - getDay());
        }
        return days;
    }

    @Override
    public int getDay() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getMonth() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    @Override
    public int getYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    @Override
    public Date getStartDate(int timeSubscription) {
        Calendar calendar = Calendar.getInstance();
        if (timeSubscription == FIRST_HALF_YEAR || timeSubscription == YEAR) {
            return new Date();
        } else if (timeSubscription == SECOND_HALF_YEAR) {
            calendar.set(getYear() - 1, Calendar.JULY, 1);
            return calendar.getTime();
        }
        return null;
    }

    @Override

    public Date getEndDate(int timeSubscription) {

        Calendar calendar = Calendar.getInstance();
        if (timeSubscription == FIRST_HALF_YEAR) {
            calendar.set(getYear() - 1, Calendar.JUNE, 30);
            return calendar.getTime();
        } else if (timeSubscription == SECOND_HALF_YEAR) {
            calendar.set(getYear(), Calendar.DECEMBER, 30);
            return calendar.getTime();
        } else if (timeSubscription == YEAR) {
            calendar.set(getYear() + 1, getMonth(), getDay());
        }
        return new Date();
    }
}
