/*
 * Copyright (C) 2015 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.bluros.music.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.widget.Toast;

import com.bluros.music.activities.MainActivity;
import com.bluros.music.activities.PlaylistDetailActivity;
import com.bluros.music.activities.SearchActivity;
import com.bluros.music.activities.SettingsActivity;
import com.bluros.music.nowplaying.Music1;
import com.bluros.music.nowplaying.Music2;
import com.bluros.music.nowplaying.Music3;
import com.bluros.music.nowplaying.Music4;

import java.util.ArrayList;

public class NavigationUtils {

    @TargetApi(21)
    public static void navigateToAlbum(Activity context, long albumID, ArrayList<Pair> transitionViews) {
        final Intent intent = new Intent(context, MainActivity.class);
        if (!PreferencesUtility.getInstance(context).getSystemAnimations()) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        intent.setAction(Constants.NAVIGATE_ALBUM);
        intent.putExtra(Constants.ALBUM_ID, albumID);

        if (MusicUtils.isLollipop() && transitionViews != null && PreferencesUtility.getInstance(context).getAnimations()) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(context, transitionViews.get(0));
            context.startActivity(intent, options.toBundle());
        } else {
            context.startActivity(intent);
        }

    }

    @TargetApi(21)
    public static void navigateToArtist(Activity context, long artistID, ArrayList<Pair> transitionViews) {
        final Intent intent = new Intent(context, MainActivity.class);
        if (!PreferencesUtility.getInstance(context).getSystemAnimations()) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        intent.setAction(Constants.NAVIGATE_ARTIST);
        intent.putExtra(Constants.ARTIST_ID, artistID);

        if (MusicUtils.isLollipop() && transitionViews != null && PreferencesUtility.getInstance(context).getAnimations()) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(context, transitionViews.get(0));
            context.startActivity(intent, options.toBundle());
        } else {
            context.startActivity(intent);
        }
    }

    public static void navigateToNowplaying(Activity context, boolean withAnimations) {

        final Intent intent = new Intent(context, MainActivity.class);
        if (!PreferencesUtility.getInstance(context).getSystemAnimations()) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        intent.setAction(Constants.NAVIGATE_NOWPLAYING);
        intent.putExtra(Constants.WITH_ANIMATIONS, withAnimations);
        context.startActivity(intent);
    }

    public static Intent getNowPlayingIntent(Context context) {

        final Intent intent = new Intent(context, MainActivity.class);
        intent.setAction(Constants.NAVIGATE_NOWPLAYING);
        return intent;
    }

    public static void navigateToSettings(Activity context) {
        final Intent intent = new Intent(context, SettingsActivity.class);
        if (!PreferencesUtility.getInstance(context).getSystemAnimations()) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        intent.setAction(Constants.NAVIGATE_SETTINGS);
        context.startActivity(intent);
    }

    public static void navigateToSearch(Activity context) {
        final Intent intent = new Intent(context, SearchActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.setAction(Constants.NAVIGATE_SEARCH);
        context.startActivity(intent);
    }

    public static void navigateToMainActivityWithFragment(Context context, String navID) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setAction(navID);
        context.startActivity(intent);
    }

    @TargetApi(21)
    public static void navigateToPlaylistDetail(Activity context, String action, long firstAlbumID, String playlistName, int foregroundcolor, long playlistID, ArrayList<Pair> transitionViews) {
        final Intent intent = new Intent(context, PlaylistDetailActivity.class);
        if (!PreferencesUtility.getInstance(context).getSystemAnimations()) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        intent.setAction(action);
        intent.putExtra(Constants.PLAYLIST_ID, playlistID);
        intent.putExtra(Constants.PLAYLIST_FOREGROUND_COLOR, foregroundcolor);
        intent.putExtra(Constants.ALBUM_ID, firstAlbumID);
        intent.putExtra(Constants.PLAYLIST_NAME, playlistName);

        if (MusicUtils.isLollipop() && PreferencesUtility.getInstance(context).getAnimations()) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.getInstance(), transitionViews.get(0), transitionViews.get(1), transitionViews.get(2));
            context.startActivity(intent, options.toBundle());
        } else {
            context.startActivity(intent);
        }
    }

    public static void navigateToEqualizer(Activity context) {
        try {
            // The google MusicFX apps need to be started using startActivityForResult
            context.startActivityForResult(MusicUtils.createEffectsIntent(), 666);
        } catch (final ActivityNotFoundException notFound) {
            Toast.makeText(context, "Equalizer not found", Toast.LENGTH_SHORT).show();
        }
    }

    public static Intent getNavigateToStyleSelectorIntent(Activity context, String what) {
        final Intent intent = new Intent(context, SettingsActivity.class);
        if (!PreferencesUtility.getInstance(context).getSystemAnimations()) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        }
        intent.setAction(Constants.SETTINGS_STYLE_SELECTOR);
        intent.putExtra(Constants.SETTINGS_STYLE_SELECTOR_WHAT, what);
        return intent;
    }

    public static Fragment getFragmentForNowplayingID(String fragmentID) {
        switch (fragmentID) {
            case Constants.TIMBER1:
                return new Music1();
            case Constants.TIMBER2:
                return new Music2();
            case Constants.TIMBER3:
                return new Music3();
            case Constants.TIMBER4:
                return new Music4();
            default:
                return new Music1();
        }

    }

}
