package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.Policy;
import myexceptions.PolicyNotFoundException;
import util.DBConnUtil;

public class InsuranceServiceImpl implements IPolicyService {

    private final String DB_FILE = "db.properties";

    @Override
    public boolean createPolicy(Policy policy) {
        String query = "INSERT INTO policy VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnUtil.getConnection(DB_FILE);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, policy.getPolicyId());
            stmt.setString(2, policy.getPolicyName());
            stmt.setString(3, policy.getPolicyType());
            stmt.setDouble(4, policy.getPremiumAmount());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error inserting policy: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Policy getPolicy(int policyId) {
        String query = "SELECT * FROM policy WHERE policy_id = ?";
        try (Connection conn = DBConnUtil.getConnection(DB_FILE);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, policyId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Policy(
                        rs.getInt("policy_id"),
                        rs.getString("policy_name"),
                        rs.getString("policy_type"),
                        rs.getDouble("premium_amount"));
            } else {
                throw new PolicyNotFoundException("Policy ID " + policyId + " not found.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching policy: " + e.getMessage());
        }
    }

    @Override
    public List<Policy> getAllPolicies() {
        List<Policy> list = new ArrayList<>();
        String query = "SELECT * FROM policy";
        try (Connection conn = DBConnUtil.getConnection(DB_FILE);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(new Policy(
                        rs.getInt("policy_id"),
                        rs.getString("policy_name"),
                        rs.getString("policy_type"),
                        rs.getDouble("premium_amount")));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching all policies: " + e.getMessage());
        }
        return list;
    }

    @Override
    public boolean updatePolicy(Policy policy) {
        String query = "UPDATE policy SET policy_name = ?, policy_type = ?, premium_amount = ? WHERE policy_id = ?";
        try (Connection conn = DBConnUtil.getConnection(DB_FILE);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, policy.getPolicyName());
            stmt.setString(2, policy.getPolicyType());
            stmt.setDouble(3, policy.getPremiumAmount());
            stmt.setInt(4, policy.getPolicyId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new PolicyNotFoundException("Policy ID " + policy.getPolicyId() + " not found.");
            }

            return true;

        } catch (SQLException e) {
            System.out.println("Error updating policy: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletePolicy(int policyId) {
        String query = "DELETE FROM policy WHERE policy_id = ?";
        try (Connection conn = DBConnUtil.getConnection(DB_FILE);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, policyId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new PolicyNotFoundException("Policy ID " + policyId + " not found.");
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Error deleting policy: " + e.getMessage());
            return false;
        }
    }
}
