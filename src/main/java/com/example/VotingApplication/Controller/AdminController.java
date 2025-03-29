package com.example.VotingApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.VotingApplication.Entity.Admin;
import com.example.VotingApplication.Entity.Candidate;
import com.example.VotingApplication.Entity.Status;
import com.example.VotingApplication.Entity.User;
import com.example.VotingApplication.Entity.VoteResult;
import com.example.VotingApplication.userRepo.UserRepository;
import com.example.VotingApplication.userservice.AdminService;
import com.example.VotingApplication.userservice.UserManagementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserManagementService userManagementService;
		
	@Autowired
	UserRepository userRepository;
	
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        if (adminService.authenticateAdmin(admin.getUsername(), admin.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/pending-users")
    public List<User> getPendingUsers() {
        return adminService.getPendingUsers();
    }

    @PostMapping("/add-candidate")
    public ResponseEntity<String> addCandidate(@RequestBody Candidate candidate) {
        adminService.addCandidate(candidate);
        return ResponseEntity.ok("Candidate added successfully");
    }

    @GetMapping("/results")
    public List<Object[]> getResults() {
        return adminService.getVoteResults();
    }
    
    
   
    
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userManagementService.getAllUsers();
    }

  
    @PutMapping("/users/{id}/status")
    public ResponseEntity<String> updateUserStatus(@PathVariable Long id, @RequestParam Status newStatus) {
        String result = userManagementService.updateUserStatus(id, newStatus);
        if (result.equals("User status updated successfully.")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(404).body(result);
        }
    }
    
}
