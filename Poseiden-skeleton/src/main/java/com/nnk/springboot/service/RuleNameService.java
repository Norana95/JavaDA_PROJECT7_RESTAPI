package com.nnk.springboot.service;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {

    @Autowired
    RuleNameRepository ruleRepository;

    public void addRule(RuleName ruleName) {
        ruleRepository.save(ruleName);
    }

    public Optional<RuleName> findRuleById(Integer id) {
        return ruleRepository.findById(id);
    }

    public void updateRule(RuleName rule) {
        rule.setName(rule.getName());
        rule.setDescription(rule.getDescription());
        rule.setJson(rule.getJson());
        rule.setTemplate(rule.getTemplate());
        rule.setSqlStr(rule.getSqlStr());
        rule.setSqlPart(rule.getSqlPart());
        ruleRepository.save(rule);
    }

    public void deleteRule(RuleName rule) {
        ruleRepository.delete(rule);
    }

    public List<RuleName> getAllRule() {
        return ruleRepository.findAll();
    }
}
