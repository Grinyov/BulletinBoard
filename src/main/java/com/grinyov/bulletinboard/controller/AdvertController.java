package com.grinyov.bulletinboard.controller;

import com.grinyov.bulletinboard.dao.AdvertDao;
import com.grinyov.bulletinboard.exception.NoSuchAdvertException;
import com.grinyov.bulletinboard.model.Advert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;
import java.util.List;

im

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
    public ModelAndView listAccounts(ModelAndView model) throws NoSuchAdvertException {
        List<Advert> ads = advertDao.getAllAds();
        model.addObject("ads", ads);
        model.setViewName("ads");
        return model;
    }

    /*

    @RequestMapping(value = "accounts")
    public ModelAndView listAccounts(ModelAndView model) throws IOException{
        List<Account> accounts = accountDao.list();
        model.addObject("accounts", accounts);
        model.setViewName("admin/accounts");
        return model;
    }

    @RequestMapping(value = "accounts/newAccount", method = RequestMethod.GET)
    public ModelAndView newAccount(ModelAndView model){
       Account account = new Account();
       model.addObject("account", account);
       model.setViewName("admin/accountForm");
       return model;
    }

    @RequestMapping(value = "accounts/newAccount", method = RequestMethod.POST)
    public ModelAndView saveAccount(@Valid @ModelAttribute Account account, BindingResult result, SessionStatus status){
        logger.info("Creating account");
        Set<ConstraintViolation<Account>> violations = validator.validate(account);
        for (ConstraintViolation<Account> violation : violations)
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
            return new ModelAndView("admin/accountForm");
        }else {
            accountDao.insertOrUpdate(account);
            status.setComplete();
            return new ModelAndView("redirect:admin/accounts");
        }
    }

    @RequestMapping(value = "accounts/deleteAccount", method = RequestMethod.GET)
    public ModelAndView deleteAccount(HttpServletRequest request) {
        int accountId = Integer.parseInt(request.getParameter("id"));
        accountDao.delete(accountId);
        return new ModelAndView("redirect:admin/accounts");
    }

    @RequestMapping(value = "accounts/editAccount", method = RequestMethod.GET)
    public ModelAndView editAccount(HttpServletRequest request){
        int accountId = Integer.parseInt(request.getParameter("id"));
        Account account = accountDao.get(accountId);
        ModelAndView model = new ModelAndView("admin/accountForm");
        model.addObject("account", account);
        return model;
    }

    @RequestMapping(value = "accounts/editAccount", method = RequestMethod.POST)
    public ModelAndView updateAccount(@ModelAttribute Account account){
        accountDao.insertOrUpdate(account);
        return new ModelAndView("redirect:admin/accounts");
    }

     */
}
