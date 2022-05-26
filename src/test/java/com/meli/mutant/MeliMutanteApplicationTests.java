package com.meli.mutant;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.mutant.dto.StatsDNAResponseDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MeliMutanteApplicationTests {

	@Autowired
	private WebApplicationContext appContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.appContext).build();
	}

	/**
	 * Test 1 - validar estadisticas iniciales cuando inicia el programa
	 * 
	 */
	@Test
	public void test01StatsTestBegin() throws Exception {
		// @formatter:off
		ResultActions result = mockMvc.perform(
				get("/stats/")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.count_mutant_dna").value(0))
				.andExpect(jsonPath("$.count_human_dna").value(0))
				.andExpect(jsonPath("$.ratio").value(0));
		
		byte[] content = result.andReturn().getResponse().getContentAsByteArray();

		StatsDNAResponseDTO dnaResult = new ObjectMapper().readValue(content, StatsDNAResponseDTO.class);
	//	
		
		Assert.assertEquals(true,dnaResult.getHumanCount()==0L);
		Assert.assertEquals(true,dnaResult.getHumanCount() == 0L);
		Assert.assertEquals(true,dnaResult.getMutantCount() == 0L);
		Assert.assertEquals(BigDecimal.ZERO,dnaResult.getRatio());
		
		// @formatter:on
	}

	/**
	 * Test 2 consulta humano no mutante, retorna un 403
	 * 
	 */
	@Test
	public void test02TestHumanDNA() throws Exception {
		// @formatter:off
		String request = "{\n"+ 
				"\"dna\": " +
				"        [\"ACCTATC\",\n" + 
				"         \"CTCACTT\",\n" + 
				"         \"ACGCTAT\",\n" + 
				"         \"ACCTACC\",\n" + 
				"         \"CAATTCC\",\n" + 
				"         \"CACCAAT\",\n" + 
				"         \"CAACAAT\"" +
				"        ]\n" +
				"}"; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isForbidden())
			;
		// @formatter:on
	}

	/**
	 * Test 3, Consultamos al api tres veces con la misma informacion de humano no
	 * mutante El sistema no debe registrar dnas duplicados
	 */
	@Test
	public void test02aTestOnlyOneDNA() throws Exception {
		test02TestHumanDNA();
		test02TestHumanDNA();
		test02TestHumanDNA();
		// @formatter:off
		mockMvc.perform(
				get("/stats/")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.count_mutant_dna").value(0))
				.andExpect(jsonPath("$.count_human_dna").value(1))
				.andExpect(jsonPath("$.ratio").value(0));
		// @formatter:on
	}

	/**
	 * Test - Mutant/Human - Status with one human
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03StatsOneHuman() throws Exception {
		// @formatter:off
		mockMvc.perform(
				get("/stats/")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.count_mutant_dna").value(0))
				.andExpect(jsonPath("$.count_human_dna").value(1))
				.andExpect(jsonPath("$.ratio").value(0));
		
		// @formatter:on
	}

	/**
	 * Test - Humano mutante - Comprobacion de humano mutante apartir de diagonal
	 * hacia arriba
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04MutantDiagnonalUPLastPositionValid() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"AACCCC\",\n" + 
				"	          \"CTCAGC\",\n" + 
				"	          \"ACCAAA\",\n" + 
				"	          \"ACAAAC\",\n" + 
				"	          \"CAAATC\",\n" + 
				"	          \"CAACAA\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Humano no mutante - Solo se encuentra secuencia DNA valida diagonal
	 * hacia arriba,
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05humanDiagnonalUPOneSequenceAtRowZero() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"AATCCCA\",\n" + 
				"	          \"CTCACTT\",\n" + 
				"	          \"ACCCTAT\",\n" + 
				"	          \"ACCTACC\",\n" + 
				"	          \"CAATTCC\",\n" + 
				"	          \"CAACAAT\",\n" + 
				"	          \"CAACAAT\"]\n" + 
				"}"; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isForbidden());
		// @formatter:on
	}

	/**
	 * Test - Humano mutante - Diagonal hacia abajo se encuentra secuencia
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06mutantDiagnonalDOWNRespectiveLastLine() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\":  [\"AATACCA\",\n" + 
				"	           \"CTCAATT\",\n" + 
				"	           \"ACCCTAT\",\n" + 
				"	           \"ACCTACA\",\n" + 
				"	           \"CAATTCC\",\n" + 
				"	           \"CAACAAT\",\n" + 
				"	           \"CAAAAAT\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Humano mutante - Prueba secuencia horizontal fila 0 y 6
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07mutantHorizontal() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"ACCCCCT\",\n" + 
				"	          \"CTCACTT\",\n" + 
				"	          \"ACCCTAT\",\n" + 
				"	          \"ACCTACC\",\n" + 
				"	          \"CAACTCC\",\n" + 
				"	          \"CAACAAT\",\n" + 
				"	          \"CAACCCC\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Humano mutante - Prueba vertical colunna 2 y 6
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08mutantVertical() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"ACCACCT\",\n" + 
				"	          \"CTCACTT\",\n" + 
				"	          \"ACCATAT\",\n" + 
				"	          \"AAAGACT\",\n" + 
				"	          \"CAACTCC\",\n" + 
				"	          \"CAACAAT\",\n" + 
				"	          \"CAACCCA\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isOk());
		// @formatter:on
	}

	/**
	 * Test - Obtener estadisticas Humano mutantes
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09StatsMutantHuman() throws Exception {
		// @formatter:off
		mockMvc.perform(
				get("/stats/")
					.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.count_mutant_dna").value(4))
				.andExpect(jsonPath("$.count_human_dna").value(2))
				.andExpect(jsonPath("$.ratio").value(2));
		
		// @formatter:on
	}

	/**
	 * Test - Human - Sequence less than 4
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10HumanSequenceLessThan4() throws Exception {
		// @formatter:off
		String request = "{\n"+ 
				"\"dna\": " +
				"        [\"ACC\",\n" + 
				"         \"CTC\",\n" + 
				"         \"ACG\"\n" + 
				"        ]\n" +
				"}"; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isForbidden())
			;
		// @formatter:on
	}

	/**
	 * Invalid Request - The length of the DNA sequences must be the same size.
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void invalidRequestInconsistentLength() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"CTXXXXT\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACGCTAT\",\n" + 
				"	        \"ACCTACC\",\n" + 
				"	        \"CAATTCC\",\n" + 
				"	        \"CACCAAC\",\n" + 
				"	        \"CAACAAT\"]\n" + 
				"}\n" + 
				""; 
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isBadRequest());
		// @formatter:on
	}

	/**
	 * Invalid Request - Invalid Nitrogenous base. The only valid characters are A,
	 * T, C e G.
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	public void invalidRequestInvalidNitrogenousBase() throws Exception {
		// @formatter:off
		String request = "{\n" + 
				"	\"dna\": [\"ACCTATC\",\n" + 
				"	        \"CTCACTT\",\n" + 
				"	        \"ACGCTAT\",\n" + 
				"	        \"ACCTACC\",\n" + 
				"	        \"CAATTCC\",\n" + 
				"	        \"CC\",\n" + 
				"	        \"CAACAAT\"]\n" + 
				"}\n" + 
				""; 
		 mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isBadRequest());
		// @formatter:on
	}

	/**
	 * Invalida secuencia enviada,
	 * 
	 * @throws Exception
	 */
	@Test
	public void invalidRequestWithoutDNA() throws Exception {
		// @formatter:off
		String request = "{ }";
		mockMvc.perform(
				post("/mutant/")
					.contentType(MediaType.APPLICATION_JSON)
					.content(request.getBytes()))
				.andExpect(status().isBadRequest());
		
		// @formatter:on
	}

}
