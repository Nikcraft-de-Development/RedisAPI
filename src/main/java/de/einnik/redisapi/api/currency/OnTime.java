package de.einnik.redisapi.api.currency;

import de.einnik.redisapi.db.records.Currency;

import java.util.UUID;

public class OnTime {

    private static final Currency currency = new Currency("ontime");

    public static void setOnTime(UUID uuid, int amount){
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

    public static String formatCmd(UUID uuid){
        final int count = get(uuid);

        int totalSeconds = count * 60;
        int days = totalSeconds / (24 * 3600);
        totalSeconds %= (24 * 3600);

        int hours = totalSeconds / 3600;
        totalSeconds %= 3600;

        int minutes = totalSeconds / 60;

        StringBuilder sb = new StringBuilder();

        if (days > 0) {
            sb.append(days).append("Tage ");
        }
        if (hours > 0 || days > 0) {
            sb.append(hours).append("Stunden ");
        }
        if (minutes > 0 || hours > 0 || days > 0) {
            sb.append(minutes).append("Minuten ");
        }

        return sb.toString().trim();
    }

    public static String formatSB(UUID uuid){
        int count = get(uuid);

        int totalSeconds = count * 60;
        int days = totalSeconds / (24 * 3600);
        totalSeconds %= (24 * 3600);

        int hours = totalSeconds / 3600;
        totalSeconds %= 3600;

        int minutes = totalSeconds / 60;

        StringBuilder sb = new StringBuilder();

        if (days > 0) {
            sb.append(days).append("d ");
        }
        if (hours > 0 || days > 0) {
            sb.append(hours).append("h ");
        }
        if (minutes > 0 || hours > 0 || days > 0) {
            sb.append(minutes).append("m ");
        }

        return sb.toString().trim();
    }
}