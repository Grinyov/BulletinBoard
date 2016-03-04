package com.grinyov.bulletinboard.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
@Component
public class Pagination {

    private String numberOfItemsPerPageStr;
    private int numberOfItemsPerPage;

    @Value("${items_per_page}")
    public void setNumberOfItemsPerPageStr(String numberOfItemsPerPageStr) {
        this.numberOfItemsPerPageStr = numberOfItemsPerPageStr;

        try {
            numberOfItemsPerPage = Integer.parseInt(numberOfItemsPerPageStr);
            if (numberOfItemsPerPage < 5)
                throw new NumberFormatException();
        } catch (NumberFormatException ignored) {
            numberOfItemsPerPage = 5;
        }

    }

    /**
     * @param items  - items to be paginated
     * @param pageId - id of the current page
     */
    public <T> List<T> getItemsPerPage(final List<T> items, int pageId) {
        List<T> itemsPerPage = new ArrayList<>();
        int itemsNumber = items.size();

        int firstIndex = (pageId - 1) * numberOfItemsPerPage;
        int lastIndex = firstIndex + numberOfItemsPerPage - 1;

        // if there are items more than numberOfItemsPerPage,
        // then slice them from the first index to last
        if ((itemsNumber - firstIndex) > numberOfItemsPerPage) {
            for (int i = firstIndex; i <= lastIndex; i++) {
                itemsPerPage.add(items.get(i));
            }
            // else, slice them from the first index to the end of the list
        } else {
            for (int i = firstIndex; i < itemsNumber; i++) {
                itemsPerPage.add(items.get(i));
            }
        }
        return itemsPerPage;
    }

    public <T> int getPagesNumber(final List<T> items) {
        int itemsCount = items.size();
        int pagesCount;

        if (itemsCount % numberOfItemsPerPage == 0) {
            pagesCount = itemsCount / numberOfItemsPerPage;
        } else {
            pagesCount = itemsCount / numberOfItemsPerPage + 1;
        }
        return pagesCount;
    }
}
