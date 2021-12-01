package se.erikalexandersson.homersays.util;

import java.util.HashMap;
import java.util.Map;

public enum SimpsonsSeasonCode {
	S01("7G", 1), S02("7F", 2), S03("8F", 3), S04("9F", 4), S05("1F", 5), S06("2F", 6), S07("3F", 7), S08("4F", 8), S09(
			"5F", 9), S10("AABF", 10), S11("BABF", 11), S12("CABF",
					12), S13("DABF", 13), S14("EABF", 14), S15("FABF", 15), S16("GABF", 16), S17("HABF", 17);

	public String seasonCode;
	public int season;

	static Map<String, SimpsonsSeasonCode> map;

	SimpsonsSeasonCode(String seasonCode, int season) {
		this.seasonCode = seasonCode;
		this.season = season;
	}

	public static SimpsonsSeasonCode of(String seasonCode) {
		if (map == null) {
			map = new HashMap<>();
			for (SimpsonsSeasonCode code : SimpsonsSeasonCode.values()) {
				map.put(code.seasonCode, code);
			}
		}

		return map.get(seasonCode);
	}

}
