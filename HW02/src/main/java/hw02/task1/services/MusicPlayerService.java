package hw02.task1.services;

import lombok.*;

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
public class MusicPlayerService implements Serializable {
    
    @Serial
    private static final long serialVersionUID = -3760445487636086036L;
    
    private RockMusicPlayerService rockMusicPlayer;
    private ClassicMusicPlayerService classicMusicPlayer;
    
    public void playClassicMusic() {
        System.out.println(classicMusicPlayer.playMusic());
    }
    
    public void playRockMusic() {
        System.out.println(rockMusicPlayer.playMusic());
    }
}
