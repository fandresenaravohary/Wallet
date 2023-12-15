package models;

public class TransactionSummary {
    private double restaurant;
    private double salary;

    public TransactionSummary(double restaurant, double salary) {
        this.restaurant = restaurant;
        this.salary = salary;
    }

    public double getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(double restaurant) {
        this.restaurant = restaurant;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "TransactionSummary{" +
                "restaurant=" + restaurant +
                ", salary=" + salary +
                '}';
    }
}
