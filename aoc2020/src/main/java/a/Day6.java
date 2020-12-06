package a;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

public class Day6 {
	@Data
	public static class Group {
		private Map<String, Integer> questions;
		private int size;
		
		public Group(Map<String, Integer> questions, int size) {
			this.questions = questions;
			this.size = size;
		}
		
		public int numUniqueQuestionsAnswered() {
			// If 2 people in a group answered a: yes, we'd only count
			// that once ...
			return questions.keySet().size();
		}
		
		public int numQuestionsAnsweredByEveryone() {
			int sum = 0;
			for (Integer v : questions.values()) {
				if (v == size) {
					sum++;
				}
			}
			return sum;
		}
	}

	public static void main(String[] args) throws Exception {
		List<Group> groups = new ArrayList<>();
		
		String input = Files.readString(Path.of("inputs/6.txt"));
		for (String group : input.split("\\n\\n")) {
			// Tracking occurrences of answered questions
			Map<String, Integer> answeredQuestions = new HashMap<>();
			
			// Tracking number of people found
			int foundPeople = 0;
			
			for (String person : group.split("\\n")) {
				foundPeople++;
				for (char c : person.toCharArray()) {
					answeredQuestions.put(
						Character.toString(c),
						answeredQuestions.getOrDefault(Character.toString(c), 0) + 1
					);
				}
			}
			groups.add(new Group(answeredQuestions, foundPeople));
		}
		
		// Part a
		System.out.println(groups.stream().collect(Collectors.summingInt(Group::numUniqueQuestionsAnswered)));
		
		// Part b
		System.out.println(groups.stream().collect(Collectors.summingInt(Group::numQuestionsAnsweredByEveryone)));
	}
}
