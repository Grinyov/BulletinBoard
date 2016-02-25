package com.grinyov.bulletinboard.model;

import org.joda.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 *
 * Entity used to store information about ads.
 */
public class Advert implements Serializable{

    private long id;
    private Account account;
    private Category category;

    @NotNull(message = "title must be not empty")
    @Size(min = 10, max = 30, message = "title size must be between 10 and 30")
    private String title;

    @NotNull(message = "advert must be not empty")
    @Size(min = 20, max = 400, message = "message size must be between 20 and 400")
    private String text;

    LocalDateTime publication = new LocalDateTime();
}
