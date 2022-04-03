package com.nnk.springboot.service;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RuleNameService {

    @Autowired
    RuleNameRepository ruleRepository;

    Logger logger = LoggerFactory.getLogger(RuleNameService.class);

    public void addRule(RuleName ruleName) {
        ruleRepository.save(ruleName);
        logger.info("rule saved !");
    }

    public Optional<RuleName> findRuleById(Integer id) {
        logger.info("inside method findRuleById in RuleNameService");
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
        logger.info("rule updated !");
    }

    public void deleteRule(RuleName rule) {
        ruleRepository.delete(rule);
        logger.info("rule deleted !");
    }

    public List<RuleName> getAllRule() {
        logger.info("inside method getAllRule in RuleNameService");
        return ruleRepository.findAll();
    }
}
