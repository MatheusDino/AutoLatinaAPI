package com.autolatina.controllers;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
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
	public Proprietarios cadastrarProps(@RequestBody Proprietarios props) {
		Proprietarios prop = props;
		
		return propRepo.save(prop);
	}
	
	@PutMapping("/dono/{iden}")
	public Proprietarios editarProps(@PathVariable("iden") long iden, @RequestBody Proprietarios props) {
		Proprietarios propData = propRepo.findById(iden).get();
		Proprietarios proprietario = propData;
		proprietario.setNome(props.getNome());
		proprietario.setEmail(props.getEmail());
		return propRepo.save(proprietario);
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
	public Veiculos cadastrarVeic(@RequestBody Veiculos veics) {
		Veiculos veic = veics;
		return veicRepo.save(veic);
	}
	
	@PutMapping("/veic/{chassis}/{iden}")
	public Veiculos editarVeic(@PathVariable("chassis") long chassis, @PathVariable("iden") long iden, @RequestBody Veiculos veics) {
		Proprietarios propData = propRepo.findById(iden).get();
		Veiculos veicData = veicRepo.findById(chassis).get();
		Veiculos veiculo = veicData;
		veiculo.setMarca(veics.getMarca());
		veiculo.setModelo(veics.getModelo());
		veiculo.setMotor(veics.getMotor());
		veiculo.setAno(veics.getAno());
		veiculo.setPropIden(propData.getIdent());
		return veicRepo.save(veiculo);
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
