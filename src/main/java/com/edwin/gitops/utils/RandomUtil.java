package com.edwin.gitops.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.time.Instant;

public class RandomUtil {
    public static String generate() {
        return Instant.now().getEpochSecond() + "-" + RandomStringUtils.randomNumeric(8);
    }
}
