package com.example.transation.util;

import java.util.Calendar;

/**
 * @Author ：yaxuSong
 * @Description:
 * @Date: 11:53 2018/11/14
 * @Modified by:
 */
public class HtmlUtils {

    public static String genHtml(String password) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html style=\"background:#f7f7f7\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>zizhu-imu</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"background:#f7f7f7;overflow:hidden\">\n" +
                "<div>\n" +
                "</div>\n" +
                "<div style=\"background:#fff;border:1px solid #ccc;margin:2%;padding:0 30px\">\n" +
                "<div style=\"line-height:40px;height:40px\">&nbsp;</div>\n" +
                "<p style=\"margin:0;padding:0;font-size:14px;line-height:30px;color:#333;font-family:arial,sans-serif;font-weight:bold\">亲爱的用户：</p>\n" +
                "<div style=\"line-height:20px;height:20px\">&nbsp;</div>\n" +
                "<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">您好！您的登录密码已经重置，重置后的密码为：</p>\n" +
                "<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\"><b style=\"font-size:18px;color:#f90\">" +
                password +
                "</b><span style=\"margin:0;padding:0;margin-left:10px;line-height:30px;font-size:14px;color:#979797;font-family:'宋体',arial,sans-serif\">(为了保障您帐号的安全性，请尽快使用新密码登录进行密码修改)</span></p>\n" +
                "<div style=\"line-height:80px;height:80px\">&nbsp;</div>\n" +
                "<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">内蒙古大学资助系统</p>\n" +
                "<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">" +
                TimeUtil.getFormatTimeStr(Calendar.getInstance().getTime(), TimeUtil.DEFAULT_FORMAT) +
                "</p>\n" +
                "<div style=\"line-height:20px;height:20px\">&nbsp;</div>\n" +
                "<div style=\"border-top:1px dashed #dfdfdf;padding:30px 0;overflow:hidden\">\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
        String html = sb.toString();
        return html;
    }

    public static String genCodeHtml(String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html style=\"background:#f7f7f7\">\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "<title>zizhu-imu</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"background:#f7f7f7;overflow:hidden\">\n" +
                "<div>\n" +
                "</div>\n" +
                "<div style=\"background:#fff;border:1px solid #ccc;margin:2%;padding:0 30px\">\n" +
                "<div style=\"line-height:40px;height:40px\">&nbsp;</div>\n" +
                "<p style=\"margin:0;padding:0;font-size:14px;line-height:30px;color:#333;font-family:arial,sans-serif;font-weight:bold\">亲爱的用户：</p>\n" +
                "<div style=\"line-height:20px;height:20px\">&nbsp;</div>\n" +
                "<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">您好！您此次绑定邮箱请求验证码为：</p>\n" +
                "<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\"><b style=\"font-size:18px;color:#f90\">" +
                code +
                "</b><span style=\"margin:0;padding:0;margin-left:10px;line-height:30px;font-size:14px;color:#979797;font-family:'宋体',arial,sans-serif\">(验证码有效期为10分钟，请尽快进行验证。)</span></p>\n" +
                "<div style=\"line-height:80px;height:80px\">&nbsp;</div>\n" +
                "<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">内蒙古大学资助系统</p>\n" +
                "<p style=\"margin:0;padding:0;line-height:30px;font-size:14px;color:#333;font-family:'宋体',arial,sans-serif\">" +
                TimeUtil.getFormatTimeStr(Calendar.getInstance().getTime(), TimeUtil.DEFAULT_FORMAT) +
                "</p>\n" +
                "<div style=\"line-height:20px;height:20px\">&nbsp;</div>\n" +
                "<div style=\"border-top:1px dashed #dfdfdf;padding:30px 0;overflow:hidden\">\n" +
                "</div>\n" +
                "</div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
        String html = sb.toString();
        return html;
    }
}
