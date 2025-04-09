package dao;
import java.util.List;
import entity.Policy;

public interface IPolicyService {
	boolean createPolicy(Policy policy);
	Policy getPolicy(int policyId);
	List<Policy> getAllPolicies();
	boolean updatePolicy(Policy policy);
	boolean deletePolicy(int policyId);
	

}
