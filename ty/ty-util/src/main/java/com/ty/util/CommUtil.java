package com.ty.util;

import javax.servlet.http.HttpServletRequest;

public class CommUtil {

    public static int null2Int(Object s) {
        int v = 0;
        if (s != null)
            try {
                v = Integer.parseInt(s.toString());
            } catch (Exception localException) {
            }
        return v;
    }

    public static float null2Float(Object s) {
        float v = 0.0F;
        if (s != null)
            try {
                v = Float.parseFloat(s.toString());
            } catch (Exception localException) {
            }
        return v;
    }

    public static double null2Double(Object s) {
        double v = 0.0D;
        if (s != null)
            try {
                v = Double.parseDouble(null2String(s));
            } catch (Exception localException) {
            }
        return v;
    }

    public static boolean null2Boolean(Object s) {
        boolean v = false;
        if (s != null)
            try {
                v = Boolean.parseBoolean(s.toString());
            } catch (Exception localException) {
            }
        return v;
    }

    public static String null2String(Object s) {
        return s == null ? "" : s.toString().trim();
    }

    public static Long null2Long(Object s) {
        Long v = Long.valueOf(0L);
        if (s != null)
            try {
                v = Long.valueOf(Long.parseLong(s.toString()));
            } catch (Exception localException) {
            }
        return v;
    }

    public static String getURL(HttpServletRequest request) {
        String contextPath = request.getContextPath().equals("/") ? "" :
                request.getContextPath();
        String url = "http://" + request.getServerName();
        if (null2Int(Integer.valueOf(request.getServerPort())) != 80)
            url = url + ":" + null2Int(Integer.valueOf(request.getServerPort())) + contextPath;
        else {
            url = url + contextPath;
        }
        return url;
    }

    public static String generic_domain(HttpServletRequest request)
    {
        String system_domain = "localhost";
        String serverName = request.getServerName();
        if (isIp(serverName))
            system_domain = serverName;
        else {
            system_domain = serverName.substring(serverName.indexOf(".") + 1);
        }

        return system_domain;
    }

    public static boolean isIp(String IP)
    {
        boolean b = false;
        IP = trimSpaces(IP);
        if (IP.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}")) {
            String[] s = IP.split("\\.");
            if ((Integer.parseInt(s[0]) < 255) &&
                    (Integer.parseInt(s[1]) < 255) &&
                    (Integer.parseInt(s[2]) < 255) &&
                    (Integer.parseInt(s[3]) < 255))
                b = true;
        }
        return b;
    }

    public static String trimSpaces(String IP)
    {
        while (IP.startsWith(" ")) {
            IP = IP.substring(1, IP.length()).trim();
        }
        while (IP.endsWith(" ")) {
            IP = IP.substring(0, IP.length() - 1).trim();
        }
        return IP;
    }

    public static boolean  isMobileDevice(String requestHeader){
        /**
         * android : 所有android设备
         * mac os : iphone ipad
         * windows phone:Nokia等windows系统的手机
         */
        String[] deviceArray = new String[]{"android","mac os","windows phone"};
        if(requestHeader == null)
            return false;
        requestHeader = requestHeader.toLowerCase();
        for(int i=0;i<deviceArray.length;i++){
            if(requestHeader.indexOf(deviceArray[i])>0){
                return true;
            }
        }
        return false;
    }

}
