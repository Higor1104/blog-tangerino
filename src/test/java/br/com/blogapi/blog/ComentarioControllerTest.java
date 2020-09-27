package br.com.blogapi.blog;

import java.io.UnsupportedEncodingException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.blogapi.blog.domain.Constantes;
import net.minidev.json.JSONObject;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ComentarioControllerTest extends BaseResourceTest {

	@Test
	public void deveCriarComentario() throws Exception {
		String authorization = recuperarToken("teste.higor");
		Assert.assertNotNull(authorization);
		String idPostagem = cadadastrarPostagem(authorization);
		Assert.assertNotNull(idPostagem);
		String idComentario = cadastrarComentario(authorization, idPostagem);
		Assert.assertNotNull(idComentario);
		String buscarComentarioPath = "/postagem/".concat(idPostagem).concat("/comentario/").concat(idComentario);
		mockMvc.perform(MockMvcRequestBuilders.get(buscarComentarioPath)
											.contentType(MediaType.APPLICATION_JSON)
											.header(Constantes.AUTHORIZATION_HEADER, authorization))
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

	private String cadastrarComentario(String authorization, String idPostagem)
			throws Exception, UnsupportedEncodingException {
		JSONObject comentario = new JSONObject();
		comentario.put("texto", "Meu primeiro comentario");
		String cadastrarComentarioPath = "/postagem/".concat(idPostagem).concat("/comentario");
		MvcResult response = mockMvc.perform(MockMvcRequestBuilders.post(cadastrarComentarioPath)
											.contentType(MediaType.APPLICATION_JSON)
											.content(comentario.toString())
											.header(Constantes.AUTHORIZATION_HEADER, authorization))
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
		.andReturn();
		return response.getResponse().getContentAsString();
	}

	@Test
	public void deveDeletarComentario() throws Exception {
		String authorization = recuperarToken("teste.higor");
		Assert.assertNotNull(authorization);
		String idPostagem = cadadastrarPostagem(authorization);
		Assert.assertNotNull(idPostagem);
		String idComentario = cadastrarComentario(authorization, idPostagem);
		Assert.assertNotNull(idComentario);
		String deletarComentarioPath = "/postagem/".concat(idPostagem).concat("/comentario/").concat(idComentario);
		mockMvc.perform(MockMvcRequestBuilders.delete(deletarComentarioPath)
											.contentType(MediaType.APPLICATION_JSON)
											.header(Constantes.AUTHORIZATION_HEADER, authorization))
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
	}

	@Test
	public void naoDeveDeletarComentario() throws Exception {
		String authorization = recuperarToken("teste.higor");
		Assert.assertNotNull(authorization);
		String idPostagem = cadadastrarPostagem(authorization);
		Assert.assertNotNull(idPostagem);
		String idComentario = cadastrarComentario(authorization, idPostagem);
		Assert.assertNotNull(idComentario);
		String authorizationHigor2 = recuperarToken("teste.higor.2");
		Assert.assertNotNull(authorizationHigor2);
		String deletarComentarioPath = "/postagem/".concat(idPostagem).concat("/comentario/").concat(idComentario);
		mockMvc.perform(MockMvcRequestBuilders.delete(deletarComentarioPath)
											.contentType(MediaType.APPLICATION_JSON)
											.header(Constantes.AUTHORIZATION_HEADER, authorizationHigor2))
		.andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
	}

}
