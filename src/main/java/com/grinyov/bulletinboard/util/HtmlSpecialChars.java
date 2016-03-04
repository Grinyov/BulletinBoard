package com.grinyov.bulletinboard.util;

import org.springframework.stereotype.Component;

/**
 * @author Vitaliy Grinyov
 * @since 2016
 *
 * A HtmlSpecialChars is a service class, generally used for converting html special
 * characters into html codes, needed for providing secure user input processing
 */
@Component("htmlSpecialChars")
public class HtmlSpecialChars {

    /**
     * @param str - string line which contains html special chars to be replaced with html codes
     * @return clean string line which does not contain any html special chars
     */
    public String replaceChars(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            switch (c) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '\"':
                    sb.append("&quot;");
                    break;
                case '\'':
                    sb.append("&apos;");
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
