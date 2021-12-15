package ite.fifthyear.is.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@Data
@EnableAutoConfiguration
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileUri;

/*    //TODO may we have a problem
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "saved_account_id")
    private SavedAccount savedAccount;*/
}
