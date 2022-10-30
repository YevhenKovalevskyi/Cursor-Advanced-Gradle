package hw02.task1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * POJO Song
 *
 * @author YevhenKovalevskyi
 */
@Data
@AllArgsConstructor
public class Song {

    private String name;
    private String author;
}
