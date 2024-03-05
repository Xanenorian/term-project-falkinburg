package datamodel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @since J2SE-1.8
 CREATE TABLE employee (
  id INT NOT NULL AUTO_INCREMENT,    
  name VARCHAR(30) NOT NULL,   
  age INT NOT NULL,    
  PRIMARY KEY (id));
 */
@Entity
@Table(name = "employee")
public class EmployeeFalkinburg {

   @Id  // primary key
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id") // specify the column name. Without it, it will use method name
   private Integer id;

   @Column(name = "name")
   private String name;

   @Column(name = "age")
   private Integer age;
   
   @Column(name = "phone")
   private String phone;

   public EmployeeFalkinburg() {
   }

   public EmployeeFalkinburg(Integer id, String name, Integer age, String phone) {
      this.id = id;
      this.name = name;
      this.age = age;
      this.phone = phone;
   }

   public EmployeeFalkinburg(String name, int age, String phone) {
      this.name = name;
      this.age = age;
      this.phone = phone;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getAge() {
      return age;
   }

   public void setAge(Integer age) {
      this.age = age;
   }
   
   public String getPhone() {
	      return phone;
	   }

	   public void setPhone(String phone) {
	      this.phone = phone;
	   }

   @Override
   public String toString() {
      return "Employee: " + this.id + ", " + this.name + ", " + this.age + ", " + this.phone;
   }
}