package groupingsystem;

import java.sql.*;

public class StudentDAO {

    private Conn conn;

    public StudentDAO() {
        conn = new Conn(); // Make sure this is correctly initialized
    }

    // Fetch student and group details by PRN and return as a String array
    public String[] getStudentByPRN(String prn) {
        String[] studentData = null;
        try {
            String query = "SELECT * FROM student s WHERE s.PRN = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setString(1, prn);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String branch = rs.getString("branch");
                String gender = rs.getString("gender");
                int year = rs.getInt("year");
                int groupId = rs.getInt("group_id");

                studentData = new String[] {
                    prn, name, email, branch, gender, String.valueOf(year), String.valueOf(groupId),
                };
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentData;
    }

    // Fetch group details by group_id and return as a 2D String array
    public String[][] getGroupAndStudentDetails(int groupId) {
        String[][] data = null;
        try {
            String query = "SELECT g.group_id, g.field_id, g.is_approved, g.mentor_id, s.prn, s.name " +
                           "FROM student s INNER JOIN group_details g ON s.group_id = g.group_id " +
                           "WHERE g.group_id = ?";
            PreparedStatement ps = conn.c.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, groupId);
            ResultSet rs = ps.executeQuery();

            // Count rows
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new String[rowCount][6];  // 6 columns: name, prn, group_id, field_id, is_approved, mentor_id
            int row = 0;

            while (rs.next()) {
                data[row][0] = rs.getString("name");
                data[row][1] = rs.getString("prn");
                data[row][2] = String.valueOf(rs.getInt("group_id"));
                data[row][3] = String.valueOf(rs.getInt("field_id"));
                data[row][4] = rs.getString("is_approved");
                data[row][5] = String.valueOf(rs.getInt("mentor_id"));
                row++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;

    }
    public String[][] getProjectsByGroupId(int groupId) {
        String[][] data = null;
        try {
            String query = "SELECT * FROM project WHERE group_id = ?";
            PreparedStatement ps = conn.c.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, groupId);
            ResultSet rs = ps.executeQuery();

            // Count rows
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new String[rowCount][5]; // 5 columns as per your table
            int row = 0;

            while (rs.next()) {
                data[row][0] = String.valueOf(rs.getInt("project_id"));
                data[row][1] = String.valueOf(rs.getInt("field_id"));
                data[row][2] = rs.getString("project_title");
                data[row][3] = String.valueOf(rs.getInt("level"));
                data[row][4] = String.valueOf(rs.getInt("group_id"));
                row++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public String[][] getMentorById(int mentorId) {
        String[][] data = null;
        try {
            String query = "SELECT * FROM faculty WHERE mentor_id = ?";
            PreparedStatement ps = conn.c.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, mentorId);
            ResultSet rs = ps.executeQuery();

            // Count rows
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            data = new String[rowCount][4]; // 4 columns: mentor_id, field_id, name, email
            int row = 0;

            while (rs.next()) {
                data[row][0] = String.valueOf(rs.getInt("mentor_id"));
                data[row][1] = String.valueOf(rs.getInt("field_id"));
                data[row][2] = rs.getString("name");
                data[row][3] = rs.getString("email");
                row++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public String[][] getGroupsByMentorId(int mentorId) {
        String[][] data = null;
        try {
            String query = "SELECT * FROM group_details WHERE mentor_id = ?";
            PreparedStatement ps = conn.c.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, mentorId);
            ResultSet rs = ps.executeQuery();

            // Count rows
            rs.last();
            int rowCount = rs.getRow();
            rs.beforeFirst();

            // Assuming columns: group_id, mentor_id, group_name, and other relevant fields
            int columnCount = rs.getMetaData().getColumnCount();
            data = new String[rowCount][columnCount];
            int row = 0;

            while (rs.next()) {
                for (int col = 0; col < columnCount; col++) {
                    data[row][col] = rs.getString(col + 1); // JDBC columns are 1-indexed
                }
                row++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
    public boolean updateGroupFieldId(int groupId, int newFieldId) {
        try {
            String query = "UPDATE group_details SET field_id = ? WHERE group_id = ?";
            PreparedStatement ps = conn.c.prepareStatement(query);
            ps.setInt(1, newFieldId);
            ps.setInt(2, groupId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean deleteGroupById(int groupId) {
    String updateStudentQuery = "UPDATE Student SET group_id = NULL WHERE group_id = ?";
    String deleteProjectQuery = "DELETE FROM Project WHERE group_id = ?";
    String deleteGroupQuery = "DELETE FROM group_details WHERE group_id = ?";
    
    try (PreparedStatement updateStudentPs = conn.c.prepareStatement(updateStudentQuery);
         PreparedStatement deleteProjectPs = conn.c.prepareStatement(deleteProjectQuery);
         PreparedStatement deleteGroupPs = conn.c.prepareStatement(deleteGroupQuery)) {
        
        // Start a transaction
        conn.c.setAutoCommit(false);
        
        // Step 1: Set group_id to NULL in Student table
        updateStudentPs.setInt(1, groupId);
        updateStudentPs.executeUpdate();
        
        // Step 2: Delete projects associated with the group
        deleteProjectPs.setInt(1, groupId);
        deleteProjectPs.executeUpdate();
        
        // Step 3: Delete the group
        deleteGroupPs.setInt(1, groupId);
        int rowsAffected = deleteGroupPs.executeUpdate();
        
        // Commit the transaction
        conn.c.commit();
        return rowsAffected > 0;
        
    } catch (SQLException e) {
        try {
            conn.c.rollback(); // Rollback on error
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        e.printStackTrace();
        return false;
    } finally {
        try {
            conn.c.setAutoCommit(true); // Restore auto-commit
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




    public static void main(String[] args) {
        StudentDAO dao = new StudentDAO();

        String[] student = dao.getStudentByPRN("PRN004");
        if (student != null) {
            System.out.println("Student Details:");
            for (String data : student) {
                System.out.println(data);
            }

            int groupId = Integer.parseInt(student[6]);
            String[][] group = dao.getGroupAndStudentDetails(groupId);
            if (group != null) {
                System.out.println("\nGroup Details:");
                for (String[] row : group) {
                    for (String data : row) {
                        System.out.print(data + " ");
                    }
                    System.out.println();
                }
            } else {
                System.out.println("No group found with ID: " + groupId);
            }

        } else {
            System.out.println("No student found with PRN: PRN004");
        }
    }
}

