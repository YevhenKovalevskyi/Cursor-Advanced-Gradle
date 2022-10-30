package hw02.task1.services;

import hw02.task1.entities.Song;

/**
 * @author YevhenKovalevskyi
 */
public interface GenreMusicPlayer {
    
    void addSong(Song song);
    String playMusic();
}
