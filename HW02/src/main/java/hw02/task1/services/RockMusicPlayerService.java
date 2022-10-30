package hw02.task1.services;

import hw02.task1.entities.Song;
import hw02.task1.messages.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Bean RockMusicPlayerService
 *
 * @author YevhenKovalevskyi
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RockMusicPlayerService implements Serializable, GenreMusicPlayer {
    
    @Serial
    private static final long serialVersionUID = -3760445487636086035L;
    
    private List<Song> playlist;
    
    public void addSong(Song song) {
        playlist.add(song);
    }
    
    public String playMusic() {
        Song song = playlist.get(new Random().nextInt(playlist.size()));
        
        String name = song.getName();
        String author = song.getAuthor();
        
        log.info(Messages.NOW_PLAYING.getLogMessage(), author, name);
    
        return String.format(Messages.NOW_PLAYING.getOutMessage(), author, name);
    }
}
