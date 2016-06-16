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

package com.bluros.music.nowplaying;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluros.music.MusicPlayer;
import com.bluros.music.R;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;

public class Music1 extends BaseNowplayingFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.fragment_music1, container, false);

        setMusicStateListener();
        setSongDetails(rootView);

        return rootView;
    }

    @Override
    public void updateShuffleState() {
        if (shuffle != null && getActivity() != null) {
            MaterialDrawableBuilder builder = MaterialDrawableBuilder.with(getActivity())
                    .setIcon(MaterialDrawableBuilder.IconValue.SHUFFLE)
                    .setSizeDp(30);

            int color, color2;
            if (isThemeIsLight()) {
                color = Color.parseColor("#ffffff");
                color2 = Color.parseColor("#000000");
            } else {
                color = Color.parseColor("#000000");
                if (isThemeIsBlack())
                    color2 = Color.parseColor("#ffb701");
                else color2 = Color.parseColor("#ffffff");
            }
            ;

            if (MusicPlayer.getShuffleMode() == 0) {
                builder.setColor(color);
            } else builder.setColor(color2);

            shuffle.setImageDrawable(builder.build());
            shuffle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MusicPlayer.cycleShuffle();
                    updateShuffleState();
                }
            });
        }
    }
}