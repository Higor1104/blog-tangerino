package br.com.blogapi.blog;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.blogapi.blog.domain.Constantes;

public class BaseResourceTest {

	@Autowired
	protected MockMvc mockMvc;

	protected String recuperarToken(String login) throws Exception {
		JSONObject loginForm = new JSONObject();
		loginForm.put("login", login);
		loginForm.put("senha", "123456");
		MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/usuario/login")
				.content(loginForm.toString())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
				.andExpect(MockMvcResultMatchers.header().exists(Constantes.AUTHORIZATION_HEADER))
				.andReturn().getResponse();

		return response.getHeader(Constantes.AUTHORIZATION_HEADER);
	}

	protected String cadadastrarPostagem(String authorization) throws JSONException, Exception, UnsupportedEncodingException {
		JSONObject cadastroLoginForm = new JSONObject();
		cadastroLoginForm.put("texto", "Minha primeira postagem");
		cadastroLoginForm.put("titulo", "Titulo da minha primeira postagem");
		MvcResult response = mockMvc
				.perform(MockMvcRequestBuilders.post("/postagem").content(cadastroLoginForm.toString())
						.contentType(MediaType.APPLICATION_JSON).header(Constantes.AUTHORIZATION_HEADER, authorization))
				.andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()))
				.andReturn();
		String idPostagem = response.getResponse().getContentAsString();
		return idPostagem;
	}
}
