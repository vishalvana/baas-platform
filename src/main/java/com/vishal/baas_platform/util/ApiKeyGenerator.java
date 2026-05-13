package com.vishal.baas_platform.util;

import java.util.UUID;

public class ApiKeyGenerator {

    public static String generateApiKey() {

        return "sk_" +
                UUID.randomUUID()
                        .toString()
                        .replace("-", "");
    }
}