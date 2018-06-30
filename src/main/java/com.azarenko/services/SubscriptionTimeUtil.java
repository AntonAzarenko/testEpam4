package com.azarenko.services;

import com.azarenko.model.Periodical;
import org.springframework.stereotype.Service;


public interface SubscriptionTimeUtil {

    /**
     * This method returns 0 if  first half of the  year now, or 1 if the secon half of the year now
     * @return
     */
    int getHalfYear();

    /**
     * this method returns number of exist this periodical in this half year
     * @param periodical
     * @return
     */
    int getNumberOfExist(Periodical periodical);

    int getCountDaysInHalfYEar(int halfYear);

    int getDay();

    int getMonth();

    int getYear();
}
