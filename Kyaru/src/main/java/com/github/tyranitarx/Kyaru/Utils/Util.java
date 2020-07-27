package com.github.tyranitarx.Kyaru.Utils;

/**
 * @author tyranitar
 * @email tyranitarx@163.com
 * @date 2020-07-23 18:59
 */
public class Util {


    public static final String RE_HTML_MARK = "(<[^<]*?>)|(<[\\s]*?/[^<]*?>)|(<[^<]*?/[\\s]*?>)";


    /**
     * 清除所有HTML标签，但是不删除标签内的内容
     *
     * @param content 文本
     * @return 清除标签后的文本
     */
    public static String cleanHtmlTag(String content) {
        return content.replaceAll(RE_HTML_MARK, "");
    }
}
