package com.autolatina.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autolatina.models.Proprietarios;
import com.autolatina.models.Veiculos;
import com.autolatina.repositories.ProprietarioRepository;
import com.autolatina.repositories.VeiculosRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ProprietariosController {
	
	@Autowired
	ProprietarioRepository propRepo;
	@Autowired
	VeiculosRepository veicRepo;
	
	//controle de Proprietarios
	
	@GetMapping("/dono")
	public List<Proprietarios> listarProps() {
		return propRepo.findAll();
	}
	
	@PostMapping("/dono")
	public String cadastrarProps(@RequestBody Proprietarios props) {
		Proprietarios prop = props;
		try {
			propRepo.save(prop);
			return "Proprietario cadastrado com sucesso. ";			
		}
		catch(DataIntegrityViolationException e) {
			return "Por favor preencha todas as informações e tente novamente. ";
		}
	}
	
	@PutMapping("/dono/{iden}")
	public String editarProps(@PathVariable("iden") long iden, @RequestBody Proprietarios props) {
		try {
			Proprietarios propData = propRepo.findById(iden).get();
			Proprietarios proprietario = propData;
			proprietario.setNome(props.getNome());
			proprietario.setEmail(props.getEmail());
			propRepo.save(proprietario);	
			return "Proprietário alterado com sucesso. ";
		}
		catch(DataIntegrityViolationException e) {
			return "Por favor preencha todas as informações e tente novamente. ";
		}
	}
	
	@DeleteMapping("/dono")
	public String deletarTodosProps() {
		propRepo.deleteAllInBatch();
		return "Todos os proprietarios foram deletados";
	}
	
	@DeleteMapping("dono/{iden}")
	public String deletarProp(@PathVariable("iden") long iden) {
		propRepo.deleteById(iden);
		return "Deletado Proprietário com ID: " + iden;
	}
	
	@GetMapping("/dono/{chassis}")
	public Proprietarios checarDonoVeic(@PathVariable("chassis") long chassis) {
		Veiculos veicData = veicRepo.findById(chassis).get();
		return propRepo.findById(veicData.getPropIden()).get();
	}
	
	// controle de Veiculos
	
	@GetMapping("/veic")
	public List<Veiculos> listarVeic() {
		return veicRepo.findAll();
	}
	
	@PostMapping("/veic")
	public String cadastrarVeic(@RequestBody Veiculos veics) {
		Veiculos veiculo = veics;
		try {
			if(veiculo.getAno() == 0) {
				throw new DataIntegrityViolationException("Falta o ano do veículo.");
			}
			veicRepo.save(veiculo);
			return "Veículo salvo com sucesso";
		}
		catch(DataIntegrityViolationException e) {
			return "Por favor preencha todas as informações e tente novamente. ";
		}
	}
	
	@PutMapping("/veic/{chassis}")
	public String editarVeic(@PathVariable("chassis") long chassis, @RequestBody Veiculos veics) {
		try {
			if(veics.getAno() == 0) {
				throw new DataIntegrityViolationException("Falta o ano do veículo");
			}
			Veiculos veicData = veicRepo.findById(chassis).get();
			Veiculos veiculo = veicData;
			veiculo.setMarca(veics.getMarca());
			veiculo.setModelo(veics.getModelo());
			veiculo.setMotor(veics.getMotor());
			veiculo.setAno(veics.getAno());
			
			if(veics.getPropIden() != 0) {
				Proprietarios propData = propRepo.findById(veics.getPropIden()).get();
				veiculo.setPropIden(propData.getIdent());				
			}
			veicRepo.save(veiculo);
			return "Veiculo alterado com sucesso.";
		}
		catch(DataIntegrityViolationException nullInfo) {
			return "Por favor preencha todas as informações e tente novamente. Erro: " + nullInfo.getMessage();
		}
		catch(NoSuchElementException NSEE) {
			return "Por favor preencha todas as informações e tente novamente. Erro: " + NSEE.getMessage();
		}
	}
	
	@DeleteMapping("/veic")
	public String deletarTodosVeic() {
		veicRepo.deleteAllInBatch();
		return "Todos os veiculos foram deletados.";
	}
	
	@DeleteMapping("/veic/{chassis}")
	public String deletarVeic(@PathVariable("chassis") long chassis) {
		veicRepo.deleteById(chassis);
		return "Deletado veiculo com chassi: " + chassis;
	}
	
	

}
