package models;

import java.util.Arrays;

public class Currency {
    private int id;
    private String name;
    private CurrencyCode code;

    public Currency(int id, String name, CurrencyCode code) {
        this.id = id;
        this.name = name;
        this.code = code;
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

    public CurrencyCode getCode() {
        return code;
    }

    public void setCode(CurrencyCode code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                '}';
    }

    public static double calculateWeightedAverage(double[] values, double[] weights) {
        if (values.length != weights.length) {
            throw new IllegalArgumentException("Arrays of values and weights must have the same length");
        }

        double sumProducts = 0;
        double sumWeights = 0;

        for (int i = 0; i < values.length; i++) {
            sumProducts += values[i] * weights[i];
            sumWeights += weights[i];
        }

        return sumProducts / sumWeights;
    }

    public static double findMinimum(double[] values) {
        double minimum = values[0];
        for (double value : values) {
            if (value < minimum) {
                minimum = value;
            }
        }
        return minimum;
    }

    public static double findMaximum(double[] values) {
        double maximum = values[0];
        for (double value : values) {
            if (value > maximum) {
                maximum = value;
            }
        }
        return maximum;
    }

    public static double calculateMedian(double[] values) {
        Arrays.sort(values);
        int length = values.length;

        if (length % 2 == 0) {
            return (values[length / 2 - 1] + values[length / 2]) / 2.0;
        } else {
            return values[length / 2];
        }
    }

}
