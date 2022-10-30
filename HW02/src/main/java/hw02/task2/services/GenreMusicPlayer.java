package hw02.task2.services;

import hw02.task2.entities.Song;

/**
 * @author YevhenKovalevskyi
 */
public interface GenreMusicPlayer {
    
    void addSong(Song song);
    String playMusic();
}
