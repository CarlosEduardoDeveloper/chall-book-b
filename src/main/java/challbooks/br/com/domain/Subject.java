package challbooks.br.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "assunto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_as")
    private Long id;

    @Column(name = "descricao", length = 20, nullable = false)
    private String description;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    private Set<Book> books = new HashSet<>();

}
