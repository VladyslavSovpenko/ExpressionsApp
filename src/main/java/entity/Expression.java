package entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

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

    public Expression(String expression, String key) {
        this.expression = expression;
        this.key = key;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expression_id")
    private int id;

    @Column(name = "expression")
    private String expression;

    @Column(name = "key")
    private String key;

    @Override
    public String toString() {
        return "Expression{" +
                "expression='" + expression + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
