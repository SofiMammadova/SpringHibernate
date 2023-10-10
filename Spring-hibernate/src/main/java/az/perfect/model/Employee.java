package az.perfect.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //zdes est vsyo:get, set i t.d
@AllArgsConstructor //dlya polnogo constructora v getEmployeeById
@Entity //v jdbc etoqo net, no v hiernate on est'
@NoArgsConstructor
/*esli bi tabel u mena nizavlsa employee, a class emp, to:
@Table(name="employee")
//no t.k. u nas odinakovo, nichego ne troqaem
*/

public class Employee {
    //Bir classin entity’si olmasi uchun, primary key’I olmalidir
    @Id //T.E id

    //i pokajem autoincrement dla ID:
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /*napr,v table nazvanie stolbca imya, a zdes peremennaya name, to
    @Column(name = "imya", length=25) //length - eto kak varchar(25)
    //no u nas vsyo sovpadaet, poetomu ne menaem
    */
    private String name;
    private String surname;
    private int age;
    private double salary;
}
