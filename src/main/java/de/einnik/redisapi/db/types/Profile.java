package de.einnik.redisapi.db.types;

import de.einnik.redisapi.db.enums.MessageAble;
import de.einnik.redisapi.db.enums.ProfileState;

@SuppressWarnings("unused")
public class Profile {
    String status = null;
    ProfileState state = null;
    boolean friend_requests = true;
    boolean friend_jump = true;
    boolean join_messages = true;
    MessageAble party_invites = MessageAble.ALL;
    MessageAble msg_messages = MessageAble.ALL;
    ProfileState online = ProfileState.ONLINE;

    public void setStatus(String set){
        status = set;
    }

    public String getStatus(){
        return status;
    }

    public void setState(ProfileState set){
        state = set;
    }

    public ProfileState getState(){
        return state;
    }

    public void setFriendRequests(boolean set){
        friend_requests = set;
    }

    public boolean getFriendRequests(){
        return friend_requests;
    }

    public void setFriendJUmp(boolean set){
        friend_jump = set;
    }

    public boolean getFriendJump(){
        return friend_jump;
    }

    public void setJoinMessages(boolean set){
        join_messages = set;
    }

    public boolean getJoinMessages(){
        return join_messages;
    }

    public void setPartyInvites(MessageAble set){
        party_invites = set;
    }

    public MessageAble getPartyInvites(){
        return party_invites;
    }

    public void setMsgMessages(MessageAble set){
        msg_messages = set;
    }

    public MessageAble getMsgMessages(){
        return msg_messages;
    }

    public void setProfileStatus(ProfileState set){
        online = set;
    }

    public ProfileState getProfileState(){
        return online;
    }
}