package com.coderscampus.Assignment_9.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import com.coderscampus.Assignment_9.domain.Recipe;
import com.coderscampus.Assignment_9.repository.RecipeRepo;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	@Autowired
	private RecipeRepo recipeRepo;

	private List<Recipe> allRecipes() {

		CSVFormat records = CSVFormat.EXCEL.withDelimiter(',').withEscape('\\').withSkipHeaderRecord()
				.withHeader("Cooking Minutes", "Dairy Free", "Gluten Free", "Instructions", "Preparation Minutes",
						"Price Per Serving", "Ready In Minutes", "Servings", "Spoonacular Score", "Title", "Vegan",
						"Vegetarian")
				.withIgnoreSurroundingSpaces();

		Reader read = null;
		CSVParser csvParsers = null;
		try {
			read = new FileReader("recipes.txt");
			csvParsers = new CSVParser(read, records);

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		for (CSVRecord record : csvParsers) {
			Integer cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));
			Boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));
			Boolean glutenFree = Boolean.parseBoolean(record.get("Gluten Free"));
			String instructions = record.get("Instructions");
			Double prepMin = Double.parseDouble(record.get("Preparation Minutes"));
			Double price = Double.parseDouble(record.get("Price Per Serving"));
			Integer readyInMin = Integer.parseInt(record.get("Ready In Minutes"));
			Integer servings = Integer.parseInt(record.get("Servings"));
			Double score = Double.parseDouble(record.get("Spoonacular Score"));
			String title = record.get("Title");
			Boolean vegan = Boolean.parseBoolean(record.get("Vegan"));
			Boolean vegetarian = Boolean.parseBoolean(record.get("Vegetarian"));

			Recipe recipe = new Recipe(cookingMinutes, dairyFree, glutenFree, instructions, prepMin, price, readyInMin,
					servings, score, title, vegan, vegetarian);

			recipeRepo.getRecipes().add(recipe);

		}

		return recipeRepo.getRecipes();
	}

	public List<Recipe> getAllRecipes() {
		if (recipeRepo.getRecipes().size() == 0) {
			return allRecipes();
		}
		return recipeRepo.getRecipes();
	}

	public List<Recipe> getGlutenFree() {
		return getAllRecipes().stream().filter(Recipe -> Recipe.getGlutenFree()).collect(Collectors.toList());
	}

	public List<Recipe> getVegan() {
		return getAllRecipes().stream().filter(Recipe -> Recipe.getVegan()).collect(Collectors.toList());
	}

	public List<Recipe> getVeganGlutenFree() {
		return getAllRecipes().stream().filter(recipe -> recipe.getVegan() && recipe.getGlutenFree())
				.collect(Collectors.toList());
	}

	public List<Recipe> getVegetarian() {
		return getAllRecipes().stream().filter(Recipe -> Recipe.getVegetarian()).collect(Collectors.toList());
	}

}
