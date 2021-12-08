import Keys.Key;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "subscriptions")
public class Subscription {
    @EmbeddedId
    private Key id;
    @Column(name = "subscription_date")
    private Date susbcriptionDate;
    @OneToOne
    @JoinColumn(name = "course_id", updatable = false, insertable = false)
    private Course course;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", updatable = false, insertable = false)
    private Student student;

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", susbcriptionDate=" + susbcriptionDate +
                '}';
    }

}
