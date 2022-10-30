package hw02.task2.services;

import hw02.task2.entities.Song;
import hw02.task2.messages.Messages;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Bean RockMusicPlayerService
 *
 * @author YevhenKovalevskyi
 */
@Slf4j
@Data
@NoArgsConstructor
@Component("rockMusicPlayer")
@PropertySource("classpath:task2/musicPlayer.properties")
public class RockMusicPlayerService implements Serializable, GenreMusicPlayer {
    
    @Serial
    private static final long serialVersionUID = -3760445487636086038L;
    
    @Autowired
    private Environment env;
    private List<Song> playlist;
    
    @PostConstruct
    public void fillInPlayList() {
        playlist = new ArrayList<>();
        
        addSong(new Song(env.getProperty("rockSong1.name"), env.getProperty("rockSong1.author")));
        addSong(new Song(env.getProperty("rockSong2.name"), env.getProperty("rockSong2.author")));
        addSong(new Song(env.getProperty("rockSong3.name"), env.getProperty("rockSong3.author")));
    }
    
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
