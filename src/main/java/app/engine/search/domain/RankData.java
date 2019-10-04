package app.engine.search.domain;

import java.util.Objects;

/**
 * Created by Ebrahim with ❤️ on 30 September 2019.
 */


public class RankData {

    private String fileName;
    private Long incident = 0L;
    private Integer matchTexts = 0;
    private Integer percentage = 0;

    public RankData(String fileName) {
        this.fileName = fileName;
    }

    public void increaseIncident(Long incident) {
        this.incident = this.incident + incident;
    }

    public void increaseMatchText() {
        matchTexts++;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getIncident() {
        return incident;
    }

    public Integer getMatchTexts() {
        return matchTexts;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RankData rankData = (RankData) o;
        return Objects.equals(fileName, rankData.fileName) &&
                Objects.equals(incident, rankData.incident) &&
                Objects.equals(matchTexts, rankData.matchTexts) &&
                Objects.equals(percentage, rankData.percentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, incident, matchTexts, percentage);
    }

    @Override
    public String toString() {
        return new StringBuilder("RankData{")
                .append("fileName='").append(fileName).append('\'')
                .append(", incident=").append(incident)
                .append(", matchTexts=").append(matchTexts)
                .append(", percentage=").append(percentage)
                .append('}').toString();
    }

}
