package Keys;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
public class Key implements Serializable {

    public Key(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Key() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return studentId == key.studentId &&
                courseId == key.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

}