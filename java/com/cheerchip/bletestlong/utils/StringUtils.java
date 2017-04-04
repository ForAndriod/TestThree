package com.cheerchip.bletestlong.utils;

import com.cheerchip.bletestlong.App;

import java.text.DecimalFormat;

/**
 * Created by YangJingLin on 2017/3/8.
 */
public class StringUtils {
    static final String HEXUP = "0123456789ABCDEF";

    /**
     *	低位在前
     */
    public static byte[] int2bytes(int value){
        byte[] src = new byte[4];
        src[0] = (byte) (value & 0xFF);
        src[1] = (byte) ((value >> 8) & 0xFF);
        src[2] = (byte) ((value >> 16) & 0xFF);
        src[3] = (byte) ((value >> 24) & 0xFF);
        return src;
    }

    /**
     *	低位在前
     */
    public static int bytes2Int(byte[] src){
        if(src.length != 4){
            return -1;
        }
        int value;
        value = (src[0] & 0xFF)
                |(src[1] & 0xFF) << 8
                |(src[2] & 0xFF) << 16
                |(src[3] & 0xFF) << 24;
        return value;
    }

    public static String byte2Hex(byte[] data){
        if(data == null || data.length == 0){
            return "数据为空----";
        }
        final StringBuilder hex = new StringBuilder(data.length*2);

        int i = 0;
        for(byte b : data){
            if(i!=0 && i%16 == 0){
                hex.append("\n");
            }else{
                if(i != 0){
                    hex.append("-");
                }
            }
            hex.append(HEXUP.charAt(b>>4&0x0F)).append(HEXUP.charAt( b & 0x0F));
            i++;
        }
        return hex.toString();
    }

    public static String byteArray2Hex(byte[] data){
        if(data == null || data.length == 0){
            return "数据为空";
        }
        final StringBuilder hex = new StringBuilder(data.length*2);

        int i = 0;
        for(byte b : data){
            if(i!=0 && i%16 == 0){
                hex.append("\n");
            }else{
                if(i != 0){
                    hex.append("_");
                }
            }
            hex.append(HEXUP.charAt(b>>4&0x0F)).append(HEXUP.charAt( b & 0x0F));
            i++;
        }
        hex.append("    长度是: ");
        hex.append(data.length);
        return hex.toString();
    }

    public static String getPackageData(byte[] raw){
        if (raw == null) {
            return "数据为空!";
        }

        // cheerchip.java.lib: 0000 - 0015: 50 4F 53 54 20 2F 48 4D 68 74 74 70 2E 6A 73 70
        // POST /HMhttp.jsp
        // count: | -13- | |3| 1 13 + 3 + 1 = 17
        final StringBuilder hex = new StringBuilder(4 * raw.length + // hex
                // chars
                // and
                // ASCII
                // chars
                ((raw.length + 15) / 16) * 17 + // sequence no,blanks,return
                // char
                (16 - (raw.length % 16)) * 3); // supplements in last line
        int i = 0;
        int s = 0;
        int j = 0;
        DecimalFormat nf = new DecimalFormat("0000");
        byte[] ascii = new byte[16];

        for (final byte b : raw) {
            if (i % 16 == 0) {
                hex.append(nf.format(i));
                hex.append(" - ");
                hex.append(nf.format(i + 15));
                hex.append(": ");
                s = 0;
            }
            hex.append(HEXUP.charAt((b & 0xF0) >> 4))
                    .append(HEXUP.charAt((b & 0x0F))).append(' ');
            ascii[s++] = b;

            if (++i % 16 == 0) {
                hex.append("   ");
                for (j = 0; j < 16; j++) {
                    if (ascii[j] >= 0x20 && ascii[j] <= 0x7E) {
                        hex.append((char) (ascii[j] & 0xFF));
                    } else {
                        hex.append('.');
                    }
                }
                hex.append('\n');
            }
        }

        // last line
        for (i = 0; i <= 16 - s; i++) {
            hex.append("   ");
        }
        for (j = 0; j < s; j++) {
            if (ascii[j] >= 0x20 && ascii[j] <= 0x7E) {
                hex.append((char) (ascii[j] & 0xFF));
            } else {
                hex.append('.');
            }
        }
        return hex.toString();
    }

    public static String getHex(byte[] raw) {
        if (raw == null) {
            return null;
        }

        // cheerchip.java.lib: 0000 - 0015: 50 4F 53 54 20 2F 48 4D 68 74 74 70 2E 6A 73 70
        // POST /HMhttp.jsp
        // count: | -13- | |3| 1 13 + 3 + 1 = 17
        final StringBuilder hex = new StringBuilder(4 * raw.length + // hex
                // chars
                // and
                // ASCII
                // chars
                ((raw.length + 15) / 16) * 17 + // sequence no,blanks,return
                // char
                (16 - (raw.length % 16)) * 3); // supplements in last line
        int i = 0;
        int s = 0;
        int j = 0;
        DecimalFormat nf = new DecimalFormat("0000");
        byte[] ascii = new byte[16];

        for (final byte b : raw) {
            if (i % 16 == 0) {
                hex.append(nf.format(i));
                hex.append(" - ");
                hex.append(nf.format(i + 15));
                hex.append(": ");
                s = 0;
            }
            hex.append(HEXUP.charAt((b & 0xF0) >> 4))
                    .append(HEXUP.charAt((b & 0x0F))).append(' ');
            ascii[s++] = b;

            if (++i % 16 == 0) {
                hex.append("   ");
                for (j = 0; j < 16; j++) {
                    if (ascii[j] >= 0x20 && ascii[j] <= 0x7E) {
                        hex.append((char) (ascii[j] & 0xFF));
                    } else {
                        hex.append('.');
                    }
                }
                hex.append('\n');
            }
        }

        // last line
        for (i = 0; i <= 16 - s; i++) {
            hex.append("   ");
        }
        for (j = 0; j < s; j++) {
            if (ascii[j] >= 0x20 && ascii[j] <= 0x7E) {
                hex.append((char) (ascii[j] & 0xFF));
            } else {
                hex.append('.');
            }
        }
        return hex.toString();
    }

    public static String getString(int stringId) {
        return App.getInstance().getString(stringId);
    }
}
