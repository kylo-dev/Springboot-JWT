package kylo.spring.jwttutorial.service.impl;

import kylo.spring.jwttutorial.repository.UserRepository;
import kylo.spring.jwttutorial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // UserDetailsService를 구현한 익명 클래스에서 loadUserByUsername 메서드에서
    // userRepository.findByEmail(email)를 통해 사용자를 조회하고 있습니다.
    // 이 메서드는 UserDetails를 구현한 객체를 반환하고 있습니다.
    // 이 객체에서 getUsername() 메서드를 호출하면 해당 사용자의 이메일 값이 반환될 것입니다.
//    @Override
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//                return userRepository.findByEmail(email)
//                        .orElseThrow(()-> new UsernameNotFoundException("User not found"));
//            }
//        };
//    }
}
