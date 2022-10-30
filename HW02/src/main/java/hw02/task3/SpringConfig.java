package hw02.task3;

import hw02.task3.entities.Song;
import hw02.task3.services.ClassicMusicPlayerService;
import hw02.task3.services.MusicPlayerService;
import hw02.task3.services.RockMusicPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.List;

/**
 * Spring Configuration
 *
 * @author YevhenKovalevskyi
 */
@Configuration
@ComponentScan("hw02.task3")
@PropertySource("classpath:task3/musicPlayer.properties")
public class SpringConfig {
    
    @Autowired
    Environment env;
    
    @Bean
    public Song classicSong1() {
        return new Song(env.getProperty("classicSong1.name"), env.getProperty("classicSong1.author"));
    }
    @Bean
    public Song classicSong2() {
        return new Song(env.getProperty("classicSong2.name"), env.getProperty("classicSong2.author"));
    }
    @Bean
    public Song classicSong3() {
        return new Song(env.getProperty("classicSong3.name"), env.getProperty("classicSong3.author"));
    }
    
    @Bean
    public Song rockSong1() {
        return new Song(env.getProperty("rockSong1.name"), env.getProperty("rockSong1.author"));
    }
    @Bean
    public Song rockSong2() {
        return new Song(env.getProperty("rockSong2.name"), env.getProperty("rockSong2.author"));
    }
    @Bean
    public Song rockSong3() {
        return new Song(env.getProperty("rockSong3.name"), env.getProperty("rockSong3.author"));
    }
    
    @Bean
    public ClassicMusicPlayerService classicalMusicPlayer() {
        List<Song> playlist = List.of(classicSong1(), classicSong2(), classicSong3());
        return new ClassicMusicPlayerService(playlist);
    }
    
    @Bean
    public RockMusicPlayerService rockMusicPlayer() {
        List<Song> playlist = List.of(rockSong1(), rockSong2(), rockSong3());
        return new RockMusicPlayerService(playlist);
    }
    
    @Bean
    public MusicPlayerService musicPlayer() {
        return new MusicPlayerService(rockMusicPlayer(), classicalMusicPlayer());
    }
}
