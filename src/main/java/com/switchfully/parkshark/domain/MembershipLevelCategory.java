package com.switchfully.parkshark.domain;

import lombok.Getter;

@Getter
public enum MembershipLevelCategory {
    Bronze("Bronze", 0, 0, 4),
    Silver("Silver", 10, 20, 6),
    Gold("Gold", 40, 30, 24);

    private final String name;
    private final double monthlyCost;
    private final int allocationReductionInPercent;
    private final int maxAllowedTimeInHours;

    MembershipLevelCategory(String name, double monthlyCost, int allocationReductionInPercent, int maxAllowedTimeInHours) {
        this.name = name;
        this.monthlyCost = monthlyCost;
        this.allocationReductionInPercent = allocationReductionInPercent;
        this.maxAllowedTimeInHours = maxAllowedTimeInHours;
    }

    @Override
    public String toString() {
        return "MembershipLevelCategory{" +
                "name='" + name + '\'' +
                ", monthlyCost=" + monthlyCost +
                ", allocationReductionInPercent=" + allocationReductionInPercent +
                ", maxAllowedTimeInHours=" + maxAllowedTimeInHours +
                '}';
    }
}
