package com.coinsucks.core.binance;

import com.binance.connector.client.impl.SpotClientImpl;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BinanceConnectorTest {
    @Test
    public void getSpotWalletBalance() {
        SpotClientImpl client = new SpotClientImpl(BinanceCreds.publicKey, BinanceCreds.privateKey);
        LinkedHashMap<String, Object> params = new LinkedHashMap<>();
        params.put("startTime", LocalDateTime.now().minus(24, ChronoUnit.MONTHS).toInstant(ZoneOffset.UTC).toEpochMilli());
        params.put("endTime", System.currentTimeMillis());
        client.createConvert().tradeFlow(params);
    }
}
