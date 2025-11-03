package de.einnik.redisapi.api.handler;

import de.einnik.redisapi.db.enums.MessageAble;
import de.einnik.redisapi.db.enums.ProfileState;
import de.einnik.redisapi.db.types.Profile;
import de.einnik.redisapi.manager.ProfileManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class ProfileHandler {

    public static void createNewProfile(Player player, Profile profile){
        ProfileManager.add(player, profile);
    }

    public static boolean existsProfile(Player player){
        return ProfileManager.exists(player);
    }

    public static Profile getProfile(Player player){
        HashMap<UUID, Profile> raw = ProfileManager.get(player);
        return raw.get(player.getUniqueId());
    }

    public static void setStatus(Player player, String string){
        getProfile(player).setStatus(string);
    }

    public static String getStatus(Player player){
        return getProfile(player).getStatus();
    }

    public static void setProfileState(Player player, ProfileState state){
        getProfile(player).setProfileStatus(state);
    }

    public static ProfileState getProfileState(Player player){
        return getProfile(player).getProfileState();
    }

    public static boolean acceptFriendRequests(Player player){
        return getProfile(player).getFriendRequests();
    }

    public static void setAcceptFriendRequests(Player player, boolean state){
        getProfile(player).setFriendRequests(state);
    }

    public static boolean acceptFriendJump(Player player){
        return getProfile(player).getFriendJump();
    }

    public static void setAcceptFriendJump(Player player, boolean state){
        getProfile(player).setFriendJUmp(state);
    }

    public static boolean showsJoinMessage(Player player){
        return getProfile(player).getJoinMessages();
    }

    public static void setShowJoinMessages(Player player, boolean state){
        getProfile(player).setJoinMessages(state);
    }

    public static MessageAble isPartyInvitable(Player player){
        return getProfile(player).getPartyInvites();
    }

    public static void setPartyInvitable(Player player, MessageAble state){
        getProfile(player).setPartyInvites(state);
    }

    public static MessageAble isMsgAble(Player player){
        return getProfile(player).getMsgMessages();
    }

    public static void setMsgAble(Player player, MessageAble state){
        getProfile(player).setMsgMessages(state);
    }

    public static ProfileState getStatusOnline(Player player){
        return getProfile(player).getProfileState();
    }

    public static void setStatusOnline(Player player, ProfileState state){
        getProfile(player).setProfileStatus(state);
    }
}