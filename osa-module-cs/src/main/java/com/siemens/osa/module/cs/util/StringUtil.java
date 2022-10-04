package com.siemens.osa.module.cs.util;

import java.util.Arrays;
import java.util.List;

public final class StringUtil {

    private StringUtil() {
    }

    /**
     * string filter.
     *
     * @param string string
     * @return {@link List}&lt;{@link String}&gt;
     */
    public static List<String> stringFliter(String string) {
        String[] split = string.split("\n");
        List<String> strings = null;
        for (String str : split) {
            if (str.contains("权限") || str.contains("Access")) {
                String s1 = str.replaceAll("\\D", " ");
                String s2 = s1.trim().replaceAll("\\s{1,}", " ");
                String[] s3 = s2.split(" ");
                strings = Arrays.asList(s3);
            }
        }
        return strings;
    }

}
