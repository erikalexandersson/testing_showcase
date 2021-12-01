package se.erikalexandersson.homersays.controllers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import se.erikalexandersson.homersays.util.SimpsonsSeasonCode;

@RestController
@OpenAPIDefinition(info = @Info(title = "Homer Says", version = "1.0", description = "What does Homer say?"))
public class HomerController {

	@GetMapping("/homer_says")
	public String homerSays(@RequestParam int season, @RequestParam int episode) {
		try {
			if (findDoh(season, episode)) {
				return "D'oh!";
			} else {
				return "No!";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "D'oh, d'oh, d'oh!";
		}
	}

	private boolean findDoh(int season, int episode) throws IOException {
		String dohListFile = "src/main/resources/static/doh.list";

		BufferedReader reader = new BufferedReader(new FileReader(dohListFile));
		String currentLine = reader.readLine();
		boolean found = false;
		while (currentLine != null) {
			if (findMatch(currentLine, season, episode)) {
				found = true;
			}

			currentLine = reader.readLine();
		}
		reader.close();
		return found;
	}

	private boolean findMatch(String line, int season, int episode) {
		final String regex = "\\s+\\[(\\d+\\D)(\\d+)\\].*$";

		final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
		final Matcher matcher = pattern.matcher(line);

		if (matcher.find()) {
			String seasonCode = matcher.group(1);
			int episodeNumber = Integer.parseInt(matcher.group(2));
			SimpsonsSeasonCode code = SimpsonsSeasonCode.of(seasonCode);
			return code != null && code.season == season && episodeNumber == episode;
		}
		return false;
	}

}
