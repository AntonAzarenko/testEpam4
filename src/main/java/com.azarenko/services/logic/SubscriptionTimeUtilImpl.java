package com.azarenko.services.logic;

import com.azarenko.model.Periodical;
import com.azarenko.services.SubscriptionTimeUtil;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class SubscriptionTimeUtilImpl implements SubscriptionTimeUtil {

    /**
     * This method returns 0 if  first half of the  year now, or 1 if the secon half of the year now
     *
     * @return
     */
    @Override
    public int getHalfYear() {
        if ((getMonth() < 5) || (getMonth() == 5 && getDay() < 21)) {
            return 0;
        } else
            return 1;
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
        if (halfYear == 0) {
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
    public Date getEndDate(int timeSubscription) {

        if (timeSubscription == 0 ){
            Calendar calendar = Calendar.getInstance();
            calendar.set(getYear()-1,5,30);
            Date date = calendar.getTime();
            return date;
        }else {
            Calendar calendar = Calendar.getInstance();
            calendar.set(getYear(),11,30);
            Date date = calendar.getTime();
            return date;
        }
    }
}
