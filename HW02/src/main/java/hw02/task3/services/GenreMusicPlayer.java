package hw02.task3.services;

import hw02.task3.entities.Song;

/**
 * @author YevhenKovalevskyi
 */
public interface GenreMusicPlayer {
    
    void addSong(Song song);
    String playMusic();
}
