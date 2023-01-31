package fr.su.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.su.demo.entities.Personne;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        System.out.println("\n --- loadUserByUsername --- \n ");
		Personne user = userRepository.findUserByMail(mail);
		
		boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        System.out.println("\n --- role : "+ user.getRoles().get(0) +" --- \n ");
        
        return new org.springframework.security.core.userdetails.User(
          user.getMail(), user.getPassword().toLowerCase(), enabled, accountNonExpired,
          credentialsNonExpired, accountNonLocked, getAuthorities(user.getRoles()));
    }
	private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        System.out.println("\n --- getAuthorities --- \n");
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        System.out.println("\n --- end getAuth --- \n");
        return authorities;
    }


}

    
 
    // @Autowired
    // private IUserService service;
 
    // @Autowired
    // private MessageSource messages;
 
    // @Autowired
    // private RoleRepository roleRepository;

    // @Override
    // public UserDetails loadUserByUsername(String email)
    //   throws UsernameNotFoundException {
 
    //     User user = userRepository.findByEmail(email);
    //     if (user == null) {
    //         return new org.springframework.security.core.userdetails.User(
    //           " ", " ", true, true, true, true, 
    //           getAuthorities(Arrays.asList(
    //             roleRepository.findByName("ROLE_USER"))));
    //     }

    //     return new org.springframework.security.core.userdetails.User(
    //       user.getEmail(), user.getPassword(), user.isEnabled(), true, true, 
    //       true, getAuthorities(user.getRoles()));
    // }

    // private Collection<? extends GrantedAuthority> getAuthorities(
    //   Collection<Role> roles) {
 
    //     return getGrantedAuthorities(getPrivileges(roles));
    // }

    // private List<String> getPrivileges(Collection<Role> roles) {
 
    //     List<String> privileges = new ArrayList<>();
    //     List<Privilege> collection = new ArrayList<>();
    //     for (Role role : roles) {
    //         privileges.add(role.getName());
    //         collection.addAll(role.getPrivileges());
    //     }
    //     for (Privilege item : collection) {
    //         privileges.add(item.getName());
    //     }
    //     return privileges;
    // }

    // private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
    //     List<GrantedAuthority> authorities = new ArrayList<>();
    //     for (String privilege : privileges) {
    //         authorities.add(new SimpleGrantedAuthority(privilege));
    //     }
    //     return authorities;
    // }
//}