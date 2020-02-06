package com.Junglee.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;

public class SortHashMapByValue {

	public HashMap<String, Float> sortHashMapByValues(HashMap<String, Float> passedMap) {
		List<String> mapKeys = new ArrayList<>(passedMap.keySet());
		List<Float> mapValues = new ArrayList<>(passedMap.values());
		Collections.sort(mapValues);
		Collections.sort(mapKeys);

		LinkedHashMap<String, Float> sortedMap = new LinkedHashMap<>();

		Iterator<Float> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			Float val = valueIt.next();
			Iterator<String> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				String key = keyIt.next();
				Float comp1 = passedMap.get(key);
				Float comp2 = val;

				if (comp1.equals(comp2)) {
					keyIt.remove();
					sortedMap.put(key, val);
					break;
				}
			}
		}
		return sortedMap;
	}

}
