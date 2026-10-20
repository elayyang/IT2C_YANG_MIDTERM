
package com.yourdomain.dao;

import com.yourdomain.model.ScenarioEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScenarioDao {

    public void createTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS vaccinations (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    pet_name TEXT NOT NULL,
                    species TEXT NOT NULL,
                    vaccine TEXT NOT NULL,
                    fee REAL NOT NULL
                )
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);
            System.out.println("Database table is ready.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public boolean addRecord(ScenarioEntity record) {
        String sql = """
                INSERT INTO vaccinations
                (pet_name, species, vaccine, fee)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, record.petName());
            pstmt.setString(2, record.species());
            pstmt.setString(3, record.vaccine());
            pstmt.setDouble(4, record.fee());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public List<ScenarioEntity> getAllRecords() {
        List<ScenarioEntity> records = new ArrayList<>();

        String sql = """
                SELECT id, pet_name, species, vaccine, fee
                FROM vaccinations
                ORDER BY id
                """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ScenarioEntity record = new ScenarioEntity(
                        rs.getInt("id"),
                        rs.getString("pet_name"),
                        rs.getString("species"),
                        rs.getString("vaccine"),
                        rs.getDouble("fee")
                );

                records.add(record);
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }

        return records;
    }
}
