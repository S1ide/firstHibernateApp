import Keys.Key;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "linkedPurchaseList")
@AllArgsConstructor
public class LinkedPurchase {
    @EmbeddedId
    private Key key;

    public LinkedPurchase() {

    }
}
