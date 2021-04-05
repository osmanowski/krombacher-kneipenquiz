package com.groupfive.krombacherkneipenquiz.hostclient;
import javax.servlet.http.HttpServletRequest;

public class Ip {
    String ipAddress;

    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }

}
