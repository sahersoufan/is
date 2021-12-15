package ite.fifthyear.is.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.*;

@Entity
@EnableAutoConfiguration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String username;
    private String password;
    private String description;

    @OneToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private AttachmentFile attachmentFiles;

}
