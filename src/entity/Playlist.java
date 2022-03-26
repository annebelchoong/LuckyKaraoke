/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author annebelchoong
 */
public class Playlist {
    private int playlistID;
    private String playlistName;

    public Playlist() {
    }

    public Playlist(int playlistID, String playlistName) {
        this.playlistID = playlistID;
        this.playlistName = playlistName;
    }

    public int getPlaylistID() {
        return playlistID;
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = playlistID;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    @Override
    public String toString() {
        return "Playlist{" + "playlistID=" + playlistID + ", playlistName=" + playlistName + '}';
    }
    
    
}