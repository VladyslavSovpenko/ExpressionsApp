package entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "expressions")
@Data
@Getter
@Setter
@RequiredArgsConstructor
public class Expression {

    public Expression(String expression) {
        this.expression = expression;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expression_id")
    private int id;

    @Column(name = "expression")
    private String expression;
}
