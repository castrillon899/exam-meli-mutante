package com.meli.mutant.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SequenceDNARequestDTO {

	@NotNull
	@NotEmpty
	private List<String> dna;

}
