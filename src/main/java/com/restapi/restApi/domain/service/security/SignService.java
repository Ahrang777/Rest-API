package com.restapi.restApi.domain.service.security;

import com.restapi.restApi.advice.exception.CEmailLoginFailedException;
import com.restapi.restApi.advice.exception.CEmailSignupFailedException;
import com.restapi.restApi.advice.exception.CRefreshTokenException;
import com.restapi.restApi.advice.exception.CUserNotFoundException;
import com.restapi.restApi.config.security.JwtProvider;
import com.restapi.restApi.config.security.RoleType;
import com.restapi.restApi.domain.entity.RefreshToken;
import com.restapi.restApi.domain.entity.Role;
import com.restapi.restApi.domain.entity.User;
import com.restapi.restApi.domain.repository.RefreshTokenJpaRepo;
import com.restapi.restApi.domain.repository.UserJpaRepo;
import com.restapi.restApi.domain.service.dto.TokenDto;
import com.restapi.restApi.domain.service.dto.UserSignupDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SignService {

    private final UserJpaRepo userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final RefreshTokenJpaRepo refreshTokenJpaRepository;

    public TokenDto login(String email, String password) {

        // 회원 정보 존재하는지 확인
        User user = userJpaRepository.findByEmail(email).orElseThrow(CEmailLoginFailedException::new);

        // 회원 패스워드 일치 여부 확인
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CEmailLoginFailedException();
        }

        // AccessToken, RefreshToken 발급
        TokenDto tokenDto = jwtProvider.createTokenDto(user.getId(), user.getRoles());


        // TODO siginup에서 refreshToken은 저장되기에 무조건 DB에 해당 user의 refreshToken은 존재하고 갱신만 있는게 맞지 않을까?
        // RefreshToken 레포에 기존 RefreshToken 있는 경우 갱신
        if (refreshTokenJpaRepository.findByKey(user.getId()).isPresent()) {
            RefreshToken refreshToken = refreshTokenJpaRepository.findByKey(user.getId()).get();
            // 더티체킹 이용한 RefreshToken 수정
            refreshToken.updateToken(tokenDto.getRefreshToken());
        } else {    //
            RefreshToken refreshToken = RefreshToken.createRefreshToken(user.getId(), tokenDto.getRefreshToken());
            refreshTokenJpaRepository.save(refreshToken);
        }

        return tokenDto;
    }

    /*// Refresh Token DB에서 해당 user의 Refresh Token 삭제
    public void logout() {

    }*/

    public Long signup(UserSignupDto userSignupDto) {
        if (userJpaRepository.findByEmail(userSignupDto.getEmail()).isPresent()) {
            throw new CEmailSignupFailedException();
        }

        log.info("=======================check1");

//        User user = User.createUser(userSignupDto.getEmail(), passwordEncoder.encode(userSignupDto.getPassword()),
//                userSignupDto.getName(), userSignupDto.getNickname());

        User user = User.builder().email(userSignupDto.getEmail()).password(passwordEncoder.encode(userSignupDto.getPassword()))
                .name(userSignupDto.getName()).nickname(userSignupDto.getNickname()).build();

        log.info("=======================check2");

        log.info("role: {}", user.getRoles());

        user.getRoles().add(new Role(RoleType.ROLE_USER));
        log.info("role = {}", user.getRoles().get(0));

        log.info("=======================check3");

        return userJpaRepository.save(user).getId();
    }

    public TokenDto reissue(String accessToken, String refreshToken) {

        // 만료된 refresh token 에러
        if (!jwtProvider.validationToken(refreshToken)) {
            throw new CRefreshTokenException();
        }

        // AccessToken 에서 PK 가져오기
        Authentication authentication = jwtProvider.getAuthentication(accessToken);


        // PK 로 User 검색
        User user = userJpaRepository.findById(Long.parseLong(authentication.getName())).orElseThrow(CUserNotFoundException::new);

        // 앞에서 예외 터지면 아래까지 안온다. 아래까지 온다는것은 user가 존재한다는 것 
        // CUserNotFoundException 예외가 발생 안했다는것
        // 따라서 user.getId() 를 써도 된다.
        // user_id로 Refresh Token 검색
        RefreshToken token = refreshTokenJpaRepository.findByKey(user.getId()).orElseThrow(CRefreshTokenException::new);

        // 리프레시 토큰 불일치 에러
        if (!token.getToken().equals(refreshToken)) {
            throw new CRefreshTokenException();
        }

        // AccessToken, RefreshToken 재발급, RefreshToken 갱신
        TokenDto tokenDto = jwtProvider.createTokenDto(user.getId(), user.getRoles());
        // 더티체킹으로 변경
        token.updateToken(tokenDto.getRefreshToken());

        return tokenDto;
    }
}
