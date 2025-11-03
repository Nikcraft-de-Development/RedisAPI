package de.einnik.redisapi.api.currency;

import de.einnik.redisapi.db.records.Currency;
import java.util.UUID;

public class Coins {

    private static final Currency currency = new Currency("coins");

    public void setCoins(UUID uuid, int amount){
        currency.set(uuid, amount);
    }

    public int get(UUID uuid){
        return currency.get(uuid);
    }

    public void add(UUID uuid, int amount){
        currency.add(uuid, amount);
    }

    public void remove(UUID uuid, int amount){
        currency.remove(uuid, amount);
    }

    public void delete(UUID uuid){
        currency.delete(uuid);
    }

    public boolean exists(UUID uuid){
        return currency.exists(uuid);
    }

    public String format(UUID uuid){
        return currency.format(uuid);
    }
}