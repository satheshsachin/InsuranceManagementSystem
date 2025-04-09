package mainmod;
import java.util.List;
import java.util.Scanner;

import dao.InsuranceServiceImpl;
import dao.IPolicyService;
import entity.Policy;
import myexceptions.PolicyNotFoundException;

public class MainModule {
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IPolicyService service = new InsuranceServiceImpl();
        int choice;
        
        do {
            System.out.println("\n====== Insurance Management System ======");
            System.out.println("1. Create Policy");
            System.out.println("2. Get Policy by ID");
            System.out.println("3. Get All Policies");
            System.out.println("4. Update Policy");
            System.out.println("5. Delete Policy");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Policy ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Policy Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Policy Type: ");
                        String type = sc.nextLine();
                        System.out.print("Enter Premium Amount: ");
                        double premium = sc.nextDouble();

                        Policy newPolicy = new Policy(id, name, type, premium);
                        if (service.createPolicy(newPolicy)) {
                            System.out.println("Policy created successfully!");
                        }
                        break;
                        
                    case 2:
                        System.out.print("Enter Policy ID: ");
                        int searchId = sc.nextInt();
                        Policy policy = service.getPolicy(searchId);
                        System.out.println(policy);
                        break;

                    case 3:
                        List<Policy> policies = service.getAllPolicies();
                        for (Policy p : policies) {
                            System.out.println(p);
                        }
                        break;
                        
                    case 4:
                        System.out.print("Enter Policy ID to update: ");
                        int updateId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Policy Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Policy Type: ");
                        String newType = sc.nextLine();
                        System.out.print("Enter New Premium Amount: ");
                        double newPremium = sc.nextDouble();

                        Policy updatedPolicy = new Policy(updateId, newName, newType, newPremium);
                        if (service.updatePolicy(updatedPolicy)) {
                            System.out.println("Policy updated successfully!");
                        }
                        break;
                        
                    case 5:
                        System.out.print("Enter Policy ID to delete: ");
                        int deleteId = sc.nextInt();
                        if (service.deletePolicy(deleteId)) {
                            System.out.println("Policy deleted successfully!");
                        }
                        break;

                    case 6:
                        System.out.println("Exiting the system...");
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }
                
            } catch (PolicyNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }

        } while (choice != 6);

        sc.close();
    }

}
