package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Rule;
import rs.ac.uns.ftn.informatika.osa.mailService.repository.RuleRepository;

@Service
public class RuleService implements RuleServiceInterface {

	@Autowired
	RuleRepository ruleRepository;
	
	@Override
	public List<Rule> findByParent(Rule parent) {
		return null;
	}

	@Override
	public Rule findOne(Integer ruleId) {
		return ruleRepository.findOne(ruleId);
	}

	@Override
	public List<Rule> findAll() {
		return ruleRepository.findAll();
	}

	@Override
	public Rule save(Rule rule) {
		return ruleRepository.save(rule);
	}

	@Override
	public void remove(Integer id) {
		ruleRepository.delete(id);
		
	}
	

}
