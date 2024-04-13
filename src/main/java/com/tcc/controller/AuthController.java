package com.tcc.controller;

import com.tcc.config.TccUserDetailService;
import com.tcc.config.jwt.JwtProvider;
import com.tcc.entity.Employee;
import com.tcc.Types.Roles;
import com.tcc.repository.EmployeeRepository;
import com.tcc.request.LoginRequest;
import com.tcc.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private  EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private TccUserDetailService userDetailService;


    /*
    SIGN UP ENDPOINT
     */
    @PostMapping("/admin/sign-up")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody Employee emp) throws Exception {

        Optional<Employee> isEmailExist = employeeRepository.findByEmail(emp.getEmail());

        if(isEmailExist.isPresent()){
            throw  new Exception("Email already exits ....");
        }

        /*
        creating a new user with specific role and other details
         */
        Employee createdEmp = new Employee();
        createdEmp.setEmail(emp.getEmail());
        createdEmp.setEmpName(emp.getEmpName());
        createdEmp.setDateOfJoining(new Date());
        createdEmp.setUserRole(emp.getUserRole());
        createdEmp.setBranchOfficeId(emp.getBranchOfficeId());
        createdEmp.setPassword(passwordEncoder.encode(emp.getPassword()));


        Employee savedEmployee = employeeRepository.save(createdEmp);


        /*
        The authentication abject is created according to the standards of spring security
         */
        Authentication authentication = new UsernamePasswordAuthenticationToken(savedEmployee.getEmail(),
                savedEmployee.getPassword());

        /*
        The authentication details stored in the SecurityContextHolder
        for future authentication of the same user
         */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /*
        JWT token is generated from the authentication object
         */
        String jwt = jwtProvider.generateToken(authentication);

        /*
        Apt.  response message is displayed to the end user of the application
         */
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully!!");
        authResponse.setRole(savedEmployee.getUserRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }



    /*
    SIGN IN END POINT
     */
    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> signInUserHandler(@RequestBody LoginRequest loginRequest) throws Exception {

        String userName = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        /*
        authenticate()  method returns and Authentication object with success or failure
        status
         */
        Authentication authentication = authenticate(userName, password);

        /*
        JWT token is generated
         */
        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Logged in Successfully!!");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        /*
        Actually role can not be null since we have set a default value
         */
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();
        authResponse.setRole(Roles.valueOf(role));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }




    /*
    Our own custom Authentication provider: authenticate() method (Default is DAO authentication provider)
     */
    private Authentication authenticate(String userName, String password) throws BadCredentialsException {

        /*
        Calls the method loadUserByUserName() of the CustomUserDetailService class i.e.  TccUserDetailService
         */
        UserDetails userDetails = userDetailService.loadUserByUsername(userName);

        if (userDetails == null){
            throw new BadCredentialsException("User not found with email : "+userName);
        }

        //System.out.println(passwordEncoder.matches(password, userDetails.getPassword()));
        if(! passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password ....");
        }

        /*
        NEW authentication type object
         */
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
