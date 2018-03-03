package Hibernate;

import java.util.Objects;

public class House {
    private Integer id;
    private String address;
    private Integer ownerId;
    private int cost;

    public House(final String address, final Integer ownerId, final int cost) {
        this.address = address;
        this.ownerId = ownerId;
        this.cost = cost;
    }

    House() {}

    public Integer getId() {
        return id;
    }

    @SuppressWarnings("unused")
    private void setId(final Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(final int ownerId) {
        this.ownerId = ownerId;
    }
    
    public int getCost() {
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
        return Objects.equals(id, thatHouse.getId())
                && Objects.equals(address, thatHouse.getAddress())
                && Objects.equals(ownerId, thatHouse.getOwnerId())
                && Objects.equals(cost, thatHouse.getCost());
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
