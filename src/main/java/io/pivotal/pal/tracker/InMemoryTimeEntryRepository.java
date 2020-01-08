package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long,TimeEntry> timeEntries = new HashMap<Long, TimeEntry>();
    private Long counter = 0L;

    public TimeEntry create(TimeEntry timeEntry) {
        counter++;
        TimeEntry newTimeEntry = new TimeEntry(
                counter,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());
        timeEntries.put(newTimeEntry.getId(), newTimeEntry);
        return newTimeEntry;
    }

    public TimeEntry find(Long id) {
       return timeEntries.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntries.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry timeEntry1 = timeEntries.get(id);
        if (timeEntry1 == null) {
            return null;
        }
        TimeEntry newTimeEntry = new TimeEntry(
                timeEntry1.getId(),
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours());
        timeEntries.put(timeEntry1.getId(), newTimeEntry);
        return newTimeEntry;
    }

    public void delete(Long id) {
        timeEntries.remove(id);
    }
}
