package com.epam.mjc;

import java.util.*;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        StringTokenizer st;
        List<String> res = new ArrayList<>();
        StringJoiner sj = new StringJoiner("");

        for (String i: delimiters) {
            sj.add(i);
        }
        st = new StringTokenizer(source, sj.toString());

        while (st.hasMoreTokens()) {
            res.add(st.nextToken());
        }
        return res;
    }
}
