package ru.usachev.LogiWebProject.entity;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Map;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @Size (min = 2, max = 20, message = "Имя не меньше 2-х и не больше 20-ти символов")
    @NotBlank
    @Pattern(regexp = "[А-Я]{1}[а-я]*", message = "Используйте для имени следующий шаблон - \"Александр\"")
    private String name;

    @Column(name = "surname")
    @Size (min = 2, max = 20, message = "Имя не меньше 2-х и не больше 20-ти символов")
    @NotBlank
    @Pattern(regexp = "[А-Я]{1}[а-я]*", message = "Используйте для имени следующий шаблон - \"Иванов\"")
    private String surname;

    @Column(name = "phone_number")
    @NotBlank
    @Pattern(regexp = "^\\+[7][-]\\d{3}-\\d{3}-\\d{2}-\\d{2}$", message = "Используйте для номера следующий шаблон - +7-953-146-23-60")
    private String phoneNumber;

    /* It's help field for driver menu. When driver finish his order it sets into his worked hours */
    @Column(name = "time_for_order_execution")
    private int timeForOrderExecution;

    @Column(name = "worked_hours")
    private int workedHours;

    @Column(name = "work_type")
    private boolean workType;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Column(name = "status")
    private String status;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "order_id")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "username")
    private User user;

    public Driver() {
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isWorkType() {
        return this.workType;
    }

    public void setWorkType(boolean workType) {
        this.workType = workType;
    }

    public int getTimeForOrderExecution() {
        return timeForOrderExecution;
    }

    public void setTimeForOrderExecution(int timeForOrderExecution) {
        this.timeForOrderExecution = timeForOrderExecution;
    }
}

