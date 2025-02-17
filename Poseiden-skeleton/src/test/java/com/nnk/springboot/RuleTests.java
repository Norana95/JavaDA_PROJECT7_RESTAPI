package com.nnk.springboot;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTests {

	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("RuleName Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		rule = ruleNameRepository.save(rule);
		Assert.assertNotNull(rule.getId());
		Assert.assertTrue(rule.getName().equals("RuleName Name"));

		// Update
		rule.setName("RuleName Name Update");
		rule = ruleNameRepository.save(rule);
		Assert.assertTrue(rule.getName().equals("RuleName Name Update"));

		// Find
		List<RuleName> listResult = ruleNameRepository.findAll();
		Assert.assertTrue(listResult.size() > 0);

		// Find by id
		Optional<RuleName> ruleId = ruleNameRepository.findById(rule.getId());
		Assert.assertEquals(ruleId.get().getId(), rule.getId());

		// Delete
		Integer id = rule.getId();
		ruleNameRepository.delete(rule);
		Optional<RuleName> ruleList = ruleNameRepository.findById(id);
		Assert.assertFalse(ruleList.isPresent());
	}
}
