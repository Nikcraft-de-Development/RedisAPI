package de.einnik.redisapi.api.currency;

import de.einnik.redisapi.db.records.Currency;
import java.util.UUID;

public class Coins {

    private static final Currency currency = new Currency("coins");

    public static void setCoins(UUID uuid, int amount){
        currency.set(uuid, amount);
    }

    public static int get(UUID uuid){
        return currency.get(uuid);
    }

    public static void add(UUID uuid, int amount){
        currency.add(uuid, amount);
    }

    public static void remove(UUID uuid, int amount){
        currency.remove(uuid, amount);
    }

    public static void delete(UUID uuid){
        currency.delete(uuid);
    }

    public static boolean exists(UUID uuid){
        return currency.exists(uuid);
    }

    public static String format(UUID uuid){
        return currency.format(uuid);
    }
}