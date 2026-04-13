package kg.attractor.movie_review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDto {
    private long id;
    private int releaseYear;
    private String title;
    private String description;
    private String director;
    private String imageName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MovieDto{");
        sb.append("releaseYear=").append(releaseYear);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", director='").append(director).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
