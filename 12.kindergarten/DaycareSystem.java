package org.example;

import java.sql.*;

public class DaycareSystem {
    private Database db;

    public DaycareSystem(Database db) {
        this.db = db;
    }

    public void addGroup(Group group) throws SQLException {
        String sql = "INSERT INTO group_info (name, number) VALUES (?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, group.getName());
            stmt.setInt(2, group.getNumber());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    group.setId(keys.getInt(1));
                }
            }
        }
    }

    public void editGroup(Group group) throws SQLException {
        String sql = "UPDATE group_info SET name = ?, number = ? WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, group.getName());
            stmt.setInt(2, group.getNumber());
            stmt.setInt(3, group.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteGroup(int groupId) throws SQLException {
        String sql = "DELETE FROM group_info WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, groupId);
            stmt.executeUpdate();
        }
    }

    public void addChild(Child child) throws SQLException {
        String sql = "INSERT INTO child (full_name, gender, age, group_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, child.getFullName());
            stmt.setString(2, child.getGender());
            stmt.setInt(3, child.getAge());
            stmt.setInt(4, child.getGroupId());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    child.setId(keys.getInt(1));
                }
            }
        }
    }

    public void editChild(Child child) throws SQLException {
        String sql = "UPDATE child SET full_name = ?, gender = ?, age = ?, group_id = ? WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setString(1, child.getFullName());
            stmt.setString(2, child.getGender());
            stmt.setInt(3, child.getAge());
            stmt.setInt(4, child.getGroupId());
            stmt.setInt(5, child.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteChild(int childId) throws SQLException {
        String sql = "DELETE FROM child WHERE id = ?";
        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, childId);
            stmt.executeUpdate();
        }
    }

    public void getGroupsWithChildren() throws SQLException {
        String sql = "SELECT g.id AS group_id, g.name AS group_name, g.number AS group_number, " +
                "c.id AS child_id, c.full_name AS child_name, c.gender, c.age " +
                "FROM group_info g LEFT JOIN child c ON g.id = c.group_id";

        try (Statement stmt = db.getConnection().createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int groupId = rs.getInt("group_id");
                String groupName = rs.getString("group_name");
                int groupNumber = rs.getInt("group_number");
                int childId = rs.getInt("child_id");
                String childName = rs.getString("child_name");
                String gender = rs.getString("gender");
                int age = rs.getInt("age");

                System.out.printf("Group: %s (ID: %d, Number: %d) | Child: %s (ID: %d, Gender: %s, Age: %d)%n",
                        groupName, groupId, groupNumber,
                        (childName != null ? childName : "None"),
                        childId, gender, age);
            }
        }
    }
}
