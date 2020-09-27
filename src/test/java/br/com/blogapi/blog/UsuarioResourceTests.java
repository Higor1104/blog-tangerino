package br.com.blogapi.blog;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.blogapi.blog.domain.Constantes;

@SpringBootTest(classes = BlogApplication.class)
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UsuarioResourceTests extends BaseResourceTest {

	@Test
	public void deveLogar() throws Exception {
		JSONObject loginForm = new JSONObject();
		loginForm.put("login", "teste.higor");
		loginForm.put("senha", "123456");
		mockMvc.perform(MockMvcRequestBuilders.post("/usuario/login").content(loginForm.toString())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value())).andExpect(MockMvcResultMatchers.header().exists(Constantes.AUTHORIZATION_HEADER));
	}

	@Test
	public void naoDeveLogar() throws Exception {
		JSONObject loginForm = new JSONObject();
		loginForm.put("login", "teste.higor");
		loginForm.put("senha", "1");
		mockMvc.perform(MockMvcRequestBuilders.post("/usuario/login").content(loginForm.toString())
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.FORBIDDEN.value()));
	}

	@Test
	public void deveCriarUsuario() throws Exception {
		String authorization = recuperarToken("teste.higor");
		
		JSONObject cadastroLoginForm = new JSONObject();
		cadastroLoginForm.put("login", "teste.higor.cadastro");
		cadastroLoginForm.put("senha", "123456");
		mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
											.content(cadastroLoginForm.toString())
											.contentType(MediaType.APPLICATION_JSON)
											.header(Constantes.AUTHORIZATION_HEADER, authorization))
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

	@Test
	public void naoDeveCriarUsuario() throws Exception {
		String authorization = recuperarToken("teste.higor");
		
		JSONObject cadastroLoginForm = new JSONObject();
		cadastroLoginForm.put("login", "teste.higor.2");
		cadastroLoginForm.put("senha", "");
		mockMvc.perform(MockMvcRequestBuilders.post("/usuario")
											.content(cadastroLoginForm.toString())
											.contentType(MediaType.APPLICATION_JSON)
											.header(Constantes.AUTHORIZATION_HEADER, authorization))
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.BAD_REQUEST.value()));
	}
}
