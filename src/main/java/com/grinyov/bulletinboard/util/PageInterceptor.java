package com.grinyov.bulletinboard.util;

import com.grinyov.bulletinboard.dao.AdvertDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * @author Vitaliy Grinyov
 * @since 2016
 */
public class PageInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AdvertDao advertDao;

    @Value("${top_annc_limit}")
    private String topAnncLimitStr;

    @Value("${title.currency_uah}")
    private String currencyUAHTitle;

    @Value("${title.currency_usd}")
    private String currencyUSDTitle;

    @Value("${title.currency_eur}")
    private String currencyEURTitle;

    @Value("${unit.uah}")
    private String unitUAHCode;

    @Value("${unit.usd}")
    private String unitUSDCode;

    @Value("${unit.eur}")
    private String unitEURCode;

    @Value("${res_ver}")
    private String resVer;

    private static String USDUAHCurrencyAttr;
    private static String EURUAHCurrencyAttr;
    private static boolean isCurrencyLoaded = false;

/*    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // displays only single one top announcement in sidebar on the right side
        int topAnncLimit;
        try {
            topAnncLimit = Integer.parseInt(topAnncLimitStr);
            if (topAnncLimit < 3)
                throw new NumberFormatException();
        } catch (NumberFormatException ignored) {
            topAnncLimit = 3;
        }

        List<Advert> topAnnc = advertDao.getTopAdverts();


        if (topAnnc != null) {
            int size = topAnnc.size();

            if (size >= topAnncLimit) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
                for (Advert advert : topAnnc) {
                    advert.setTimeString(simpleDateFormat.format(advert.getTime()));
                }

                Collections.shuffle(topAnnc);
                request.setAttribute("top_annc", topAnnc.subList(0, topAnncLimit - 1));
                request.setAttribute("top_ann", topAnnc.get(topAnncLimit - 1));
            }
        }
    }*/
}
