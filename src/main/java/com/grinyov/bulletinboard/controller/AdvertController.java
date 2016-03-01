package com.grinyov.bulletinboard.controller;

import com.grinyov.bulletinboard.dao.AdvertDao;
import com.grinyov.bulletinboard.exception.NoSuchAdvertException;
import com.grinyov.bulletinboard.model.Advert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.util.List;
import java.util.Set;


/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
@Controller
public class AdvertController {

    private static final Logger logger = LoggerFactory.getLogger(AdvertController.class);

    @Autowired
    private AdvertDao advertDao;

    private Validator validator;

    public AdvertController(){
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @RequestMapping(value = "ads")
    public ModelAndView listAds(ModelAndView model) throws NoSuchAdvertException {
        List<Advert> ads = advertDao.getAllAds();
        model.addObject("ads", ads);
        model.setViewName("ads");
        return model;
    }

    @RequestMapping(value = "ads/newAdvert", method = RequestMethod.GET)
    public ModelAndView newAdvert(ModelAndView model){
        Advert advert = new Advert();
        model.addObject("advert", advert);
        model.setViewName("advertForm");
        return model;
    }

    @RequestMapping(value = "ads/newAdvert", method = RequestMethod.POST)
    public ModelAndView saveAdvert(@Valid @ModelAttribute Advert advert, BindingResult result, SessionStatus status){
        logger.info("Creating account");
        Set<ConstraintViolation<Advert>> violations = validator.validate(advert);
        for (ConstraintViolation<Advert> violation : violations)
        {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            // Add JSR-303 errors to BindingResult
            // This allows Spring to display them in view via a FieldError
            result.addError(new FieldError("account",propertyPath,

                    "Invalid "+ propertyPath + "(" + message + ")"));
        }
        if (result.hasErrors()){
            logger.info("Returning to creation page");
            return new ModelAndView("advertForm");
        }else {
            advertDao.addAdvert(advert);
            status.setComplete();
            return new ModelAndView("redirect:ads");
        }
    }

    @RequestMapping(value = "accounts/deleteAccount", method = RequestMethod.GET)
    public ModelAndView deleteAdvert(HttpServletRequest request) {
        int accountId = Integer.parseInt(request.getParameter("id"));
        advertDao.deleteAdvert(accountId);
        return new ModelAndView("redirect:ads");
    }

    @RequestMapping(value = "ads/editAdvert", method = RequestMethod.GET)
    public ModelAndView editAdvert(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        Advert advert = advertDao.getAdvertById(id);
        ModelAndView model = new ModelAndView("advertForm");
        model.addObject("advert", advert);
        return model;
    }

    @RequestMapping(value = "ads/editAdvert", method = RequestMethod.POST)
    public ModelAndView updateAdvert(@ModelAttribute Advert advert){
        advertDao.addAdvert(advert);
        return new ModelAndView("redirect:ads");
    }

}
