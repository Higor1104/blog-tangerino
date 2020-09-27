package br.com.blogapi.blog.domain.auth;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.blogapi.blog.infrastructure.persistence.usuario.Usuario;
import br.com.blogapi.blog.infrastructure.persistence.usuario.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService implements UserDetailsService {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private String jwtExpiration;

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<Usuario> usuario = repository.findByLogin(login);
		if (usuario.isPresent()) {
			return usuario.get();
		}

		throw new UsernameNotFoundException("Usuário não encontrado!");
	}

	public String gerarToken(Authentication authentication) {
		Usuario usuario = (Usuario) authentication.getPrincipal();
		Date today = new Date();
		Date dataExpiracao = new Date(today.getTime() + Long.parseLong(jwtExpiration));

		return Jwts.builder().setIssuer("Blog do Tangerino").setSubject(usuario.getId().toString()).setIssuedAt(today)
				.setExpiration(dataExpiracao).signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}

	public boolean isTokenValido(String token) {
		boolean tokenValido = true;
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
		} catch (Exception e) {
			tokenValido = false;
		}
		return tokenValido;
	}

	public Optional<Usuario> obterUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		Long idUsuario = Long.parseLong(claims.getSubject());
		return repository.findById(idUsuario);
	}
}
