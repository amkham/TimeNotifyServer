package Db.entity;

import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "notification", schema = "public", catalog = "postgres")
public class Notification {


    private int __id;
    @SerializedName("name")
    private String __name;
    @SerializedName("message")
    private String __message;
    @SerializedName("hour")
    private Integer __hour;
    @SerializedName("min")
    private Integer __min;
    @SerializedName("sec")
    private Integer __sec;

    public Notification(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return __id;
    }

    public void setId(int id) {
        __id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return __name;
    }

    public void setName(String name) {
        __name = name;
    }

    @Basic
    @Column(name = "message", nullable = true, length = 100)
    public String getMessage() {
        return __message;
    }

    public void setMessage(String message) {
        __message = message;
    }

    @Basic
    @Column(name = "hour", nullable = true)
    public Integer getHour() {
        return __hour;
    }

    public void setHour(Integer hour) {
        __hour = hour;
    }

    @Basic
    @Column(name = "min", nullable = true)
    public Integer getMin() {
        return __min;
    }

    public void setMin(Integer min) {
        __min = min;
    }

    @Basic
    @Column(name = "sec", nullable = true)
    public Integer getSec() {
        return __sec;
    }

    public void setSec(Integer sec) {
        __sec = sec;
    }

    @Override
    public boolean equals(Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        Notification _that = (Notification) _o;
        return __id == _that.__id && Objects.equals(__name, _that.__name) && Objects.equals(__message, _that.__message) && Objects.equals(__hour, _that.__hour) && Objects.equals(__min, _that.__min) && Objects.equals(__sec, _that.__sec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(__id, __name, __message, __hour, __min, __sec);
    }

}
