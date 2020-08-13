package mantis.model;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "mantis_bug_table")
public class Issue {

  @Id
  @Column(name = "id")
  @Type(type = "int")
  private int id;

//  @Transient
  public String summary;
//  @Transient
  private String description;
//  @Transient
  private ru.stqa.pft.mantis.model.Project project;

//  @Transient
  private String resolution;

  @Column(name = "resolution")
  @Type(type = "short")
  private short resolutionId;

  public int getId() {
    return id;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  public ru.stqa.pft.mantis.model.Project getProject() {
    return project;
  }

  public String getResolution() {
    return resolution;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }


  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Issue withProject(ru.stqa.pft.mantis.model.Project project) {
    this.project = project;
    return this;
  }

  public Issue withResolution(String resolution) {
    this.resolution = resolution;
    return this;
  }

  @Override
  public String toString() {
    return "Issue{" +
        "id=" + id +
        ", resolution='" + resolution + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Issue issue = (Issue) o;
    return id == issue.id &&
        Objects.equals(resolution, issue.resolution);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resolution);
  }
}