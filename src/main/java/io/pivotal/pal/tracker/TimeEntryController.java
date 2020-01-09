package io.pivotal.pal.tracker;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.DistributionSummary;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository repository;
    private final DistributionSummary timeEntrySummary;
    private final Counter actionCounter;

    public TimeEntryController(TimeEntryRepository timeEntryRepository,
                               MeterRegistry meterRegistry) {
        this.repository = timeEntryRepository;

        timeEntrySummary = meterRegistry.summary("timeEntry.summary");
        actionCounter = meterRegistry.counter("timeEntry.actionCounter");
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = repository.create(timeEntryToCreate);

        actionCounter.increment();
        timeEntrySummary.record(repository.list().size());

        return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.CREATED);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId) {
        TimeEntry foundTimeEntry = repository.find(timeEntryId);
        if (foundTimeEntry == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        actionCounter.increment();
        return new ResponseEntity<TimeEntry>(foundTimeEntry, HttpStatus.OK);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        actionCounter.increment();
        return new ResponseEntity<List<TimeEntry>>(repository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity update(@PathVariable Long timeEntryId, @RequestBody TimeEntry expected) {
        TimeEntry updatedTimeEntry = repository.update(timeEntryId, expected);
        if (updatedTimeEntry == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        actionCounter.increment();
        return new ResponseEntity<TimeEntry>(updatedTimeEntry, HttpStatus.OK);
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity delete(@PathVariable Long timeEntryId) {

        repository.delete(timeEntryId);

        actionCounter.increment();
        timeEntrySummary.record(repository.list().size());

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
