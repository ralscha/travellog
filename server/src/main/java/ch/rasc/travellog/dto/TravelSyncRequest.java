package ch.rasc.travellog.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TravelSyncRequest {
  private final List<TravelSync> inserted;

  private final List<TravelSync> updated;

  private final Set<Long> removed;

  private final Set<Long> gets;

  @JsonCreator
  public TravelSyncRequest(@JsonProperty("inserted") List<TravelSync> inserted,
      @JsonProperty("updated") List<TravelSync> updated,
      @JsonProperty("removed") Set<Long> removed, @JsonProperty("gets") Set<Long> gets) {
    this.inserted = inserted != null ? List.copyOf(inserted) : null;
    this.updated = updated != null ? List.copyOf(updated) : null;
    this.removed = removed != null ? Set.copyOf(removed) : null;
    this.gets = gets != null ? Set.copyOf(gets) : null;
  }

  public List<TravelSync> getInserted() {
    return this.inserted;
  }

  public List<TravelSync> getUpdated() {
    return this.updated;
  }

  public Set<Long> getRemoved() {
    return this.removed;
  }

  public Set<Long> getGets() {
    return this.gets;
  }

}
