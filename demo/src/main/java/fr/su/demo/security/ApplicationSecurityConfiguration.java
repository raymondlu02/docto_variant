package fr.su.demo.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
 @EnableWebSecurity
 public class ApplicationSecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // @Autowired
    // private DataSource dataSource;

    // @Autowired
    // public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
    // auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
    //         .dataSource(dataSource)
    //         .usersByUsernameQuery("select mail, username, enabled from professionnel where username=?")
    //         .authoritiesByUsernameQuery("select nom, role from professionnel where nom=?");
    // }

     @Bean
     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        // http.formLogin();

        // http.authorizeRequests().antMatchers("/inscriptionUtilisateur").hasRole("USER");
        
        // http.authorizeRequests().anyRequest().authenticated();
        //  return http.build();

        http
        .authorizeRequests()
        .antMatchers("/","/inscriptionUtilisateur","/inscriptionPro","/inscription/**","/login","/disponibilite","/liste","/logout","/listeByNom").permitAll()
        .antMatchers("/","/inscriptionUtilisateur","/inscriptionPro","/inscription/**","/login","/disponibilite","/liste","/logout","/listeByNom","/disponibilite/prendreRDVNonCo","/disponibilite/prendreRDV" ).permitAll()

        .antMatchers("/css/**","/images/**" ).permitAll()
        .antMatchers("/professionnel/**").hasRole("PRO") //.permitAll()
        .antMatchers("/utilisateur/**").hasRole("USER")
        //.antMatchers("/hello").hasRole("pro")
        .anyRequest()
        .authenticated()
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .and()
        .httpBasic();
        http.formLogin();

        return http.build();




     }
        // http
        //     .authorizeRequests()
        //     .antMatchers("/", "/liste*", "/connexion", "/inscription", "/disponibilite","/logout","/login").permitAll()
        //     .antMatchers("/hello").hasRole("pro")
        //     .anyRequest()
        //     .authenticated()
        //     .and()
        //     .logout()
        //     .logoutSuccessUrl("/")
        //     .and()
        //     .httpBasic();
        // http.formLogin();

    

    //  @Bean
    //  public UserDetailsService userDetailsService() {
    //      UserDetails user = User.builder()
    //          .username("user")
    //          .password(passwordEncoder().encode("password"))
    //          .roles("USER")
    //          .build();

    //          UserDetails user2 = User.withDefaultPasswordEncoder()
    //          .username("user2")
    //          .password("password")
    //          .roles("NOTUSER")
    //          .build();
    //      return new InMemoryUserDetailsManager(user,user2);
    //  }


 }