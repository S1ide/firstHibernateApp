import Keys.Key;
import Keys.PurchaseKey;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "purchaselist")
public class Purchase {
    @EmbeddedId
    private PurchaseKey key;
    private int price;
    @Column(name = "subscription_date")
    private Date subcriptionDate;
}

