package com.google;

import org.apache.maven.shared.utils.StringUtils;

import java.util.*;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    System.out.println("Here's a list of all available videos:");
    for (Video v : this.videoLibrary.getVideos()) {
      System.out.println(printVideo(v));
    }
  }

  public void playVideo(String videoId) {
    if (!validVideo(videoId)) {
      System.out.println("Cannot play video: Video does not exist");
      return;
    }
    if(!VideoStacks.videoList.empty()) {
      stopVideo();
    }
    VideoStacks.videoList.push(videoLibrary.getVideo(videoId));
    System.out.println("Playing video: "+VideoStacks.videoList.peek().getTitle());
    if (!VideoStacks.pauseList.empty()) {
      VideoStacks.pauseList.pop();
    }
  }


  public void stopVideo() {
    if(!VideoStacks.videoList.empty()) {
      if (!VideoStacks.pauseList.empty()) {
        VideoStacks.pauseList.pop();
      }
      System.out.println("Stopping video: "+VideoStacks.videoList.pop().getTitle());
    } else {
      System.out.println("Cannot stop video: No video is currently playing");
    }
  }

  public void playRandomVideo() {
    int index = (int)(Math.random()*videoLibrary.getVideos().size());
    Video playingVideo = videoLibrary.getVideos().get(index);
    playVideo(playingVideo.getVideoId());
  }

  public void pauseVideo() {
    if(!VideoStacks.videoList.empty()) {
      if(VideoStacks.pauseList.empty()) {
        VideoStacks.pauseList.push(VideoStacks.videoList.peek());
        System.out.println("Pausing video: " + VideoStacks.pauseList.peek().getTitle());
      } else {
        System.out.println("Video already paused: " + VideoStacks.pauseList.peek().getTitle());
      }
    } else {
      System.out.println("Cannot pause video: No video is currently playing");
    }
  }

  public void continueVideo() {
    if(!VideoStacks.videoList.empty()) {
      if(VideoStacks.pauseList.empty()) {
        System.out.println("Cannot continue video: Video is not paused");
      } else {
        System.out.println("Continuing video: " + VideoStacks.pauseList.pop().getTitle());
      }
    } else {
      System.out.println("Cannot continue video: No video is currently playing");
    }
  }

  public void showPlaying() {
    String state = !VideoStacks.pauseList.empty()?"PAUSED":VideoStacks.videoList.empty()?"STOP":"PLAY";
    switch (state){
      case "PLAY":
        System.out.println("Currently playing: "+ printVideo(VideoStacks.videoList.peek()));
        break;
      case "PAUSED":
        System.out.println("Currently playing: "+ printVideo(VideoStacks.videoList.peek())+" - PAUSED");
        break;
      case "STOP":
        System.out.println("No video is currently playing");
    }
  }

  public void createPlaylist(String playlistName) {
    if (!VideoStacks.playList.containsKey(playlistName)) {
      VideoPlaylist playlist = new VideoPlaylist(playlistName);
      VideoStacks.playList.put(playlistName,playlist);
    } else {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
    }
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    if (!VideoStacks.playList.containsKey(playlistName)) {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
      return;
    }
    if (!validVideo(videoId)) {
      System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
      return;
    }
    if (VideoStacks.playList.get(playlistName).getIds().contains(videoId)) {
      System.out.println("Cannot add video to " + playlistName + ": Video already added");
    } else {
      VideoStacks.playList.get(playlistName).setIds(videoId);
      System.out.println("Added video to " + playlistName + ": " + videoLibrary.getVideo(videoId).getTitle());
    }
  }

  public void showAllPlaylists() {
    if (VideoStacks.playList.isEmpty()) {
      System.out.println("No playlists exist yet");
    } else {
      System.out.println("Showing all playlists:");
      // TODO the order
      for (Map.Entry<String, VideoPlaylist> entry : VideoStacks.playList.entrySet()) {
        System.out.println(entry.getKey());
      }
    }
  }

  public void showPlaylist(String playlistName) {
    VideoPlaylist list = VideoStacks.playList.get(playlistName);
    if (list == null) {
      System.out.println("Cannot show playlist " +playlistName+ ": Playlist does not exist");
      return;
    }
    List<String> l = list.getIds();
    for (String str : l) {
      System.out.println(videoLibrary.getVideo(str).getTitle());
    }
    System.out.println("showPlaylist needs implementation");
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    System.out.println("removeFromPlaylist needs implementation");
  }

  public void clearPlaylist(String playlistName) {
    System.out.println("clearPlaylist needs implementation");
  }

  public void deletePlaylist(String playlistName) {
    System.out.println("deletePlaylist needs implementation");
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }

  /**
   * print video information by video
   * @param v
   * @return
   */
  private String printVideo(Video v) {
    String tags = StringUtils.join(v.getTags().toArray()," ");
    String s = v.getTitle()+" ("+ v.getVideoId() +") [" + tags + "]";
    return s;
  }
  /**
   * check if this video exists in file or not
   * @param videoId
   * @return
   */
  private boolean validVideo(String videoId) {
    if (videoLibrary.getVideo(videoId)!=null) {
      return true;
    } else {
      return false;
    }
  }
}