package br.com.blogapi.blog;

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
public class PostagemResourceTests extends BaseResourceTest {

	@Test
	public void deveCriarPostagem() throws Exception {
		String authorization = recuperarToken("teste.higor");
		String idPostagem = cadadastrarPostagem(authorization);

		mockMvc.perform(MockMvcRequestBuilders.get("/postagem/".concat(idPostagem))
				.contentType(MediaType.APPLICATION_JSON).header(Constantes.AUTHORIZATION_HEADER, authorization))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

	@Test
	public void deveDeletarPostagem() throws Exception {
		String authorization = recuperarToken("teste.higor");
		String idPostagem = cadadastrarPostagem(authorization);

		mockMvc.perform(MockMvcRequestBuilders.delete("/postagem/".concat(idPostagem))
				.contentType(MediaType.APPLICATION_JSON).header(Constantes.AUTHORIZATION_HEADER, authorization))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

	@Test
	public void naoDeveDeletarPostagem() throws Exception {
		String authorization = recuperarToken("teste.higor");
		String idPostagem = cadadastrarPostagem(authorization);
		String authorizationHigor2 = recuperarToken("teste.higor.2");
		mockMvc.perform(MockMvcRequestBuilders.delete("/postagem/".concat(idPostagem))
				.contentType(MediaType.APPLICATION_JSON).header(Constantes.AUTHORIZATION_HEADER, authorizationHigor2))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}

}
