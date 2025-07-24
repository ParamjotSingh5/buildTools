package com.example.library;


import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;

public class MyUtil {
    // Part of public API: uses Guava
    public ImmutableList<String> getNames() {
        return ImmutableList.of("Alice", "Bob");
    }

    // Internal method: uses Apache Commons
    public String formatName(String name) {
        return StringUtils.upperCase(name);
    }

}
