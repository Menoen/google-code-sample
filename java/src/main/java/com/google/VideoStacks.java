package com.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.TreeMap;

public class VideoStacks {

    static Stack<Video> videoList = new Stack<>();

    static Stack<Video> pauseList = new Stack<>();

    static TreeMap<String, VideoPlaylist> playList = new TreeMap<>();
}
