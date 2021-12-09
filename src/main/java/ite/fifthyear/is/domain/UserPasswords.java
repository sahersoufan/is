package ite.fifthyear.is.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EnableAutoConfiguration
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPasswords {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String password;
}
