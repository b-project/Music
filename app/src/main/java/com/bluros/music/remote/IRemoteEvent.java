package com.bluros.music.remote;

/**
 * Created by Christoph on 28.03.2016.
 */
public interface IRemoteEvent {
    public enum RemoteState {
        PLAYING, PAUSED_PLAYBACK, STOPPED;
    }

    void onStateChange(RemoteState state);
}
