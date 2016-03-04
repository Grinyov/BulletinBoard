package com.grinyov.bulletinboard.util;

import com.grinyov.bulletinboard.model.Advert;

import java.util.Comparator;

public final class AdvertComparator {

    public static final Comparator<Advert> COMPARE_BY_ID = new Comparator<Advert>() {
        @Override
        public int compare(Advert o1, Advert o2) {
            return Long.compare(o1.getId(), o2.getId());
        }
    };

    public static final Comparator<Advert> COMPARE_BY_DATE = new Comparator<Advert>() {
        @Override
        public int compare(Advert o1, Advert o2) {
            return Long.compare(o1.getPublication().getTime(), o2.getPublication().getTime());
        }
    };

}
