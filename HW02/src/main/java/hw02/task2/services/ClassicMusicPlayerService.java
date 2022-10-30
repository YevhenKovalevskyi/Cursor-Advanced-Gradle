package hw02.task2.services;

import hw02.task2.messages.Messages;
import hw02.task2.entities.Song;
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
 * Bean ClassicMusicPlayerService
 *
 * @author YevhenKovalevskyi
 */
@Slf4j
@Data
@NoArgsConstructor
@Component("classicMusicPlayer")
@PropertySource("classpath:task2/musicPlayer.properties")
public class ClassicMusicPlayerService implements Serializable, GenreMusicPlayer {
    
    @Serial
    private static final long serialVersionUID = -3760445487636086037L;
    
    @Autowired
    private Environment env;
    private List<Song> playlist;
    
    @PostConstruct
    public void fillInPlayList() {
        playlist = new ArrayList<>();
        
        addSong(new Song(env.getProperty("classicSong1.name"), env.getProperty("classicSong1.author")));
        addSong(new Song(env.getProperty("classicSong2.name"), env.getProperty("classicSong2.author")));
        addSong(new Song(env.getProperty("classicSong3.name"), env.getProperty("classicSong3.author")));
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
