package rs.ac.uns.ftn.informatika.osa.mailService.service;

import java.util.List;

import rs.ac.uns.ftn.informatika.osa.mailService.entity.Rule;

public interface RuleServiceInterface {
	
	List<Rule> findByParent(Rule parent);
	
	Rule findOne(Integer ruleId);
	
	List<Rule> findAll();
	
	Rule save(Rule rule);
	
	void remove(Integer id);

}
