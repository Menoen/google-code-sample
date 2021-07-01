package com.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/** A class used to represent a Playlist */
class VideoPlaylist {
    // the name of this playlist
    private String name;
    // the videos' id in this playlist
    private ArrayList<String> ids = new ArrayList<>();

    public VideoPlaylist(String name) {
        this.name = name;
        System.out.println("Successfully created new playlist: " + name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids.add(ids);
    }
    public void deleteIds(String ids) {
        this.ids.remove(ids);
    }
}
