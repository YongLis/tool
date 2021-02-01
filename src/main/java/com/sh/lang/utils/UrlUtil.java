package com.sh.lang.utils;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

public class UrlUtil {
    public static String getHostFromUrl(URL url) {
        return url.getHost();
    }

    public static int getPortFromUrl(URL url) {
        return url.getPort() == -1 ? url.getDefaultPort() : url.getPort();
    }

    public static String getProtocolFromUrl(URL url) {
        return url.getProtocol();
    }

    public static String getRequestPath(URL url) {
        String requestPath = null;
        if (url.getQuery() == null) {
            requestPath = url.getPath();
        } else {
            requestPath = url.getPath() + "?" + url.getQuery();
        }

        if (StringUtils.isBlank(requestPath)) {
            requestPath = "/";
        }

        return requestPath;

    }

    public static String getEndPoint(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            return null;
        }
        String queryString = url.getQuery();
        return url.toString().replace("?" + queryString, "");
    }

    public static String urlEncode(String s) {
        String encoded = null;
        try {
            encoded = URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException ignore) {
        }
        StringBuffer buf = new StringBuffer(encoded.length());
        char focus;
        for (int i = 0; i < encoded.length(); i++) {
            focus = encoded.charAt(i);
            if (focus == '*') {
                buf.append("%2A");
            } else if (focus == '+') {
                buf.append("%20");
            } else if (focus == '%' && (i + 1) < encoded.length() && encoded.charAt(i + 1) == '7'
                       && encoded.charAt(i + 2) == 'E') {
                buf.append('~');
                i += 2;
            } else {
                buf.append(focus);
            }
        }
        return buf.toString();
    }

    public static String urlDecode(String s) {
        String decoded = null;
        try {
            decoded = URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException ignore) {
        }

        return decoded;
    }

}
