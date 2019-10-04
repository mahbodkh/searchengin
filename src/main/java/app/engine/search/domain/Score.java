package app.engine.search.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class Score implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long incident;
    private List<CategoryIndex> categoryIndices;


    public Score() {
        this.incident = 1L;
        this.categoryIndices = new ArrayList<>();
    }


    public Long getIncident() {
        return incident;
    }

    public void setIncident(Long incident) {
        this.incident = incident;
    }

    public List<CategoryIndex> getCategoryIndices() {
        return categoryIndices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return Objects.equals(incident, score.incident) &&
                Objects.equals(categoryIndices, score.categoryIndices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incident, categoryIndices);
    }

    @Override
    public String toString() {
        return new StringBuilder("Score{")
                .append("incident=").append(incident)
                .append(", categoryIndices=").append(categoryIndices)
                .append('}').toString();
    }
}
