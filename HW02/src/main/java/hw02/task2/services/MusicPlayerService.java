package hw02.task2.services;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

/**
 * Bean MusicPlayerService
 *
 * @author YevhenKovalevskyi
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Component("musicPlayer")
public class MusicPlayerService implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -3760445487636086039L;

    @Autowired
    private RockMusicPlayerService rockMusicPlayer;
    @Autowired
    private ClassicMusicPlayerService classicMusicPlayer;
    
    public void playClassicMusic() {
        System.out.println(classicMusicPlayer.playMusic());
    }
    
    public void playRockMusic() {
        System.out.println(rockMusicPlayer.playMusic());
    }
}
