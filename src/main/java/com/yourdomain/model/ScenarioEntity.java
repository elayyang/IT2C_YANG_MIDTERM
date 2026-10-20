
package com.yourdomain.model;

public record ScenarioEntity(
        int id,
        String petName,
        String species,
        String vaccine,
        double fee
) {
}
