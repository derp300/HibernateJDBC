package Hibernate;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Integer id;
    
    @Column(name = "address")
    private String address;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(name = "cost")
    private int cost;

    public House(final String address, final Integer ownerId, final int cost) {
        this.address = address;
        this.ownerId = ownerId;
        this.cost = cost;
    }

    House() {}

    public Integer id() {
        return id;
    }

    public String address() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public Integer ownerId() {
        return ownerId;
    }

    public void setOwner(final int ownerId) {
        this.ownerId = ownerId;
    }
    
    public int cost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(final Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;

        final House thatHouse = (House) that;
        return Objects.equals(id, thatHouse.id())
        		&& Objects.equals(address, thatHouse.address())
        		&& Objects.equals(ownerId, thatHouse.ownerId())
        		&& Objects.equals(cost, thatHouse.cost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address);
    }

    @Override
    public String toString() {
        return String.format("%s{id=%d, address='%s', ownerId='%s', cost='%d'}",
                getClass().getSimpleName(), id, address, ownerId, cost);
    }
}
