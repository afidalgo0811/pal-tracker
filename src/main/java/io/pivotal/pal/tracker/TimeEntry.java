package io.pivotal.pal.tracker;

import java.time.LocalDate;
import java.util.Objects;

public class TimeEntry {

    private long userId;
    private LocalDate date;
    private int hours;
    private long id;
    private long projectId;

    public long getUserId() {
        return userId;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public long getProjectId() {
        return projectId;
    }

    public TimeEntry(long projectId, long userId, LocalDate date, int hours) {
        this.id = -1;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
        this.projectId = projectId;
    }

    public TimeEntry(long timeEntryId, long projectId, long userId, LocalDate date, int hours) {
        this.id = timeEntryId;
        this.userId = userId;
        this.date = date;
        this.hours = hours;
        this.projectId = projectId;
    }

    public TimeEntry() {
    }

    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeEntry timeEntry = (TimeEntry) o;
        return userId == timeEntry.userId &&
                hours == timeEntry.hours &&
                id == timeEntry.id &&
                projectId == timeEntry.projectId &&
                Objects.equals(date, timeEntry.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, date, hours, id, projectId);
    }

    @Override
    public String toString() {
        return "TimeEntry{" +
                "userId=" + userId +
                ", date=" + date +
                ", hours=" + hours +
                ", id=" + id +
                ", projectId=" + projectId +
                '}';
    }
}
