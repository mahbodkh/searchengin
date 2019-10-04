package app.engine.search.domain;

import java.util.Objects;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class CategoryIndex {

    private String categoryName;
    private Long incident = 1L;

    public CategoryIndex(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getIncident() {
        return incident;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void increase() {
        incident++;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryIndex that = (CategoryIndex) o;
        return Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(incident, that.incident);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, incident);
    }

    @Override
    public String toString() {
        return new StringBuilder("CategoryIndex{")
                .append("categoryName='").append(categoryName).append('\'')
                .append(", incident=").append(incident)
                .append('}').toString();
    }
}
