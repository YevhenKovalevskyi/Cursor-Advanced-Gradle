package hw02.task3.services;

import hw02.task3.messages.Messages;
import hw02.task3.entities.Song;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Random;

/**
 * Bean ClassicMusicPlayerService
 *
 * @author YevhenKovalevskyi
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClassicMusicPlayerService implements Serializable, GenreMusicPlayer {
    
    @Serial
    private static final long serialVersionUID = -3760445487636086040L;

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
