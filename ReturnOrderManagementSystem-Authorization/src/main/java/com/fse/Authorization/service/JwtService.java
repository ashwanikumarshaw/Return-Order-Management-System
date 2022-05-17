package com.fse.Authorization.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fse.Authorization.dao.UserDao;
import com.fse.Authorization.entity.JwtRequest;
import com.fse.Authorization.entity.JwtResponse;
import com.fse.Authorization.entity.User;
import com.fse.Authorization.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtService implements UserDetailsService {
	@Value("${security.secret_key:AshwaniKumarShaw}")
	private String SECRET_KEY ;
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String userName = jwtRequest.getUserName();
		String userPassword = jwtRequest.getUserPassword();
		authenticate(userName, userPassword);

		UserDetails userDetails = loadUserByUsername(userName);
		String newGeneratedToken = jwtUtil.generateToken(userDetails);

		User user = userDao.findById(userName).get();
		return new JwtResponse(user, newGeneratedToken);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findById(username).get();

		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(),
					getAuthority(user));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

	private Set getAuthority(User user) {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		user.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}

	private void authenticate(String userName, String userPassword) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	public boolean validateJwtToken(String authToken,String userName) throws Exception {
		try {
		      Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(authToken.substring(7));
		      return jwtUtil.verifyRequest(authToken,userName);
		    } catch (SignatureException e) {
		      log.info("Invalid JWT signature.");
		      log.trace("Invalid JWT signature trace: {}", e);
		    } catch (MalformedJwtException e) {
		      log.info("Invalid JWT token.");
		      log.trace("Invalid JWT token trace: {}", e);
		    } catch (ExpiredJwtException e) {
		      log.info("Expired JWT token.");
		      log.trace("Expired JWT token trace: {}", e);
		    } catch (UnsupportedJwtException e) {
		      log.info("Unsupported JWT token.");
		      log.trace("Unsupported JWT token trace: {}", e);
		    } catch (IllegalArgumentException e) {
		      log.info("JWT token compact of handler are invalid.");
		      log.trace("JWT token compact of handler are invalid trace: {}", e);
		    }
		    return false;
	}
}
