package com.coderscampus.Assignment_9.repository;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.coderscampus.Assignment_9.domain.Recipe;

@Repository
public class RecipeRepo {
	private List<Recipe> recipes = new ArrayList<>(100);

	public List<Recipe> getRecipes() {
		return recipes;
	}

}